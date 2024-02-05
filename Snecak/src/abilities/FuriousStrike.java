package abilities;

import hero.HeroTemplate;
import monster.Dice;
import monster.MonsterBase;

public class FuriousStrike extends AbilityBase {
    public FuriousStrike() {
        setName("FuriousStrike");
    }

    public void use (HeroTemplate hero, MonsterBase monster) {
            int damage =(hero.getAttack()/100) * 10 + hero.getAttack();
            setDamage(damage);
            hero.setHP(hero.getHP() -  (hero.getLevel()*100/10));
            System.out.println("Used " + getName() + "!");

    }

}

