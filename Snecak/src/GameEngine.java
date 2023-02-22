import java.util.Scanner;

public class GameEngine {
    Player[] players;
    Map gameMap = new Map();

    public void GameStart() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of players (2-8): ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine();
        players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            Player player = new Player();
            System.out.print("Enter name for player " + (i + 1) + ": ");
            player.setName(scanner.nextLine());
            player.pickHero();
            players[i] = player;
            gameMap.addPlayer(player);
        }
        for (Player player : players) {
            System.out.println(player.getName() + " is a " + player.getHero().getName());
        }
        GameLoop();
    }
GameEnginePlayerEnum  GetPlayerAction(Player player) {
    while (true) {
        System.out.println("Location : " + gameMap.getPlayerLocation(player) + "\n HP :" + player.getHero().getHP());
        System.out.println("Choose your action : 1. Move, 2. Search, 3. Fight, 4. Flee, 5. Open inventory or 6. skip your turn");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();

        if (s.equals("1")) {
            return GameEnginePlayerEnum.MOVE_ON_MAP;
        } else if (s == "2") {
            return GameEnginePlayerEnum.SEARCH;
        } else if (s == "3") {
            return GameEnginePlayerEnum.FIGHT;
        } else if (s == "4") {
            return GameEnginePlayerEnum.FLEE;
        } else if (s == "5") {
            return GameEnginePlayerEnum.OPEN_INVENTORY;
        } else if (s == "6") {
            return GameEnginePlayerEnum.SKIP_YOUR_TURN;
        }
        else {
            System.out.println("Invalid input try again");
        }
    }
}

    private void GameLoop(){
        while(true) {
            for(var player : players){
               if (player.isAlive() == false) {
                   continue;
               }

              var action = GetPlayerAction(player);
               if(action == GameEnginePlayerEnum.MOVE_ON_MAP){
                   Move(player);
               }

            }
        }
    }

    public void Move(Player player) {
        Scanner scanner = new Scanner(System.in);
        String direction = "";
        System.out.print("Enter a direction (up, down, left, right) or press q to exit: ");
        direction = scanner.nextLine().toUpperCase();
        if (direction == "Q") {
            return;
        }
        MovementDirectionEnum movementDirectionEnum = MovementDirectionEnum.valueOf(direction);
        gameMap.movePlayer(player, movementDirectionEnum);
    }
}

