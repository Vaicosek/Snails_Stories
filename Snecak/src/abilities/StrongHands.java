package abilities;

import hero.HeroTemplate;
import itemshandling.Inventory;
import monster.MonsterBase;

import java.util.List;

public class StrongHands extends AbilityBase {
    private Inventory inventory;

    public StrongHands() {
        setName("StrongHands");
        setDamage(0); // Set the initial damage value as needed
        setManaCost(0); // Set the mana cost as needed
        this.inventory = new Inventory(); // Initialize the inventory
    }

    public void use(HeroTemplate hero, List<MonsterBase> monsters) {

        inventory.openInventoryMenu(hero);
    }
}
