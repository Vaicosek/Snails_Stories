package game.mapvariables;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeckOfCards {

    private final List<String> biomesDeck;
    private final Random random;

    public DeckOfCards() {
        biomesDeck = new ArrayList<>();
        biomesDeck.add("Mountains");
        biomesDeck.add("Desert");
        biomesDeck.add("Deep Forest");
        biomesDeck.add("Forest");
        biomesDeck.add("River");
        biomesDeck.add("Magical Forest");
        biomesDeck.add("Valley");
        biomesDeck.add("Swamp");
        biomesDeck.add("Plain");
        biomesDeck.add("Tundra");
        biomesDeck.add("Cave");
        biomesDeck.add("Tower");
        biomesDeck.add("Castle");
        biomesDeck.add("Village");
        biomesDeck.add("Town");
        biomesDeck.add("Ruin");
        biomesDeck.add("Dungeon");

        random = new Random();
    }

    public BiomesModel drawRandomBiomesCard() {
        return drawRandomCard(biomesDeck);
    }

    public BiomesModel getDefaultLocation() {
        var fountain = new BiomesModel("Fountain");
        fountain.monsters = new ArrayList<>();
        return fountain;
    }

    private BiomesModel drawRandomCard(List<String> deck) {
        int index = random.nextInt(deck.size());
        return new BiomesModel(deck.get(index));
    }
}
