package game.itemshandling;

import lombok.Getter;
import lombok.Setter;
@Getter
public class ItemBase {

    @Getter
    protected final ItemType itemType;
    private final String description;
    @Setter
    protected int damage;
    @Setter
    protected int health;
    @Setter
    protected int protection;
    @Setter
    protected String name;


    public ItemBase(ItemType itemType, String description, int damage, int health, int protection, String name) {
        this.itemType = itemType;
        this.description = description;
        this.damage = damage;
        this.health = health;
        this.protection = protection;
        this.name = name;
    }

    public String getDescription() {
        return (String.format("%s: Damage=%d, Health=%d, Protection=%d",name, damage, health, protection));
    }
}
