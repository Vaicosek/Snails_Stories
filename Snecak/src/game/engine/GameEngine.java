package game.engine;

import game.itemshandling.Inventory;
import game.mapvariables.GameMap;
import game.monster.MonsterBase;
import game.players.Player;

import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameEngine {

    private final Inventory inventory = new Inventory();
    Player[] players;
    GameMap gameMap;
    private final GameStartedEventSource gameStartedEventSource = new GameStartedEventSource();
    private Map<GameEnginePlayerEnum, Consumer<Player>> actionHandlers;
    private final Map<String, GameEnginePlayerEnum> inputToActionMap;
    private static final Logger logger = Logger.getLogger(GameEngine.class.getName());

    public GameEngine() {
        gameMap = new GameMap(this);
        initializeActionHandlers();
        inputToActionMap = new HashMap<>();
        mapInputToActions();
    }

    private void mapInputToActions() {
        inputToActionMap.put("1", GameEnginePlayerEnum.MOVE_ON_MAP);
        inputToActionMap.put("2", GameEnginePlayerEnum.FIGHT);
        inputToActionMap.put("3", GameEnginePlayerEnum.OPEN_INVENTORY);
        inputToActionMap.put("4", GameEnginePlayerEnum.SKIP_YOUR_TURN);
    }

    private void initializeActionHandlers() {
        actionHandlers = new EnumMap<>(GameEnginePlayerEnum.class);
        actionHandlers.put(GameEnginePlayerEnum.MOVE_ON_MAP, this::move);
        actionHandlers.put(GameEnginePlayerEnum.FIGHT, this::fightWrapper);
        actionHandlers.put(GameEnginePlayerEnum.OPEN_INVENTORY, this::openInventory);

    }

    public void gameStart() {
        Scanner scanner = new Scanner(System.in);
        logger.info("Enter number of players (1-8): ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine();
        players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            Player player = new Player();
            int finalI = i;
            logger.log(Level.INFO, () -> String.format("Enter name for player %d: ", finalI + 1));
            player.setName(scanner.nextLine());
            player.pickHero();
            player.levelUp();
            players[i] = player;
            gameMap.addPlayer(player);


        }
        for (Player player : players) {
            logger.info(player.getName() + " is a " + player.getHero().getName());
        }
        logger.info("You woke up at fountain");

        gameStartedEventSource.fireEvent(new GameStartedEvent());

        gameLoop();
    }

    GameEnginePlayerEnum getPlayerAction(Player player) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            logger.info("Player: " + player.getName() + " Location : " + gameMap.getPlayerLocation(player).name + "\n HP :" + player.getHero().getHp());
            logger.info("Choose your action : 1. Move, 2. Fight, 3. Open inventory or 4. Skip your turn");

            String input = scanner.next();
            GameEnginePlayerEnum action = inputToActionMap.get(input);

            if (action != null) {
                if (action == GameEnginePlayerEnum.FIGHT && gameMap.getPlayerLocation(player).monsters.isEmpty()) {
                    logger.info("There is nothing to fight here");
                    continue; // Prompt the user for input again
                }
                return action;
            } else {
                logger.warning("Invalid input try again");
            }
        }
    }

    private void gameLoop() {
        while (true) {
         for (Player player : players) {


                GameEnginePlayerEnum action = getPlayerAction(player);
                Consumer<Player> actionHandler = actionHandlers.get(action);

                    actionHandler.accept(player);

                }
            }
        }

    private void openInventory(Player player) {
        inventory.openInventoryMenu();
    }

    public void move(Player player) {
        logger.info("Enter a direction (up, down, left, right) or press q to exit: ");
        Scanner scanner = new Scanner(System.in);
        String direction;
        direction = scanner.nextLine().toUpperCase();
        if (direction.equals("Q")) {
            return;
        }

        player.setPreviousPosition(gameMap.getPlayerPosition().get(player));

        MovementDirectionEnum movementDirectionEnum;
        try {
            movementDirectionEnum = MovementDirectionEnum.valueOf(direction);
        } catch (Exception e) {
            logger.info("Invalid input");
            move(player);
            return;
        }

        try {

            gameMap.movePlayer(player, movementDirectionEnum);

        } catch (Exception e) {
            logger.info(e.getMessage());

        }
    }

    private void fightWrapper(Player player) {
        List<MonsterBase> monsters = gameMap.getPlayerLocation(player).monsters;
        fight(player, monsters);
    }

    private void fight(Player player, List<MonsterBase> monsters) {
        CombatRound combatRound = new CombatRound(player, monsters, gameMap);


        combatRound.executeRound();
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