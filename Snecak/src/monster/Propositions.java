package monster;

import engine.GameEngine;
import mapvariables.BiomeModel;
import players.Player;

public class Propositions{
        public void attackMonster(Monster monster) {
            int damage = player.getHero().getDamage();
            int monsterHealth = monster.getHealth();
            monsterHealth -= damage;
            monster.setHealth(monsterHealth);

            if (monster.getHealth() <= 0) {
                int experience = monster.getExperience();
                player.getHero().gainExperience(experience);
                gameMap.removeMonster(monster);
            }
        }

        public void attackPlayer(Player player) {
            Monster currentMonster = Map.getCurrentTile().getMonster();
            int damage = currentMonster.getDamage();
            int playerHealth = player.getHero().getHealth();
            playerHealth -= damage;
            player.getHero().setHealth(playerHealth);

            if (player.getHero().getHealth() <= 0) {
                GameEngine.gameOver();
            }
        }
    BiomeModel currentRoom = map.getPlayerLocation(player);
    public BiomeModel getCurrentTile(Player player) {
        int x = player.getX();
        int y = player.getY();
        return map[x][y];
    }

}
