package abilities;

import hero.HeroTemplate;
import monster.MonsterBase;
import players.Player;

public class WeaponBlast extends AbilityBase {
    public WeaponBlast() {
        setName("WeaponBlast");
    }
    public void use(HeroTemplate hero, MonsterBase monster, Player player) {
        int damage = hero.getEquippedWeaponDamage() * 2 + monster.getHP() / 10;
        setDamage(damage);
        hero.destroyWeapon(hero.getEquippedWeapon(), player);
    }


}
