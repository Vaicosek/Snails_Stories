package abilities;


import hero.Hero;
import hero.HeroTemplate;

public class MagicalPower implements BonusAbilityTemplate {
    private String name = "MagicalPower";
    private int totalDamage;
    private int manaCost = 0;
    private boolean unlocked;

    public MagicalPower() {
         setName ("MagicalPower");
    }


    @Override
    public void applyBonus(Hero hero) {
        hero.SetMana(100);
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

