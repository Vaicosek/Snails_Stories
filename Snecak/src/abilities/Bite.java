package abilities;
import hero.HeroTemplate;
import monster.MonsterBase;


public class Bite implements NormalAbilityTemplate {

    private int totalDamage;
    private String name = "Bite";
    private int manaCost = 30;
    private boolean unlocked;

        public Bite() {

        }

    @Override
    public void cast(HeroTemplate hero, MonsterBase monster) {

        // Calculate total damage
        totalDamage = 8 + hero.getLevel() * 5;

        // Update the damage value of the ability
        setDamage(totalDamage);
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
        return 30;
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





