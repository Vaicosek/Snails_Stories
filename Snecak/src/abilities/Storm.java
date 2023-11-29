package abilities;

import hero.HeroTemplate;
import monster.Dice;
import monster.MonsterBase;

public class Storm extends HeroAbility {
    public Storm() {
        setName("Storm");
        setManaCost(60);
    }

    public void use(HeroTemplate hero, MonsterBase monster) {

        int currentMana = hero.getMana();
        int manaCost = getManaCost(); // Get the mana cost from the superclass

        if (currentMana >= manaCost) {
            hero.setMana(currentMana - manaCost);
            System.out.println("Used " + getName() + "!");
            int damage = Dice.getNextNumber(0, hero.getLevel() * 9);
            setDamage(damage);


        } else {
            System.out.println("Not enough mana to use " + getName() + " or it's not your turn.");
        }
    }

    public boolean isSpellAreaEffect() {
        return true;
    }
}
