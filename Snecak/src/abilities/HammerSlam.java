package abilities;

import hero.HeroTemplate;
import monster.Dice;
import monster.MonsterBase;
import players.Player;

import java.util.List;

public class HammerSlam implements AreaAbilityTemplate {

    private String name = "HammerSlam";
    private int totalDamage;
    private int manaCost;
    private boolean unlocked;

    public HammerSlam() {

    }

    @Override
    public void castAreaEffect(HeroTemplate hero, List<MonsterBase> monsters, Player player) {
        System.out.println("Used " + getName() + "!");
        int damage = Dice.getNextNumber(2, 2 + (hero.getLevel() * 3 ));
        setDamage(damage);
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
        return false;
    }

    @Override
    public void setUnlocked(boolean unlocked) {

    }


}
