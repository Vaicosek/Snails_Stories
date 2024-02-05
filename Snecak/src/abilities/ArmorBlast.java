package abilities;

import hero.HeroTemplate;
import itemshandling.Armor;
import monster.MonsterBase;
import players.Player;

public class ArmorBlast extends AbilityBase {
    public ArmorBlast() {
        setName("ArmorBlast");

    }

    public void use(HeroTemplate hero, MonsterBase monster, Player player) {
        int damage = hero.getEquippedArmorProtection() * 3 + monster.getHP() / 10;
        setDamage(damage);
        hero.destroyArmor(hero.getEquippedArmor(), player);
    }
}

