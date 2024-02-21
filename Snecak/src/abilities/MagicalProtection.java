package abilities;

import hero.HeroTemplate;
import monster.MonsterBase;

import java.util.List;
import java.util.Scanner;

public class MagicalProtection implements NormalAbilityTemplate {

    private String name = "MagicalProtection";
    private int totalDamage;
    private int manaCost = 0;
    private boolean unlocked;

    public MagicalProtection() {
    }

    @Override
    public void cast(HeroTemplate hero, MonsterBase monster) {
        Scanner scanner = new Scanner(System.in);

        int currentHp = hero.getHP();
        int mana = hero.getMana();

        System.out.println("Enter the amount of mana you want to convert to temporary HP (maximum 100):");
        int manaToConvert = scanner.nextInt();
        int maxAllowedBonus = 100;

        if (manaToConvert > mana) {
            System.out.println("You don't have enough mana for this conversion.");
        } else if (manaToConvert > maxAllowedBonus) {
            System.out.println("You can only convert up to 100 mana to temporary HP.");
            manaToConvert = maxAllowedBonus;
        } else {
            int temporaryHp = currentHp + manaToConvert;

            // Ensure temporary HP doesn't exceed the maximum allowed
            hero.setHP(Math.min(temporaryHp, maxAllowedBonus));

            int remainingMana = mana - manaToConvert;
            hero.setMana(remainingMana);

            System.out.println("Converted " + manaToConvert + " mana to temporary HP using " + getName() + "!");
        }
    }

    @Override
    public int getDamage() {
        return totalDamage;
    }

    @Override
    public void setDamage(int totalDamage) {

    }

    @Override
    public int getManaCost() {
        return manaCost;
    }

    @Override
    public void setManaCost(int manaCost) {

    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isUnlocked() {
        return false;
    }

    @Override
    public void setUnlocked(boolean unlocked) {

    }
}

