package abilities;

import hero.HeroTemplate;
import monster.MonsterBase;
import players.Player; // Import Player if you need access to the Player object

import java.util.List;

public class ArmorBlast implements AreaAbilityTemplate {

    private int manaCost;
    private int totalDamage;
    private String name;
    private boolean unlocked = false;

    public ArmorBlast() {
        setName("ArmorBlast");
    }

    @Override
    public int getDamage() {
        return totalDamage;
    }

    @Override
    public void setDamage(int totalDamage) {
        this.totalDamage = totalDamage;
    }

    @Override
    public int getManaCost() {
        return manaCost;
    }

    @Override
    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    // Assuming the Player object needs to be passed to use certain methods like destroyArmor
    // If Player is not needed, you can remove it from the method parameters
    @Override
    public void castAreaEffect(HeroTemplate hero, List<MonsterBase> monsters, Player player) {
        // Calculate the base damage based on the hero's equipped armor protection
        int baseDamage = hero.getEquippedArmorProtection() * 3;

        // Apply the effect to each monster
        for (MonsterBase monster : monsters) {
            int damage = baseDamage + monster.getHP() / 10;
            // Use the takeDamage method to apply damage
            monster.takeDamage(damage);
            System.out.printf("%s took %d damage from ArmorBlast.\n", monster.getName(), damage);
        }

        // Destroy the hero's equipped armor after the ability is used
        hero.destroyArmor(hero.getEquippedArmor(), player);
    }

    }

