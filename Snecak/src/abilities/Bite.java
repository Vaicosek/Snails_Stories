package abilities;

import hero.HeroTemplate;
import monster.Dice;
import monster.MonsterBase;

public class Bite extends HeroAbility {
    public Bite() {
        setName("Bite");
        setManaCost(50);

    }

    public void use(HeroTemplate hero, MonsterBase monster) {
        int currentMana = hero.getMana();
        int manaCost = getManaCost();

        System.out.println("Current Mana: " + currentMana);
        System.out.println("Mana Cost: " + manaCost);

        if (currentMana >= manaCost) {
            hero.setMana(currentMana - manaCost);
            System.out.println("Used " + getName() + "!");

            int scaledamage = (int) (monster.getHP() * 0.1);
            System.out.println("Scaled Damage: " + scaledamage);

            int damage = Dice.getNextNumber(3, 3 + (hero.getLevel() * 5) + scaledamage);
            System.out.println("Calculated Damage: " + damage);

            setDamage(damage);
        } else {
            System.out.println("Not enough mana to use " + getName() + " or it's not your turn.");
        }
    }
}

