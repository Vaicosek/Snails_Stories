package abilities;

import hero.HeroTemplate;
import monster.Dice;

public class Bite extends AbilityBase {

        public Bite() {
            setName("Bite");
        }

        @Override
        public void calculateDamage(HeroTemplate hero, int monsterHp) {
            int baseDamage = 8 + hero.getLevel() * 5;

            // Calculate total damage
            int totalDamage = baseDamage;

            // Update the damage value of the ability
            setDamage(totalDamage);
        }
    }





