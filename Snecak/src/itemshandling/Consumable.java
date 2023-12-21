package itemshandling;

import hero.HeroTemplate;
import monster.Dice;
import players.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class Consumable extends ItemBase {
    public Consumable(int damage, int health, int protection) {
        super(ItemType.CONSUMABLE, damage, health,
                protection);
    }

    public void setRandomHealth(Player player) {
        this.Health = Dice.getNextNumber(0,10 + (player.getHero().getLevel() * 10));
    }
    public void setRandomDamage(Player player) {
        this.Damage = Dice.getNextNumber(0,4 + player.getHero().getLevel());
    }
    @Override
    protected void InitializeName() {
        names= new ArrayList<String>(Arrays.asList(
                // Food items
                "Apple",
                "Banana",
                "Carrot",
                "Chicken leg",
                "Fish",
                "Ham",
                "Pizza",
                "Roast beef",
                "Turkey leg",
                "Watermelon",

                // Drink items
                "Beer",
                "Coffee",
                "Juice",
                "Milk",
                "Soda",
                "Tea",
                "Water",
                "Wine",
                "Hot chocolate",
                "Lemonade",

                // Potion items
                "Potion of healing",
                "Elixir of life",
                "Draught of vitality",
                "Tonic of rejuvenation",
                "Remedy of restoration",
                "Salve of mending",
                "Serum of regeneration",
                "Ointment of recovery",
                "Balm of revival",
                "Lotion of renewal",

                // More food items
                "Cheese",
                "Bread",
                "Eggs",
                "Honey",
                "Yogurt",
                "Peanut butter",
                "Jelly",
                "Crackers",
                "Popcorn",
                "Chocolate",

                // More drink items
                "Lime soda",
                "Energy drink",
                "Iced tea",
                "Root beer",
                "Ginger ale",
                "Fruit punch",
                "Smoothie",
                "Iced coffee",
                "Hot tea",
                "Coconut water"
        ));

    }


}
