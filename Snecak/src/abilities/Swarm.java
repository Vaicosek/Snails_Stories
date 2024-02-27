package abilities;

import hero.HeroTemplate;
import monster.Dice;
import monster.MonsterBase;
import players.Player;

import java.util.List;

public class Swarm implements TickAbilityTemplate {
        private String name = "Swarm";
        private int manaCost = 50;
        private boolean unlocked = false;
        private int remainingTurns = 3;
        private int totalDamage;
        public Swarm() {

        }

        @Override
        public void cast(HeroTemplate hero, MonsterBase monster, List<MonsterBase> monsters) {
            int currentMana = hero.getMana();
            int manaCost = getManaCost();

            if (currentMana >= manaCost) {
                for (MonsterBase currentMonster : monsters) {
                    hero.setMana(currentMana - manaCost);
                    int damage = Dice.getNextNumber(0, (10 + (hero.getLevel() * 5)));
                    setDamage(damage);
                    currentMonster.takeDamage(totalDamage);
                }
            } else {
                System.out.println("Not enough mana to use " + getName() + " or it's not your turn.");
            }
        }

    @Override
    public void onTick(Player player, MonsterBase monster, List<MonsterBase> monsters, int turnCounter) {
        if (isEffectActive()) {
            for (MonsterBase currentMonster : monsters) { // Changed variable name to currentMonster
                int damagePerTurn = (currentMonster.getHP() / 100);

                currentMonster.takeDamage(damagePerTurn);
                System.out.printf("Swarm deals %d damage to %s%n", damagePerTurn, currentMonster.getName());
            }
            remainingTurns--;
            if (remainingTurns <= 0) {
                System.out.println("Swarm effect has ended.");
                // Ensure to reset or deactivate the ability effect here if necessary
            }
        }
    }


    @Override
        public int getRemainingTurns() {
            return remainingTurns;
        }

        @Override
        public void setRemainingTurns(int turns) {
            this.remainingTurns = turns;
        }

        @Override
        public boolean isEffectActive() {
            return remainingTurns > 0;
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
            this.unlocked = true;
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
    }

