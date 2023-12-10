package abilities;

import hero.HeroTemplate;
import monster.Dice;
import monster.MonsterBase;

public class Rage extends HeroAbility {
    public Rage() {
        setName("Rage");
    }

    public void use(HeroTemplate hero, MonsterBase monster) {
       int damage = Dice.getNextNumber(hero.getAttack() + hero.getAttack(),hero.getAttack() + hero.getAttack() + hero.getAttack());
        setDamage(damage);
            System.out.println("Used " + getName() + "!");
        int toll = hero.getHP() - damage/5;
        hero.setHP(toll);
        }
    }

