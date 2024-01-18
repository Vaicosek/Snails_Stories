package engine;

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
          
        }

        private void handlePlayerTurn(Player player) {
            
        }

        private void handleMonsterTurn(MonsterBase monster) {
            
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
