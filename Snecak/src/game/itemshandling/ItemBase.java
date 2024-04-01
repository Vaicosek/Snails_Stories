package game.itemshandling;
import lombok.Getter;
import lombok.Setter;
@Getter
public class ItemBase {

    @Getter
    protected final ItemType itemType;
    @Setter
    protected int damage;
    @Setter
    protected int health;
    @Setter
    protected int protection;
    @Setter
    protected String name;


    public ItemBase(ItemType itemType, int damage, int health, int protection, String name) {
        this.itemType = itemType;
        this.damage = damage;
        this.health = health;
        this.protection = protection;
        this.name = name;
    }

}
