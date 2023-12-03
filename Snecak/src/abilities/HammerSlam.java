package abilities;

import hero.HeroTemplate;
import monster.Dice;
import monster.MonsterBase;

public class HammerSlam extends HeroAbility {
    public HammerSlam() {
        setName("HammerSlam");
    }
    public void use(HeroTemplate hero, MonsterBase monster) {
            System.out.println("Used " + getName() + "!");
            int damage = Dice.getNextNumber(2, 2 + (hero.getLevel() * 3 ));
            setDamage(damage);
    }

    public boolean isSpellAreaEffect() {
        return true;
    }
}
