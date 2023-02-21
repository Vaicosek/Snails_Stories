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
    List<Item> getInventory();
    void addItem(Item item);
    void removeItem(Item item);
    boolean hasItem(Item item);
    boolean hasItemByName(String itemName);
}
