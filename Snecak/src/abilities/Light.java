package abilities;

import hero.HeroTemplate;
import monster.Dice;
import monster.MonsterBase;

public class Light extends HeroAbility {
    private int remainingTurns;
    public Light() {
        setName("Light");
        setManaCost(50);
        remainingTurns = 3;
    }
    public void use(HeroTemplate hero, MonsterBase monster, int currentTurn) {
        int currentMana = hero.getMana();
        int manaCost = getManaCost();

        if (currentMana >= manaCost) {
            hero.setMana(currentMana - manaCost);
            System.out.println("Used " + getName() + "!");
            applyActiveEffect(monster);

        } else {
            System.out.println("Not enough mana to use " + getName() + " or it's not your turn.");
        }
    }

    public void applyActiveEffect(MonsterBase monster) {
        if (remainingTurns > 0) {

            int damageReduction = Dice.getNextNumber(65,100)/100;
            monster.reduceAttack(damageReduction);

            remainingTurns--;

            if (remainingTurns == 0) {
                System.out.println(getName() + " effect has ended.");
            }
        } else {
            System.out.println(getName() + " effect has ended.");
        }
    }
}
