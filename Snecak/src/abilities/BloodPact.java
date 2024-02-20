package abilities;

import hero.HeroTemplate;
import monster.MonsterBase;

import java.util.List;
import java.util.Scanner;

public class BloodPact implements NormalAbilityTemplate {
    private String name = "Blood Pact";
    private int damage;
    private int manaCost;
    private boolean unlocked;

    public BloodPact(String name, int manaCost) {
        this.name = name;
        this.manaCost = manaCost;
    }

    public void cast(HeroTemplate hero, MonsterBase monster) {
        Scanner scanner = new Scanner(System.in);

        int currentHp = hero.getHP();
        int attackDamage = hero.getAttack();

        System.out.println("Enter the amount of HP you want to sacrifice:");
        int hpToSacrifice = scanner.nextInt();

        if (hpToSacrifice > currentHp) {
            System.out.println("You don't have enough HP for this sacrifice.");
        } else {
            int increasedDamage = hpToSacrifice * 3; // For every 1 HP, increase attack by 3
            setDamage(increasedDamage + attackDamage);

            int remainingHp = currentHp - hpToSacrifice;
            hero.setHP(remainingHp);

            System.out.println("Sacrificed " + hpToSacrifice + " HP to increase attack by " + increasedDamage + "!");
        }
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public void setDamage(int totalDamage) {

    }

    @Override
    public int getManaCost() {
        return 0;
    }

    @Override
    public void setManaCost(int manaCost) {

    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean isUnlocked() {
        return false;
    }

    @Override
    public void setUnlocked(boolean unlocked) {

    }

}


