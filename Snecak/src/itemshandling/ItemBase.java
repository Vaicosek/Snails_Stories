package itemshandling;

import monster.Dice;

import java.util.ArrayList;

public abstract class ItemBase {

    private ItemType itemType;
    private String name;
    private int bonus;

    public ItemBase(ItemType itemType) {
        this.itemType = itemType;
        initializeName();
        initializeBonus();
    }

    public ItemType getItemType() {
        return itemType;
    }

    public String getName() {
        return name;
    }

    public int getBonus() {
        return bonus;
    }

    @Override
    public String toString() {
        return name + " (+" + bonus + ")";
    }

    protected abstract void initializeName();

    protected abstract void initializeBonus();

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

