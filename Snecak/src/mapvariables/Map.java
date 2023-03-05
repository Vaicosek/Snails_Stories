package mapvariables;

import engine.GameEngine;
import engine.MovementDirectionEnum;
import jdk.jshell.spi.ExecutionControl;
import mapvariables.BiomeModel;
import monster.MonsterFactory;
import players.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class Map {

    public int Height;
    public int Width;
    private BiomeModel[][] map;
    private DeckOfCards deckOfCards;
    private ArrayList<Player> players;

    MonsterFactory monsterFactory;

    public Map(GameEngine gameEngine) {
        monsterFactory = new MonsterFactory(this, gameEngine);
        this.Height = 40;
        this.Width = 40;
        this.map = new BiomeModel[Height][Width];
        this.deckOfCards = new DeckOfCards();
        this.players = new ArrayList<Player>();
        map[Height / 2][Width / 2] = deckOfCards.getDefaultLocation();
    }

    public BiomeModel getPlayerLocation(Player player) {
        return map[player.getX()][player.getY()];
    }

    public void addPlayer(Player player) {
        player.setX(Width / 2);
        player.setY(Height / 2);
        players.add(player);
    }

    public void movePlayer(Player player, MovementDirectionEnum direction) throws Exception {
        int x = player.getX();
        int y = player.getY();


        if (direction == MovementDirectionEnum.UP) {
            x -= 1;
        } else if (direction == MovementDirectionEnum.DOWN) {
            x += 1;
        } else if (direction == MovementDirectionEnum.LEFT) {
            y -= 1;
        } else if (direction == MovementDirectionEnum.RIGHT) {
            y += 1;
        }
        else
            throw new Exception("Invalid Enum Value");



        if (checkMapBoundaries(x, y)) {
            System.out.println("Invalid move: off the edge of the map.");
            return;
        }
        player.setX(x);
        player.setY(y);


        if (map[x][y] == null) {
            map[x][y] = deckOfCards.drawRandomBiomeCard();
            map[x][y].monsters = monsterFactory.CreateMonsters(players.size());
        }

    }

    Boolean checkMapBoundaries(int x, int y) {
        return !(x >= 0 && x < Height && y >= 0 && y < Width);
    }

}

