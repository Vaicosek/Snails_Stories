package abilities;

import hero.HeroTemplate;
import monster.Dice;
import monster.MonsterBase;

public class CriticalHit extends AbilityBase {
    public CriticalHit() {
        setName("CriticalHit");

    }

    public void use (HeroTemplate hero, MonsterBase currentMonster){
        int percent = Dice.getNextNumber(0, 100);
        if (percent>= 33) {
            int damage =(hero.getAttack()/100) * 20 + hero.getAttack();
            setDamage(damage);
            System.out.println("Used " + getName() + "!");
        }
    }
    }

