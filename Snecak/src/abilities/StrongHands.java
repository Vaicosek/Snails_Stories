package abilities;

import hero.HeroTemplate;
import itemshandling.Inventory;
import monster.MonsterBase;
import players.Player;

import java.util.List;

public class StrongHands implements NormalAbilityTemplate {
    private Inventory inventory;

    private String name = "StrongHands";
    private int totalDamage;
    private int manaCost = 60;
    private boolean unlocked;

    public StrongHands() {

        this.inventory = new Inventory();
    }

    public void useStrongHands(HeroTemplate hero, Player player) {

        inventory.openInventoryMenu(hero,player);
    }


}
