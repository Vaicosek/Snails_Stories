package abilities;

import hero.HeroTemplate;
import monster.Dice;
import monster.MonsterBase;

public class HolyFire extends HeroAbility {

    private static final double[] TIER_MULTIPLIERS = {0.05, 0.08, 0.12, 0.18, 0.25};
    public HolyFire() {
        setName("HolyFire");
        setManaCost(50);
    }

    public void use(HeroTemplate hero, MonsterBase monster) {
        int currentMana = hero.getMana();
        int manaCost = getManaCost(); // Get the mana cost from the superclass

        if (currentMana >= manaCost) {
            hero.setMana(currentMana - manaCost);
            System.out.println("Used " + getName() + "!");
            int damage = calculateDamage(hero, monster);
            setDamage(damage);
            monster.takeDamage(damage);

        } else {
            System.out.println("Not enough mana to use " + getName() + " or it's not your turn.");
        }
    }

    private int calculateDamage(HeroTemplate hero, MonsterBase monster) {
        int maxHP = monster.getHP(); // Assuming you have a method to get the max HP of a monster
        int tier = monster.getTier();
        double multiplier = TIER_MULTIPLIERS[tier - 1]; // Get the multiplier based on the monster's tier
        return (int) (maxHP * multiplier) + (5* monster.getTier());
    }
}
