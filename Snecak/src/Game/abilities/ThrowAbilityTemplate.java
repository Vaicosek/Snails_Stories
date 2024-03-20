package Game.abilities;

import Game.hero.HeroTemplate;
import Game.itemshandling.Inventory;
import Game.monster.MonsterBase;
import Game.players.Player;

public interface ThrowAbilityTemplate extends AbilityTemplate {
    void cast(Player player, HeroTemplate hero, MonsterBase monster);
    void throwItem (Inventory inventory, MonsterBase monster);
}
