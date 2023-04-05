package itemshandling;

import monster.Dice;

import java.util.ArrayList;

public abstract class ItemBase {

    private final ItemType itemType;

    private static final int CONSUMABLE = 1;
    private static final int WEAPON = 2;
    private static final int ARMOR = 3;

    public int type;

    private int damage;
    private int health;
    private int protection;
    private String name;


    public ItemBase(ItemType itemType) {
        this.itemType = itemType;
        initializeName();

    }

    public ItemType getItemType() {
        return itemType;
    }

    public String getName() {
        return name;
    }

    protected abstract void initializeName();

    protected ArrayList<String> ItemNames;

    String getRandomItemName() {
        int i;
        try {
            i = Dice.getNextNumber(1, ItemNames.size() - 1);
            return ItemNames.get(i);
        } catch (Exception e) {
            return "";
        }

    }
}

