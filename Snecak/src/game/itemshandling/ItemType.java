package game.itemshandling;

import game.hero.HeroTemplate;
import game.monster.Dice;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Function;

public enum ItemType {

    CONSUMABLE(
            Arrays.asList("Apple", "Banana", "Carrot", "Chicken leg", "Fish", "Ham", "Pizza", "Roast beef", "Turkey leg", "Watermelon",
                    "Beer", "Coffee", "Juice", "Milk", "Soda", "Tea", "Water", "Wine", "Hot chocolate", "Lemonade",
                    "Potion of healing", "Elixir of life", "Draught of vitality", "Tonic of rejuvenation", "Remedy of restoration",
                    "Salve of mending", "Serum of regeneration", "Ointment of recovery", "Balm of revival", "Lotion of renewal",
                    "Cheese", "Bread", "Eggs", "Honey", "Yogurt", "Peanut butter", "Jelly", "Crackers", "Popcorn", "Chocolate",
                    "Lime soda", "Energy drink", "Iced tea", "Root beer", "Ginger ale", "Fruit punch", "Smoothie", "Iced coffee",
                    "Hot tea", "Coconut water"),
            (item, hero) -> {
                item.setHealth(Dice.getNextNumber(1, 10 + hero.getLevel() * 10));
                item.setDamage(Dice.getNextNumber(1, hero.getLevel() * 2)); // Assuming consumables can have damage.
            },
            item -> "Health: " + item.getHealth() + ", Damage: " + item.getDamage() // Display logic for consumable
    ),
    WEAPON(
            Arrays.asList("arcane staff", "orb of force", "spellbook", "rod of negation", "rod of the pact keeper", "staff of power",
                    "ethereal sword", "void scythe", "chaos axe", "demonic trident", "thorn whip", "oaken club", "spear of the hunt",
                    "moon sickle", "thunderhead hammer", "entangle rope", "totem of the wolf", "dragon teeth dagger", "blazing sun spear",
                    "venomous thorn sword", "dwarven waraxe", "heavy flail", "hammer of the gods", "giant maul", "spiked gauntlets",
                    "sword of the righteous", "glaive of vengeance", "lance of the knight", "crimson greatsword", "executioner's axe",
                    "blades of darkness", "poisoned dart", "assassin's dagger", "hidden blade gauntlet", "thieves' tools", "snake fang sword",
                    "chakram", "divine mace", "holy avenger sword", "mace of smiting", "scepter of radiance", "healing staff", "staff of light",
                    "sunburst bow", "censer of purity", "holy lance"),
            (item, hero) -> item.setDamage(Dice.getNextNumber(1, hero.getLevel() * 11)),
            item -> "Damage: " + item.getDamage() // Display logic for weapon
    ),
    ARMOR(
            Arrays.asList("robe of the archmagi", "cloak of displacement", "ring of turning", "amulet of protection", "bracers of defense",
                    "staff of defense", "shield", "crystal ball", "mantle", "boots", "leather armor", "hide armor", "scale mail",
                    "spider silk vest", "mantle", "embrace", "circlet", "cloak", "mail", "mantle", "chain mail", "splint mail",
                    "plate mail", "adamantine plate", "shield", "mithral armor", "dragonscale mail", "titanium shield", "diamond plate",
                    "crimson mail", "leather armor", "studded leather", "dark cloak", "poisoned hood", "boots", "goggles", "chain",
                    "vest", "boots", "cloak", "plate mail", "faith shield", "breastplate", "amulet of life", "golden gauntlets",
                    "armor of divine favor", "helm", "holy ring", "vambraces", "sacred girdle"),
            (item, hero) -> {
                item.setProtection(Dice.getNextNumber(1, 6 + hero.getLevel()));
                item.setDamage(Dice.getNextNumber(1, hero.getLevel())); // Assuming armor can have damage.
            },
            item -> "Protection: " + item.getProtection() + ", Damage: " + item.getDamage() // Display logic for armor
    );

    private final List<String> names;
    private final BiConsumer<ItemBase, HeroTemplate> attributeInitializer;
    private final Function<ItemBase, String> attributeDisplay;

    ItemType(List<String> names, BiConsumer<ItemBase, HeroTemplate> attributeInitializer, Function<ItemBase, String> attributeDisplay) {
        this.names = names;
        this.attributeInitializer = attributeInitializer;
        this.attributeDisplay = attributeDisplay;
    }

    public String getRandomName() {
        Random rand = new Random();
        return names.get(rand.nextInt(names.size()));
    }

    public ItemBase createItem(HeroTemplate hero) {
        String name = getRandomName();
        ItemBase item = new ItemBase(this, getAttributeDisplay(new ItemBase(this, "", 0, 0, 0, name)), 0, 0, 0, name);
        initializeAttributes(item, hero);
        item.setDescription(getAttributeDisplay(item)); // Set the description after initializing attributes
        return item;
    }

    public void initializeAttributes(ItemBase item, HeroTemplate hero) {
        attributeInitializer.accept(item, hero);
    }

    public String getAttributeDisplay(ItemBase item) {
        return attributeDisplay.apply(item);
    }
}
