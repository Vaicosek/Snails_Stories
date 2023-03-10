package players;

import itemshandling.ItemBase;
import itemshandling.ItemBase;

import java.util.List;

public interface PlayerTemplate {
    String getName();

    void setName(String name);

    int getHealth();

    void setHealth(int health);

    int getAttack();

    void setAttack(int attack);

    int getDefense();

    void setDefense(int defense);

    int getX();

    void setX(int x);

    int getY();

    void setY(int y);

    List<ItemBase> getInventory();

    void addItem(ItemBase item);

    void removeItem(ItemBase item);

    boolean hasItem(ItemBase item);

    boolean hasItemByName(String itemName);
}
