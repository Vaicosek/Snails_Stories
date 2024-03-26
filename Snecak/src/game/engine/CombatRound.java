package game.engine;

import game.abilities.AbilityTemplate;
import game.abilities.EntityAbilityTemplate;
import game.abilities.TickAbilityTemplate;
import game.abilitiesmanagement.ActionSelector;
import game.itemshandling.ItemBase;
import game.mapvariables.GameMap;
import game.mapvariables.PositionModel;
import game.monster.MonsterBase;
import game.players.Player;


import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CombatRound {
        private Player player;
        private List<MonsterBase> monsters;
        private GameMap gameMap;
        private int turnCounter;
        private Scanner scanner;


        private Random random = new Random();

    public CombatRound(Player player, List<MonsterBase> monsters, GameMap gameMap) {
        this.player = player;
        this.monsters = monsters;
        this.gameMap = gameMap;
        this.scanner = new Scanner(System.in);
        this.turnCounter = 0;
    }

    public void executeRound() {
        Scanner scanner1 = new Scanner(System.in);

        PositionModel playerPosition = gameMap.getPlayerPosition().get(player);
        List<Player> players = gameMap.getPlayersAtLocation(playerPosition.x, playerPosition.y);

        // Ask players to join the fight
        askPlayersToJoin(players);

        // Allow the player to flee
        allowPlayerToFlee(scanner1);

        while (!monsters.isEmpty()) {


            MonsterBase currentMonster = monsters.size() == 1 ? monsters.get(0) : MonsterBase.chooseMonster(monsters);

            // Loop through all players and let them attack the current monster

            handlePlayerTurn(currentMonster, players);


            handleMonsterTurn(player, currentMonster);

            if (monsters.isEmpty()) {
                break;
            }


            turnCounter++; // Increment the turn counter after player's attack


            // Display the result of the battle
            displayBattleResult();
        }

    }

    private void askPlayersToJoin(List<Player> players) {
        for (Player currentPlayer : players) {
            if (currentPlayer != player && currentPlayer.getHero().getHP() > 0) {
                System.out.printf("%s, do you want to join the fight? (y/n)%n", currentPlayer.getName());
                String input = scanner.nextLine().trim();
                if (input.equalsIgnoreCase("y")) {
                    System.out.printf("%s has joined the fight!%n", currentPlayer.getName());
                }
            }
        }
    }

    private void allowPlayerToFlee(Scanner scanner) {
        System.out.println("You can choose if you want to flee now by pressing Q");
        String s = scanner.nextLine();
        if (s.equalsIgnoreCase("q")) {
            flee(player);
        }
    }

    private void handleDefeatedMonster(Player currentPlayer, MonsterBase currentMonster) {

        if (currentMonster.HP < 0) {

            System.out.printf("%s has been defeated!%n", currentMonster.getName());
            System.out.printf("%s gained %d XP!%n", currentPlayer.getName(), currentMonster.MonsterXp);
            currentPlayer.increaseXP(currentMonster.MonsterXp);
            monsters.remove(currentMonster);
            ItemBase.DropItem(currentPlayer, currentPlayer.getInventory());
        }
    }

    private void handlePlayerDefeated() {
        System.out.println("You have been defeated...");
        // TODO: handle game over

    }

    private void displayBattleResult() {
        if (player.getHero().getHP() > 0) {
            System.out.println("You won the battle!");
        }
    }

    private void handlePlayerTurn(MonsterBase currentMonster,List<Player> players ) {
        for (Player currentPlayer : players) {
            ActionSelector.chooseAction(currentPlayer, currentMonster, monsters);
            applyTickEffects(currentPlayer);
            handleDefeatedMonster(currentPlayer, currentMonster);
            }
    }

    private void handleMonsterTurn(Player player, MonsterBase monster) {


        if (monster.isTaunted() && monster.getTauntedDuration() > 0) {
            handleTauntedMonsterTurn(player, monster);
        } else if (monster.isMisdirected() && monster.getMisdirectedDuration() > 0) {
            handleMisdirectedMonsterTurn(monster, gameMap.getPlayerPosition().get(player));
        } else if (monster.isEntangled() && monster.getEntangledDuration() > 0) {
            System.out.println("The monster skips its turn!");
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
        // Check if the monster has any entities to attack
        List<EntityAbilityTemplate> entities = player.getSummonedEntities();
        if (entities != null && !entities.isEmpty()) { // Check if entities is not null before using it
            for (EntityAbilityTemplate entity : entities) {
                // Implement logic for the entity automatically attacking the monster
                int entityDamage = entity.performAutoAttack();
                monster.takeDamage(entityDamage);
                System.out.printf("%s attacked %s for %d damage!%n", entity.getEntityName(), monster.getName(), entityDamage);
            }
        } else {
            // Handle the case when there are no entities to attack
            System.out.println("The monster has no entities to attack.");
        }
    }

        private void handleMonsterAttacksOnPlayers(Player player, MonsterBase monster) {
            // Implement logic for monster attacking players
            int playerDamage = monster.Attack(); // Replace this with the appropriate method for monster attacks
            player.getHero().setHP(player.getHero().getHP() - playerDamage);
            System.out.printf("%s hit you for %d damage!%n", monster.getName(), playerDamage);
        }


    private void handleMisdirectedMonsterTurn(MonsterBase monster, PositionModel playerPosition) {
        List<Player> playersInSameLocation = gameMap.getPlayersAtLocation(playerPosition.x, playerPosition.y);

        // Exclude the misdirecting player from potential targets
        playersInSameLocation.remove(player);

        if (!playersInSameLocation.isEmpty()) {
            // Choose a random player from the remaining players in the same location

            Player newTarget = playersInSameLocation.get(random.nextInt(playersInSameLocation.size()));

            // Attack the new target
            int monsterDamage = monster.Attack();
            newTarget.getHero().setHP(newTarget.getHero().getHP() - monsterDamage);
            System.out.printf("%s hit %s for %d damage!%n", monster.getName(), newTarget.getName(), monsterDamage);
        } else {
            // No other players in the same location, so the monster skips its turn
            System.out.println("The misdirected monster is unable to find another target and skips its turn!");
        }

        // Decrement the misdirection duration
        if (monster.getMisdirectedDuration() > 0) {
            monster.setMisdirected(false, 0);
        }
    }

    private void handleTauntedMonsterTurn(Player tauntingPlayer, MonsterBase monster) {
        int reduction = tauntingPlayer.getHero().getEquippedArmorProtection();
        int monsterDamage = (monster.Attack() - reduction);
        tauntingPlayer.getHero().setHP(tauntingPlayer.getHero().getHP() - monsterDamage);
        System.out.printf("%s hits you for %d damage due to Taunt!%n", monster.getName(), monsterDamage);

        if (monster.getTauntedDuration() > 0) {
            monster.setTaunted(false, 0);
        }
    }

    private void flee(Player player) {
        PositionModel currentPosition = gameMap.getPlayerPosition().get(player);
        PositionModel previousPosition = player.getPreviousPosition();

        // Check if the player has a previous position
        if (previousPosition != null) {
            System.out.printf("%s fled from %s to %s!%n", player.getName(), currentPosition, previousPosition);

            // Move the player to the previous position
            currentPosition.x = previousPosition.x;
            currentPosition.y = previousPosition.y;

            // Update the player's position in the map
            gameMap.getPlayerPosition().put(player, currentPosition);

            // Clear the player's previous position after fleeing

        } else {
            System.out.println("You cannot flee from the current location.");
        }
    }

    private void applyTickEffects(Player player) {
        List<AbilityTemplate> abilities = player.getHero().getAbilities();
        for (AbilityTemplate ability : abilities) {
            if (ability instanceof TickAbilityTemplate tickAbility && (tickAbility.isEffectActive())) {
                    // Assuming the effect applies to specific monsters or all monsters
                    for (MonsterBase monster : monsters) {
                        tickAbility.onTick(player, monster, monsters, turnCounter);
                        System.out.printf( "Tick effect of %s applied to %s on turn %d.%n",
                                tickAbility.getName(), monster.getName(), turnCounter);
                    }

            }
        }
    }
}
