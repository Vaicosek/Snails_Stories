package mapvariables;

import engine.*;
import jdk.jshell.spi.ExecutionControl;
import mapvariables.BiomeModel;
import monster.MonsterFactory;
import players.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Map implements GameStartedListener {

    public int Height;
    public int Width;
    private BiomeModel[][] map;
    private DeckOfCards deckOfCards;
    private ArrayList<Player> players;

    MonsterFactory monsterFactory;
    GameEngine gameEngine;

    public Map(GameEngine gameEngine) {
        monsterFactory = new MonsterFactory(this, gameEngine);
        this.Height = 40;
        this.Width = 40;
        this.map = new BiomeModel[Height][Width];
        this.deckOfCards = new DeckOfCards();
        this.players = new ArrayList<Player>();
        this.gameEngine = gameEngine;
        map[Height / 2][Width / 2] = deckOfCards.getDefaultLocation();
        gameEngine.registerListener(this);
    }

    public BiomeModel getPlayerLocation(Player player) {
        var pos = PlayerPosition.get(player);
        return map[pos.x][pos.y];
    }

    public void movePlayer(Player player, MovementDirectionEnum direction) throws Exception {
        var pos = PlayerPosition.get(player);

        if (direction == MovementDirectionEnum.UP) {
            pos.x -= 1;
        } else if (direction == MovementDirectionEnum.DOWN) {
            pos.x += 1;
        } else if (direction == MovementDirectionEnum.LEFT) {
            pos.y -= 1;
        } else if (direction == MovementDirectionEnum.RIGHT) {
            pos.y += 1;
        } else
            throw new Exception("Invalid Enum Value");


        if (checkMapBoundaries(pos.x, pos.y)) {
            System.out.println("Invalid move: off the edge of the map.");
            return;
        }


        if (map[pos.x][pos.y] == null) {
            map[pos.x][pos.y] = deckOfCards.drawRandomBiomeCard();
            map[pos.x][pos.y].monsters = monsterFactory.CreateMonsters(players.size());
        }

    }

    Boolean checkMapBoundaries(int x, int y) {
        return !(x >= 0 && x < Height && y >= 0 && y < Width);
    }

    HashMap<Player, PositionModel> PlayerPosition = new HashMap<>();

    @Override
    public void onGameStarted(GameStartedEvent event) {

        for (var player : gameEngine.getPlayers()) {
            PlayerPosition.put(player, new PositionModel(Width / 2, Height / 2));
        }

    }
    public void addPlayer(Player player) {
        players.add(player);
    }

    public HashMap<Player, PositionModel> getPlayerPosition() {
        return PlayerPosition;
    }

    public void destroy() {
        gameEngine.unregisterListener(this);
    }
}

