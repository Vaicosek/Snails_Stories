package game.abilities;

import game.hero.HeroTemplate;
import game.itemshandling.Inventory;
import game.monster.MonsterBase;
import game.players.Player;

public interface ThrowAbilityTemplate extends AbilityTemplate {
    void cast(Player player, HeroTemplate hero, MonsterBase monster);
    void throwItem (Inventory inventory, MonsterBase monster);
}
