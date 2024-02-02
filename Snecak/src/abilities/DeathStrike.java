package abilities;

import hero.HeroTemplate;
import monster.MonsterBase;

public class DeathStrike extends AbilityBase {

    public DeathStrike() {
        setName("DeathStrike");

    }


    public void use(HeroTemplate hero, MonsterBase monster) {
        int lowHP = 100 + (hero.getLevel() * 100)/20;
        int damage = monster.getHP()/20;
        if (hero.getHP() < lowHP){
            setDamage(damage);
        }
    }
}
