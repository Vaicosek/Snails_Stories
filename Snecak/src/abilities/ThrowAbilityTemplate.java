package abilities;

import hero.HeroTemplate;
import itemshandling.Inventory;
import monster.MonsterBase;
import players.Player;

public interface ThrowAbilityTemplate extends AbilityTemplate {
    void cast(Player player, HeroTemplate hero, MonsterBase monster);
    void throwItem (Inventory inventory, MonsterBase monster);
}
