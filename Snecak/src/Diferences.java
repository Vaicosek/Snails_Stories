import java.util.Scanner;

public class Diferences {
    public void movePlayer(Player player, String direction) {
        int x = player.getX();
        int y = player.getY();
        if (direction.equalsIgnoreCase("up") || direction.equalsIgnoreCase("W")) {
            x -= 1;
        } else if (direction.equalsIgnoreCase("down") || direction.equalsIgnoreCase("S")) {
            x += 1;
        } else if (direction.equalsIgnoreCase("left") || direction.equalsIgnoreCase("A")) {
            y -= 1;
        } else if (direction.equalsIgnoreCase("right") || direction.equalsIgnoreCase("D")) {
            y += 1;
        } else if (direction.equalsIgnoreCase("remain") || direction.equalsIgnoreCase("R")) {
            y += 0;
            x += 0;
        }
    }
    public static void Move() {
        Map gameMap = new Map();
        Player player = new Player();
        gameMap.addPlayer(player);
        Scanner scanner = new Scanner(System.in);
        String direction = "";
        while (!direction.equalsIgnoreCase("q")) {
            System.out.print("Enter a direction (up / w, down / a, left / s, right / d) or press q to exit: ");
            direction = scanner.nextLine();
            gameMap.movePlayer(player, direction);
        }
        scanner.close();
    }
}

