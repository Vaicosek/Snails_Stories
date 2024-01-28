package abilities;

import hero.HeroTemplate;
import monster.MonsterBase;

import java.util.List;

public class Healing extends AbilityBase {
    private HeroTemplate hero;
    private static final int MANA_COST = 30;

    public Healing() {
       setName("Healing");

    }

    public void use(HeroTemplate hero, List<MonsterBase> monsters) {
        int currentMana = hero.getMana();
        if (currentMana >= MANA_COST) {
            int maxHealth = hero.getHP();
            double healAmount = maxHealth * 0.1;
            int currentHealth = hero.getHP();
            int newHealth = Math.min(currentHealth + (int) healAmount, maxHealth);
            hero.setHP(newHealth);

            hero.setMana(currentMana - MANA_COST);
            System.out.println("Healed for " + (int) healAmount + " HP. Current HP: " + newHealth);
        } else {
            System.out.println("Not enough mana to use Healing.");
        }
    }
}

