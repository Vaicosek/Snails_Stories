package engine;

import abilitiesmanagement.ActionSelector;
import itemshandling.ItemBase;
import mapvariables.Map;
import mapvariables.PositionModel;
import monster.MonsterBase;
import players.Player;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CombatRound {
        private Player player;
        private List<MonsterBase> monsters;
        private Map gameMap;

    public CombatRound(Player player, List<MonsterBase> monsters, Map gameMap) {
        this.player = player;
        this.monsters = monsters;
        this.gameMap = gameMap;
    }

    public void executeRound() {
        System.out.println("You can choose if you want to flee now by pressing Q");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if (s.equalsIgnoreCase("q")) {
            flee(player);
        }

        PositionModel playerPosition = gameMap.getPlayerPosition().get(player);
        List<Player> players = gameMap.getPlayersAtLocation(playerPosition.x, playerPosition.y);
        int currentMonsterIndex = 0;
        int turnCounter = 0; // Initialize the turn counter to 0

        // Ask all players at the beginning of the fight if they want to join
        for (Player currentPlayer : players) {
            if (currentPlayer != player && currentPlayer.getHero().getHP() > 0) {
                System.out.printf("%s, do you want to join the fight? (y/n)%n", currentPlayer.getName());
                String input = scanner.nextLine().trim();
                if (input.equalsIgnoreCase("y")) {
                    System.out.printf("%s has joined the fight!%n", currentPlayer.getName());
                }
            }
        }

        while (!monsters.isEmpty() && player.getHero().getHP() > 0) {
            MonsterBase currentMonster = monsters.size() == 1 ? monsters.get(0) : MonsterBase.chooseMonster(monsters);

            // Loop through all players and let them attack the current monster
            for (Player currentPlayer : players) {
                if (currentPlayer.getHero().getHP() <= 0) {
                    continue;
                }


                handlePlayerTurn(currentPlayer, currentMonster);

                if (currentMonster.getHP() <= 0) {
                    System.out.printf("%s has been defeated!%n", currentMonster.getName());
                    System.out.printf("%s gained %d XP!%n", currentPlayer.getName(), currentMonster.MonsterXp);
                    currentPlayer.increaseXP(currentMonster.MonsterXp);
                    monsters.remove(currentMonster);
                    ItemBase.DropItem(currentPlayer, currentPlayer.getInventory());
                    currentMonsterIndex = 0; // Reset monster index
                    break; // Only one player can defeat the monster per turn
                }

                turnCounter++; // Increment the turn counter after player's attack
            }


            for (MonsterBase monster : monsters) {
                if (player.getHero().getHP() <= 0) {
                    break;
                }

                handleMonsterTurn(player, monster);

                if (player.getHero().getHP() <= 0) {
                    System.out.println("You have been defeated...");
                    // TODO: handle game over
                    break; // End the fight if the player has been defeated
                }

                turnCounter++; // Increment the turn counter after monster's attack
            }

            currentMonsterIndex++;
        }

        if (player.getHero().getHP() > 0) {
            System.out.println("You won the battle!");
        }
    }

    private void handlePlayerTurn(Player currentPlayer, MonsterBase currentMonster) {
        ActionSelector.chooseAction(currentPlayer, monsters);
    }

    private void handleMonsterTurn(Player player, MonsterBase monster) {
        if (monster.isTaunted() && monster.getTauntedDuration() > 0) {
            handleTauntedMonsterTurn(player, monster);
        } else if (monster.isMisdirected() && monster.getMisdirectedDuration() > 0) {
            handleMisdirectedMonsterTurn(monster,gameMap.getPlayerPosition().get(player));
        } else if (monster.isEntangled() && monster.getEntangledDuration() > 0) {
            System.out.println("The monster is entangled and skips its turn!");
            monster.setEntangled(false, monster.getEntangledDuration());
        } else {
            handleRegularMonsterTurn(player, monster);
        }
    }


    private void handleRegularMonsterTurn(Player player, MonsterBase monster) {
        int reduction = player.getHero().getEquippedArmorProtection();
        int monsterDamage = (monster.Attack() - reduction);
        player.getHero().setHP(player.getHero().getHP() - monsterDamage);
        System.out.printf("%s hit you for %d damage!%n", monster.getName(), monsterDamage);
    }


    private void handleMisdirectedMonsterTurn(MonsterBase monster, PositionModel playerPosition) {
        List<Player> playersInSameLocation = gameMap.getPlayersAtLocation(playerPosition.x, playerPosition.y);

        // Exclude the misdirecting player from potential targets
        playersInSameLocation.remove(player);

        if (!playersInSameLocation.isEmpty()) {
            // Choose a random player from the remaining players in the same location
            Player newTarget = playersInSameLocation.get(new Random().nextInt(playersInSameLocation.size()));

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
}
