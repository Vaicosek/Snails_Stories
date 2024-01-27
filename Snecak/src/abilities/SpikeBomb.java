package abilities;

import hero.HeroTemplate;
import monster.Dice;
import monster.MonsterBase;

public class SpikeBomb extends HeroAbility {
    public SpikeBomb() {
        setName("SpikeBomb");
    }
    public boolean isSpellAreaEffect() {
        return true;
    }

    public void use(HeroTemplate hero, MonsterBase monster) {
        int currentMana = hero.getMana();
        int manaCost = getManaCost();

        if (currentMana >= manaCost) {
            hero.setMana(currentMana - manaCost);
            System.out.println("Used " + getName() + "!");

            // Calculate damage based on hero's level and monster's tier
            int Damage = Dice.getNextNumber(0, (10 + (hero.getLevel() * 5)));
            setDamage(Damage);

        } else {
            System.out.println("Not enough mana to use " + getName() + " or it's not your turn.");
        }
    }

}
