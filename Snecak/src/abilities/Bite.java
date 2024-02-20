package abilities;

import hero.Hero;
import hero.HeroTemplate;
import monster.MonsterBase;

import java.util.List;

public class Bite extends AbilityBase implements AbilityBaseInterface {

    int totalDamage =0;
        public Bite() {

        }


        public void calculateDamage(HeroTemplate hero, int monsterHp) {
            int baseDamage = 8 + hero.getLevel() * 5;

            // Calculate total damage
          totalDamage = baseDamage;

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
        return 0;
    }

    @Override
    public void setManaCost(int manaCost) {

    }

    @Override
    public boolean isUnlocked() {
        return false;
    }

    @Override
    public void setUnlocked(boolean unlocked) {

    }

    @Override
    public void use(HeroTemplate hero, MonsterBase currentMonster) {

    }

    @Override
    public void useAreaSpell(HeroTemplate hero, List<MonsterBase> monsters) {

    }

    @Override
    public void applyPassiveEffect(Hero hero) {

    }

    @Override
    public void applyActiveEffect(Hero hero) {

    }

    @Override
    public void summonPlayerAlliedEntity(HeroTemplate hero) {

    }
}





