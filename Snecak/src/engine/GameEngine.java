package engine;

import abilitiesmanagement.ActionSelector;
import itemshandling.Inventory;
import itemshandling.ItemBase;
import mapvariables.Map;
import mapvariables.PositionModel;
import monster.MonsterBase;
import players.Player;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

public class GameEngine {

    private Inventory inventory = new Inventory();
    Player[] players;
    Map gameMap;
    private GameStartedEventSource gameStartedEventSource = new GameStartedEventSource();

    public GameEngine() {
        gameMap = new Map(this);
    }

    public void GameStart() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of players (1-8): ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine();
        players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            Player player = new Player();
            System.out.print("Enter name for player " + (i + 1) + ": ");
            player.setName(scanner.nextLine());
            player.pickHero();
            player.LevelUp();
            players[i] = player;
            gameMap.addPlayer(player);


        }
        for (Player player : players) {
            System.out.println(player.getName() + " is a " + player.getHero().getName());
        }
        System.out.println("You woke up at fountain");

        gameStartedEventSource.fireEvent(new GameStartedEvent());

        GameLoop();
    }

    GameEnginePlayerEnum GetPlayerAction(Player player) {
        while (true) {
            System.out.println("Player: " + player.getName() + " Location : " + gameMap.getPlayerLocation(player).Name + "\n HP :" + player.getHero().getHP());
            System.out.println("Choose your action : 1. Move, 2. Fight, 3. Open inventory or 4. Skip your turn");
            Scanner scanner = new Scanner(System.in);
            String s = scanner.next();

            switch (s) {
                case "1" -> {
                    return GameEnginePlayerEnum.MOVE_ON_MAP;
                }
                case "2" -> {
                    if (gameMap.getPlayerLocation(player).monsters.isEmpty()) {
                        System.out.println("There is nothing to fight here");
                        break;

                    }
                    return GameEnginePlayerEnum.FIGHT;
                }
                case "3" -> {
                    return GameEnginePlayerEnum.OPEN_INVENTORY;
                }
                case "4" -> {
                    return GameEnginePlayerEnum.SKIP_YOUR_TURN;
                }
                default -> System.out.println("Invalid input try again");
            }
        }
    }

    private void GameLoop() {
        while (true) {
            for (var player : players) {
                if (!player.isAlive()) {
                    continue;
                }

                var action = GetPlayerAction(player);
                if (action == GameEnginePlayerEnum.SKIP_YOUR_TURN) {
                    continue;
                }

                if (action == GameEnginePlayerEnum.MOVE_ON_MAP) {
                    move(player);

                }
                if (action == GameEnginePlayerEnum.FIGHT) {
                    fight(player, gameMap.getPlayerLocation(player).monsters);
                }
                if (action == GameEnginePlayerEnum.OPEN_INVENTORY) {
                    inventory.openInventoryMenu(player.getHero());

                }


            }
        }
    }

    public void move(Player player) {
        System.out.print("Enter a direction (up, down, left, right) or press q to exit: ");
        Scanner scanner = new Scanner(System.in);
        String direction = "";
        direction = scanner.nextLine().toUpperCase();
        if (direction.equals("Q")) {
            return;
        }

        MovementDirectionEnum movementDirectionEnum;
        try {
            movementDirectionEnum = MovementDirectionEnum.valueOf(direction);
        } catch (Exception e) {
            System.out.println("Invalid input");
            move(player);
            return;
        }

        try {

            gameMap.movePlayer(player, movementDirectionEnum);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    private void fight(Player player, List<MonsterBase> monsters) {
        System.out.println("You can choose if you want to flee now by pressing Q");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if (s.equalsIgnoreCase("q")) {
            flee();
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

                ActionSelector.chooseAction(currentPlayer, gameMap.getPlayerLocation(currentPlayer).monsters);

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

            // Monsters' turn
            for (MonsterBase monster : monsters) {
                if (player.getHero().getHP() <= 0) {
                    break;
                }

                int monsterDamage = monster.Attack();
                player.getHero().setHP(player.getHero().getHP() - monsterDamage);
                System.out.printf("%s hit you for %d damage!%n", monster.getName(), monsterDamage);

                turnCounter++; // Increment the turn counter after monster's attack
            }

            if (player.getHero().getHP() <= 0) {
                System.out.println("You have been defeated...");
                // TODO: handle game over
                break; // End the fight if the player has been defeated
            }

            currentMonsterIndex++;
        }

        if (player.getHero().getHP() > 0) {
            System.out.println("You won the battle!");
        }
    }





    private void flee() {

    }


    public int getGroupLevel() {
        List<Player> playerList = Arrays.asList(players);
        return calculateGroupLevel(playerList);
    }

    public static int calculateGroupLevel(List<Player> players) {
        int totalLevels = 0;
        int numPlayers = players.size();
        for (Player player : players) {
            totalLevels += player.getHero().getLevel();
        }
        if (numPlayers > 0) {
            return totalLevels / numPlayers;
        } else {
            return 0;
        }
    }

    public List<Player> getPlayers() {

        return Arrays.asList(players);
    }

    public void registerListener(GameStartedListener listener) {
        gameStartedEventSource.registerListener(listener);
    }

    public void unregisterListener(GameStartedListener listener) {
        gameStartedEventSource.unregisterListener(listener);
    }

}