package Game.mapvariables;

import Game.engine.*;
import Game.monster.MonsterBase;
import Game.monster.MonsterFactory;
import Game.players.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameMap implements GameStartedListener {

    public int Height;
    public int Width;
    private BiomeModel[][] gameMapModel;
    private DeckOfCards deckOfCards;
    private ArrayList<Player> players;

    MonsterFactory monsterFactory;
    GameEngine gameEngine;

    public GameMap(GameEngine gameEngine) {
        monsterFactory = new MonsterFactory(this, gameEngine);
        this.Height = 40;
        this.Width = 40;
        this.gameMapModel = new BiomeModel[Height][Width];
        this.deckOfCards = new DeckOfCards();
        this.players = new ArrayList<Player>();
        this.gameEngine = gameEngine;
        gameMapModel[Height / 2][Width / 2] = deckOfCards.getDefaultLocation();
        gameEngine.registerListener(this);
    }

    public BiomeModel getPlayerLocation(Player player) {
        var pos = PlayerPosition.get(player);
        return gameMapModel[pos.x][pos.y];
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
            System.out.println("Invalid move: off the edge of the gameMapModel.");
            return;
        }


        if (gameMapModel[pos.x][pos.y] == null) {
            gameMapModel[pos.x][pos.y] = deckOfCards.drawRandomBiomeCard();


            int groupLevel = gameEngine.getGroupLevel();

            int numMonsters = players.size() + 1;

            List<MonsterBase> monsters = monsterFactory.createMonsters(numMonsters, groupLevel);
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

    public List<Player> getPlayersAtLocation(int x, int y) {
        List<Player> playersAtLocation = new ArrayList<>();
        for (Player player : players) {
            var pos = PlayerPosition.get(player);
            if (pos.x == x && pos.y == y) {
                playersAtLocation.add(player);
            }
        }
        return playersAtLocation;
    }

}

