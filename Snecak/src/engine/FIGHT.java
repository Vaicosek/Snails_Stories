/*package engine;

import itemshandling.ItemBase;
import monster.MonsterBase;
import players.Player;

import java.util.List;
import java.util.Scanner;

public class FIGHT {
    private void fight(Player player, List<MonsterBase> monsters) {
        System.out.println("You can choose if you want to flee now by pressing Q");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if (s.equalsIgnoreCase("q")) {
            flee();
        }

        List<Player> players = gameMap.getPlayersAtLocation(gameMap.getPlayerPosition().get(player));
        int currentPlayerIndex = players.indexOf(player);
        int currentMonsterIndex = 0;

        while (player.getHero().getHP() > 0) {
            if (monsters.isEmpty()) {
                break;
            }

            MonsterBase currentMonster = monsters.size() == 1 ? monsters.get(0) : MonsterBase.chooseMonster(monsters);

            // Player's turn
            System.out.printf("It's %s's turn to attack!%n", player.getName());
            int damageDealt = player.getHero().getAttack();
            currentMonster.HP -= damageDealt;
            System.out.printf("You hit %s for %d damage!%n", currentMonster.getName(), damageDealt);

            if (currentMonster.getHP() <= 0) {
                System.out.printf("%s has been defeated!%n", currentMonster.getName());
                System.out.printf("You gained %d XP!%n", currentMonster.MonsterXp);
                player.increaseXP(currentMonster.MonsterXp);
                monsters.remove(currentMonster);
                ItemBase.DropItem(player);
                currentMonsterIndex = 0; // Reset monster index
                continue;
            }

            // Other players' turn
            for (int i = 1; i < players.size(); i++) {
                Player currentPlayer = players.get((currentPlayerIndex + i) % players.size());

                // Check if the current player's hero is alive and ask if they want to join the fight
                if (currentPlayer.getHero().getHP() > 0) {
                    System.out.printf("%s, do you want to join the fight against %s? (y/n)%n", currentPlayer.getName(), currentMonster.getName());
                    String input = scanner.nextLine().trim();
                    if (input.equalsIgnoreCase("y")) {
                        int damageDealtByOtherPlayer = currentPlayer.getHero().getAttack();
                        currentMonster.HP -= damageDealtByOtherPlayer;
                        System.out.printf("%s hit %s for %d damage!%n", currentPlayer.getName(), currentMonster.getName(), damageDealtByOtherPlayer);
                        if (currentMonster.getHP() <= 0) {
                            System.out.printf("%s has been defeated!%n", currentMonster.getName());
                            System.out.printf("%s gained %d XP!%n", currentPlayer.getName(), currentMonster.MonsterXp);
                            currentPlayer.increaseXP(currentMonster.MonsterXp);
                            monsters.remove(currentMonster);
                            ItemBase.DropItem(currentPlayer);
                            currentMonsterIndex = 0; // Reset monster index
                            break;
                        }
                    }
                }
            }

            // Monsters' turn
            for (MonsterBase monster : monsters) {
                if (player.getHero().getHP() <= 0) {
                    break;
                }

                int  monsterDamage = monster.Attack();
                player.getHero().setHP(player.getHero().getHP() - monsterDamage);
                System.out.printf("%s hit you for %d damage!%n", monster.getName(),  monsterDamage);
            }

            if (player.getHero().getHP() <= 0) {
                System.out.println("You have been defeated...");
                // TODO: handle game over
            }

            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            currentMonsterIndex++;
        }

        if (player.getHero().getHP() > 0) {
            System.out.println("You won the battle!");
        }
}*/


/*
ORIGINAL ORIGINAL ORIGINAL

    private void fight(Player player, List<MonsterBase> monsters) {
        System.out.println("You can choose if you Want to Flee now By Pressing Q");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if (s.equalsIgnoreCase("q")) {
            flee();
        }

        */
/*for (Player p : players) {
            var pos = gameMap.getPlayerPosition().get(player);
            if (p != player && gameMap.getPlayerPosition().get(p).x == pos.x && gameMap.getPlayerPosition().get(p).y == pos.y) {

            }
        }*//*



        while (player.getHero().getHP() > 0) {

            if (monsters.isEmpty()) break;

            MonsterBase currentMonster = monsters.size() == 1 ? monsters.get(0) : MonsterBase.chooseMonster(monsters);

            //     System.out.println("Your turn to attack!");
            //    boolean useAbility = false;
            //     System.out.println("Do you want to use an ability? (y/n)");
            //     String input = Scanner.nextLine().trim();
            //    if (input.equalsIgnoreCase("y")) {
            //         useAbility = true;
            //}
            //     if (useAbility) {
            //       player.getHero().powers();
            // int damageDealt = player.getHero().useAbility(ability, currentMonster);
            //    System.out.printf("You used %s and dealt %d damage to %s!%n", ability.getName(), damageDealt, currentMonster.getName());

            int damageDealt = player.getHero().getAttack();
            currentMonster.HP -= damageDealt;
            System.out.printf("You hit %s for %d damage!%n", currentMonster.getName(), damageDealt);

            if (currentMonster.getHP() <= 0) {
                System.out.printf("%s has been defeated!%n", currentMonster.getName());
                System.out.printf("You gained %d XP!%n", currentMonster.MonsterXp);
                player.increaseXP(currentMonster.MonsterXp);
                monsters.remove(currentMonster);
                ItemBase.DropItem(player);
                continue;
            }

            for (MonsterBase monster : monsters) {
                if (player.getHero().getHP() <= 0) {
                    break;
                }

                System.out.printf("%s's turn to attack!%n", monster.getName());
                int monsterDamage = monster.Attack();
                player.getHero().setHP(player.getHero().getHP() - monsterDamage);
                System.out.printf("%s hit you for %d damage! Your HP is now %d%n", monster.getName(), monsterDamage, player.getHero().getHP());
            }
        }
    }*/
