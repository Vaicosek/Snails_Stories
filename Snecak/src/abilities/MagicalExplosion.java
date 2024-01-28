package abilities;

import hero.HeroTemplate;
import monster.MonsterBase;

public class MagicalExplosion extends AbilityBase {
    public MagicalExplosion() {
        setName("MagicalExplosion");
        setManaCost(0);
    }

    public void use(HeroTemplate hero, MonsterBase monster) {
        int currentMana = hero.getMana();
        int manaCost = 10;

        if (currentMana >= manaCost) {
            int totalDamage = currentMana / 10; // Calculate damage based on mana spent
            System.out.println("Used " + getName() + "! Dealt " + totalDamage + " damage to all monsters.");


            int manaSpent = totalDamage * 10;
            hero.setMana(currentMana - manaSpent);

            setDamage(totalDamage);

        }
    }

    public boolean isSpellAreaEffect() {
        return true;
    }
}
