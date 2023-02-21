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
            System.out.println(player.getName() + " is a " + player.getHero());
        }
    }
    public void Move(Player player) {
        Scanner scanner = new Scanner(System.in);
        String direction = "";
            System.out.print("Enter a direction (up, down, left, right) or press q to exit: ");
            direction = scanner.nextLine().toUpperCase();
            if ( direction == "Q"){
                return;
            }
            MovementDirectionEnum movementDirectionEnum = MovementDirectionEnum.valueOf(direction);
            gameMap.movePlayer(player, movementDirectionEnum);
    }

}

