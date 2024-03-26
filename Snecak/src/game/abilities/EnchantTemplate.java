package game.abilities;

import game.hero.HeroTemplate;
import game.itemshandling.ItemBase;
import game.players.Player;

import java.util.List;

public interface EnchantTemplate extends AbilityTemplate {
     void enchant(HeroTemplate hero, Player player);
    void enchantWeapons(HeroTemplate hero, Player player);
    void enchantArmor(HeroTemplate hero, Player player);

    void enchantConsumables(HeroTemplate hero, Player player);

    void printItemList(List<? extends ItemBase> items);

    void handleEnchantInput(HeroTemplate hero, List<? extends ItemBase> items);
}
