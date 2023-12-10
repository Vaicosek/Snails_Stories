package abilities;

import hero.HeroTemplate;
import monster.Dice;
import monster.MonsterBase;

public class DivineSword extends HeroAbility {
    public DivineSword() {
        setName("DivineSword");
        setManaCost(50);
    }

    public void use (HeroTemplate hero, MonsterBase monster) {
        int currentMana = hero.getMana();
        int manaCost = getManaCost();
        if (currentMana >= manaCost) {
            hero.setMana(currentMana - manaCost);
            System.out.println("Used " + getName() + "!");
            int damage = hero.getAttack() + Dice.getNextNumber(1, hero.getLevel() * 8);
            setDamage(damage);
        } else {
            System.out.println("Not enough mana to use " + getName() + " or it's not your turn.");
        }
    }
}
