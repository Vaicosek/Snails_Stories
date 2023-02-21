import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DeckOfCards {

    private List<String> biomeDeck;
    private List<String> structureDeck;
    private Random random;

    public DeckOfCards() {
        biomeDeck = new ArrayList<>();
        biomeDeck.add("Mountains");
        biomeDeck.add("Desert");
        biomeDeck.add("Deep Forest");
        biomeDeck.add("Forest");
        biomeDeck.add("River");
        biomeDeck.add("Magical Forest");
        biomeDeck.add("Valley");
        biomeDeck.add("Swamp");
        biomeDeck.add("Plain");
        biomeDeck.add("Tundra");

        structureDeck = new ArrayList<>();
        structureDeck.add("Cave");
        structureDeck.add("Tower");
        structureDeck.add("Castle");
        structureDeck.add("Village");
        structureDeck.add("Town");
        structureDeck.add("Ruin");
        structureDeck.add("Dungeon");

        random = new Random();
    }

    public String drawRandomBiomeCard() {
        return drawRandomCard(biomeDeck);
    }

    public String drawRandomStructureCard() {
        return drawRandomCard(structureDeck);
    }

    private String drawRandomCard(List<String> deck) {
        Collections.shuffle(deck);
        int index = random.nextInt(deck.size());
        return deck.get(index);
    }
}
