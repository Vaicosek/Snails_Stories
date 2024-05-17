package game.engine;

import game.abilities.AbilityTemplate;
import game.abilities.EntityAbilityTemplate;
import game.abilities.TickAbilityTemplate;
import game.abilitiesmanagement.ActionSelector;
import game.hero.HeroTemplate;
import game.itemshandling.ItemFactory;
import game.mapvariables.GameMap;
import game.mapvariables.PositionModel;
import game.monster.MonsterBase;
import game.players.Player;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

public class CombatRound {
    private final Player player;
    private final List<MonsterBase> monsters;
    private final GameMap gameMap;
    private int turnCounter;
    private final Scanner scanner;
    private static final Logger logger = Logger.getLogger(CombatRound.class.getName());

    private final Random random = new Random();

    public CombatRound(Player player, List<MonsterBase> monsters, GameMap gameMap) {
        this.player = player;
        this.monsters = monsters;
        this.gameMap = gameMap;
        this.scanner = new Scanner(System.in);
        this.turnCounter = 0;
    }

    public void executeRound() {
        PositionModel playerPosition = gameMap.getPlayerPosition().get(player);
        List<Player> players = gameMap.getPlayersAtLocation(playerPosition.x, playerPosition.y);

        // Ask players to join the fight
        askPlayersToJoin(players);

        // Allow the player to flee
        allowPlayerToFlee();

        while (!monsters.isEmpty()) {
            MonsterBase currentMonster = monsters.size() == 1 ? monsters.get(0) : MonsterBase.chooseMonster(monsters);

            // Loop through all players and let them attack the current monster
            handlePlayerTurn(currentMonster, players);
            handleMonsterTurn(player, currentMonster);

            if (monsters.isEmpty()) {
                break;
            }

            turnCounter++; // Increment the turn counter after player's attack
        }

        // Display the result of the battle
        displayBattleResult();
    }

    private void askPlayersToJoin(List<Player> players) {
        for (Player currentPlayer : players) {
            if (currentPlayer != player && currentPlayer.getHero().getHp() > 0) {
                logger.info(String.format("%s, do you want to join the fight? (y/n)%n", currentPlayer.getName()));
                String input = scanner.nextLine().trim();
                if (input.equalsIgnoreCase("y")) {
                    logger.info(String.format("%s has joined the fight!%n", currentPlayer.getName()));
                }
            }
        }
    }

    private void allowPlayerToFlee() {
        logger.info("You can choose if you want to flee now by pressing Q");
        String s = scanner.nextLine();
        if (s.equalsIgnoreCase("q")) {
            flee(player);
        }
    }

    private void handleDefeatedMonster(HeroTemplate hero, MonsterBase currentMonster) {
        if (currentMonster.getHp() <= 0) {
            logger.info(String.format("%s has been defeated!%n", currentMonster.getName()));
            logger.info(String.format("%s gained %d XP!%n", player.getName(), currentMonster.getMonsterXp()));
            hero.increaseXP(currentMonster.getMonsterXp());
            monsters.remove(currentMonster);
            ItemFactory.dropItem(hero, hero.getInventory());
        }
    }

    private void displayBattleResult() {
        if (player.getHero().getHp() > 0) {
            logger.info("You won the battle!");
        }
    }

    private void handlePlayerTurn(MonsterBase currentMonster, List<Player> players) {
        for (Player currentPlayer : players) {
            ActionSelector.chooseAction(currentPlayer, currentMonster, monsters);
            applyTickEffects(currentPlayer);
            handleDefeatedMonster(currentPlayer.getHero(), currentMonster);
        }
    }

    private void handleMonsterTurn(Player player, MonsterBase monster) {
        if (monster.isTaunted() && monster.getTauntedDuration() > 0) {
            handleTauntedMonsterTurn(player, monster);
        } else if (monster.isMisdirected() && monster.getMisdirectedDuration() > 0) {
            handleMisdirectedMonsterTurn(monster, gameMap.getPlayerPosition().get(player));
        } else if (monster.isEntangled() && monster.getEntangledDuration() > 0) {
            logger.info("The monster skips its turn!");
            monster.setEntangled(false, monster.getEntangledDuration());
        } else {
            double chance = Math.random();
            if (chance <= 0.5) {
                handleMonsterAttackOnEntities(player, monster);
            } else {
                handleMonsterAttacksOnPlayers(player, monster);
            }
        }
    }

    private void handleMonsterAttackOnEntities(Player player, MonsterBase monster) {
        List<EntityAbilityTemplate> entities = player.getSummonedEntities();
        if (entities != null && !entities.isEmpty()) {
            for (EntityAbilityTemplate entity : entities) {
                int entityDamage = entity.performAutoAttack();
                monster.takeDamage(entityDamage);
                logger.info(String.format("%s attacked %s for %d damage!%n", entity.getEntityName(), monster.getName(), entityDamage));
            }
        } else {
            logger.info("The monster has no entities to attack.");
        }
    }

    private void handleMonsterAttacksOnPlayers(Player player, MonsterBase monster) {
        int playerDamage = monster.attack();
        player.getHero().setHp(player.getHero().getHp() - playerDamage);
        logger.info(String.format("%s hit you for %d damage!%n", monster.getName(), playerDamage));
    }

    private void handleMisdirectedMonsterTurn(MonsterBase monster, PositionModel playerPosition) {
        List<Player> playersInSameLocation = gameMap.getPlayersAtLocation(playerPosition.x, playerPosition.y);
        playersInSameLocation.remove(player);

        if (!playersInSameLocation.isEmpty()) {
            Player newTarget = playersInSameLocation.get(random.nextInt(playersInSameLocation.size()));
            int monsterDamage = monster.attack();
            newTarget.getHero().setHp(newTarget.getHero().getHp() - monsterDamage);
            logger.info(String.format("%s hit %s for %d damage!%n", monster.getName(), newTarget.getName(), monsterDamage));
        } else {
            logger.info("The misdirected monster is unable to find another target and skips its turn!");
        }

        if (monster.getMisdirectedDuration() > 0) {
            monster.setMisdirected(false, 0);
        }
    }

    private void handleTauntedMonsterTurn(Player tauntingPlayer, MonsterBase monster) {
        int reduction = tauntingPlayer.getHero().getEquippedArmorProtection();
        int monsterDamage = monster.attack() - reduction;
        tauntingPlayer.getHero().setHp(tauntingPlayer.getHero().getHp() - monsterDamage);
        logger.info(String.format("%s hits you for %d damage due to Taunt!%n", monster.getName(), monsterDamage));

        if (monster.getTauntedDuration() > 0) {
            monster.setTaunted(false, 0);
        }
    }

    private void flee(Player player) {
        PositionModel currentPosition = gameMap.getPlayerPosition().get(player);
        PositionModel previousPosition = player.getPreviousPosition();

        if (previousPosition != null) {
            logger.info(String.format("%s fled from %s to %s!%n", player.getName(), currentPosition, previousPosition));
            gameMap.getPlayerPosition().put(player, new PositionModel(previousPosition.x, previousPosition.y));
        } else {
            logger.info("You cannot flee from the current location.");
        }
    }

    private void applyTickEffects(Player player) {
        List<AbilityTemplate> abilities = player.getHero().getAbilities();
        for (AbilityTemplate ability : abilities) {
            if (ability instanceof TickAbilityTemplate tickAbility && tickAbility.isEffectActive()) {
                for (MonsterBase monster : monsters) {
                    tickAbility.onTick(player, monster, monsters, turnCounter);
                    logger.info(String.format("Tick effect of %s applied to %s on turn %d.%n", tickAbility.getName(), monster.getName(), turnCounter));
                }
            }
        }
    }
}
