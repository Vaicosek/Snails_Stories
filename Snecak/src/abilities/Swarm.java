package abilities;

import hero.HeroTemplate;
import monster.Dice;
import monster.MonsterBase;

public class Swarm extends AbilityBase {

    private int remainingTurns;

    private int turnCounter;

    public Swarm() {
        setName("Swarm");
        setManaCost(50);
        remainingTurns = 3; // Set the initial remaining turns
        turnCounter = 0; // Initialize the turn counter
    }

    public void use(HeroTemplate hero, MonsterBase monster) {
        int currentMana = hero.getMana();
        int manaCost = getManaCost();

        if (currentMana >= manaCost) {
            hero.setMana(currentMana - manaCost);
            System.out.println("Used " + getName() + "!");

            // Calculate damage based on hero's level and monster's tier
            int Damage = Dice.getNextNumber(0, (10 + (hero.getLevel() * 5)));
            setDamage(Damage);
            applyActiveEffect(monster);
        } else {
            System.out.println("Not enough mana to use " + getName() + " or it's not your turn.");
        }
    }
    public void applyActiveEffect(MonsterBase monster) {
        if (remainingTurns > 0) {
            int damagePerTurn = (monster.getHP() / 100);

            monster.takeDamage(damagePerTurn);

            remainingTurns--;

            if (remainingTurns == 0) {
                System.out.println(getName() + " effect has ended.");
            }
        } else {
            System.out.println(getName() + " effect has ended.");
        }
    }
    public boolean isSpellAreaEffect() {
        return true;
    }
}