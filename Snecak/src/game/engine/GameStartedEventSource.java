package game.engine;

import java.util.ArrayList;
import java.util.List;

public class GameStartedEventSource {
    private final List<GameStartedListener> listeners = new ArrayList<>();

    public void registerListener(GameStartedListener listener) {
        listeners.add(listener);
    }

    public void unregisterListener(GameStartedListener listener) {
        listeners.remove(listener);
    }

    public void fireEvent(GameStartedEvent event) {
        for (GameStartedListener listener : listeners) {
            listener.onGameStarted(event);
        }
    }
}