package game.abilities;

import game.hero.HeroTemplate;
import game.monster.MonsterBase;
import game.players.Player; // Import Player if you need access to the Player object

import java.util.List;

public class ArmorBlast implements AreaAbilityTemplate {

    private String name = "ArmorBlast";
    private int totalDamage;
    private int manaCost = 30;
    private boolean unlocked = false;

    public ArmorBlast() {

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
    public void setUnlocked() {
         unlocked = true;
    }


    @Override
    public void castAreaEffect(HeroTemplate hero, List<MonsterBase> monsters, Player player) {
        // Calculate the base damage based on the hero's equipped armor protection
        int baseDamage = hero.getEquippedArmorProtection() * 3;

        // Apply the effect to each monster
        for (MonsterBase monster : monsters) {
            int damage = baseDamage + monster.getHP() / 10;
            // Use the takeDamage method to apply damage
            setDamage(damage);
           ;
        }

        // Destroy the hero's equipped armor after the ability is used
        hero.destroyArmor(hero.getEquippedArmor(), player);
    }

    }

