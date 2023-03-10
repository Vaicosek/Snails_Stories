package mapvariables;

import mapvariables.BiomeModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DeckOfCards {

    private List<String> biomeDeck;
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
        biomeDeck.add("Cave");
        biomeDeck.add("Tower");
        biomeDeck.add("Castle");
        biomeDeck.add("Village");
        biomeDeck.add("Town");
        biomeDeck.add("Ruin");
        biomeDeck.add("Dungeon");

        random = new Random();
    }

    public BiomeModel drawRandomBiomeCard() {
        return drawRandomCard(biomeDeck);
    }

    public BiomeModel getDefaultLocation() {
        var fountain = new BiomeModel("Fountain");
        fountain.monsters = new ArrayList<>();
        return fountain;
    }

    private BiomeModel drawRandomCard(List<String> deck) {
        int index = random.nextInt(deck.size());
        return new BiomeModel(deck.get(index));
    }
}
