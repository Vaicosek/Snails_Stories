package abilities;

import hero.HeroTemplate;
import monster.MonsterBase;
import players.Player;

import java.util.List;
import java.util.Scanner;

public class MagicalExplosion implements AreaAbilityTemplate {

    private String name = "MagicalExplosion";
    private int totalDamage;
    private int manaCost = 0;
    private boolean unlocked =false;


    public MagicalExplosion() {
    }

    public void castAreaEffect(HeroTemplate hero, List<MonsterBase> monsters, Player player) {
        Scanner scanner = new Scanner(System.in); // Scanner for user input
        System.out.println("Enter the amount of mana to spend on Magical Explosion:");

        int manaToSpend = scanner.nextInt(); // Read user input
        int currentMana = hero.getMana();

        if (currentMana >= manaToSpend) {
            int damage = manaToSpend /5; // Calculate damage based on mana spent
            System.out.println("Used " + getName() + "! Dealt " + damage + " damage to all monsters.");

            hero.setMana(currentMana - manaToSpend); // Subtract the spent mana

            // Apply damage to all monsters
            for (MonsterBase monster : monsters) {

                monster.takeDamage(damage);
                System.out.printf("%s takes %d damage from %s.\n", monster.getName(), damage, getName());
            }
        } else {
            System.out.println("Not enough mana to use " + getName() + ".");
        }
    }

    public boolean isSpellAreaEffect() {
        return true;
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
        return unlocked;
    }

    @Override
    public void setUnlocked() {
        unlocked = true;
    }
}
