package abilities;

import hero.HeroTemplate;
import monster.Dice;
import monster.MonsterBase;

public class HolyNova extends HeroAbility {
    private static final double[] TIER_MULTIPLIERS = {0.05, 0.08, 0.12, 0.18, 0.25};

    public HolyNova() {
        setName("HolyNova");
        setManaCost(100); // Adjust the mana cost accordingly
    }

    public void use(HeroTemplate hero, MonsterBase monster) {
        int currentMana = hero.getMana();
        int manaCost = getManaCost();

        if (currentMana >= manaCost) {
            hero.setMana(currentMana - manaCost);
            System.out.println("Used " + getName() + "!");

            // Calculate damage based on hero's level and monster's tier
            int heroDamage = Dice.getNextNumber(5, (10 + (hero.getLevel() * 5)));
            int tier = monster.getTier();
            double multiplier = TIER_MULTIPLIERS[tier - 1];
            int monsterDamage = (int) (monster.getHP() * multiplier) + (5 * tier);

            int totalDamage = heroDamage + monsterDamage;
            setDamage(totalDamage);

            // Apply damage to all monsters
            monster.takeDamage(totalDamage);
        } else {
            System.out.println("Not enough mana to use " + getName() + " or it's not your turn.");
        }
    }

    public boolean isSpellAreaEffect() {
        return true;
    }
}
