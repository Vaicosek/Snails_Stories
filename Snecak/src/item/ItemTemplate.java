package item;

public interface ItemTemplate {
    String getName();
    void setName(String name);
    int getQuantity();
    void setQuantity(int quantity);
    ItemType getType();
    void setType(ItemType type);
}
