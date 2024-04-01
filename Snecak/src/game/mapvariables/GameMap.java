package game.mapvariables;

import game.engine.*;
import game.monster.MonsterFactory;
import game.players.Player;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class GameMap implements GameStartedListener {

    public int height;
    public int width;
    private final BiomesModel[][] gamemapmodel;
    private final DeckOfCards deckOfCards;
    private final ArrayList<Player> players;
    private static final Logger logger = Logger.getLogger(GameMap.class.getName());

    MonsterFactory monsterFactory;
    GameEngine gameEngine;

    public GameMap(GameEngine gameEngine) {
        monsterFactory = new MonsterFactory(this, gameEngine);
        this.height = 40;
        this.width = 40;
        this.gamemapmodel = new BiomesModel[height][width];
        this.deckOfCards = new DeckOfCards();
        this.players = new ArrayList<>();
        this.gameEngine = gameEngine;
        gamemapmodel[height / 2][width / 2] = deckOfCards.getDefaultLocation();
        gameEngine.registerListener(this);
    }

    public BiomesModel getPlayerLocation(Player player) {
        var pos = playerPosition.get(player);
        return gamemapmodel[pos.x][pos.y];
    }

    public void movePlayer(Player player, MovementDirectionEnum direction) throws Exception {
        var pos = playerPosition.get(player);

        if (direction == MovementDirectionEnum.UP) {
            pos.x -= 1;
        } else if (direction == MovementDirectionEnum.DOWN) {
            pos.x += 1;
        } else if (direction == MovementDirectionEnum.LEFT) {
            pos.y -= 1;
        } else if (direction == MovementDirectionEnum.RIGHT) {
            pos.y += 1;
        } else {
            throw new Exception("Invalid Enum Value");
        }


        if (checkMapBoundaries(pos.x, pos.y)) {
            logger.info("Invalid move: off the edge of the gameMapModel.");
            return;
        }


        if (gamemapmodel[pos.x][pos.y] == null) {
            gamemapmodel[pos.x][pos.y] = deckOfCards.drawRandomBiomesCard();


            int groupLevel = gameEngine.getGroupLevel();

            int numMonsters = players.size() + 1;

            monsterFactory.createMonsters(numMonsters, groupLevel);
        }

    }

    Boolean checkMapBoundaries(int x, int y) {
        return !(x >= 0 && x < height && y >= 0 && y < width);
    }

    @Getter
    HashMap<Player, PositionModel> playerPosition = new HashMap<>();

    @Override
    public void onGameStarted(GameStartedEvent event) {

        for (var player : gameEngine.getPlayers()) {
            playerPosition.put(player, new PositionModel(width / 2, height / 2));
        }

    }
    public void addPlayer(Player player) {
        players.add(player);
    }

    public void destroy() {
        gameEngine.unregisterListener(this);
    }

    public List<Player> getPlayersAtLocation(int x, int y) {
        List<Player> playersAtLocation = new ArrayList<>();
        for (Player player : players) {
            var pos = playerPosition.get(player);
            if (pos.x == x && pos.y == y) {
                playersAtLocation.add(player);
            }
        }
        return playersAtLocation;
    }

}

