package engine;

import abilitiesmanagement.ActionSelector;
import itemshandling.ItemBase;
import mapvariables.Map;
import mapvariables.PositionModel;
import monster.MonsterBase;
import players.Player;

import java.util.List;
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
                if (currentMonster.isEntangled() && currentMonster.getDuration() > 0) {
                    System.out.println("The monster is entangled and skips its turn!");

                    currentMonster.setEntangled(false, currentMonster.getDuration());
                    break;
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

        ActionSelector.chooseAction(currentPlayer, gameMap.getPlayerLocation(currentPlayer).monsters);
    }

    private void handleMonsterTurn(Player player, MonsterBase monster) {
        if (monster.isTaunted()) {
            System.out.println(monster.getName() + " targets " + player.getName() + " due to Taunt!");
        } else {
            int reduction = player.getHero().getEquippedArmorProtection();
            int monsterDamage = (monster.Attack() - reduction);
            player.getHero().setHP(player.getHero().getHP() - monsterDamage);
            System.out.printf("%s hit you for %d damage!%n", monster.getName(), monsterDamage);
        }
    }

        private void askForFlee(Player player) {
            System.out.printf("%s, do you want to flee? (y/n)%n", player.getName());
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("y")) {
                flee(player);
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
