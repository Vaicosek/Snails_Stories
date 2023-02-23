import itemshandling.Item;
import itemshandling.ItemType;

import java.util.ArrayList;
import java.util.List;

public class StoryTeller {
    public static Item item;
    private List<Player> players = new ArrayList<>();

    public void AddItemToPlayerInventory(Player player, String name, int quantity, ItemType type) {
        Item item = new Item(name, quantity, type);
        player.getInventory().add(item);
    }

    public void removeItemFromPlayerInventory(Player player, Item item) {
        player.getInventory().remove(item);
    }


}
