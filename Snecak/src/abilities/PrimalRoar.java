package abilities;

import hero.HeroTemplate;
import monster.Dice;
import monster.MonsterBase;

public class PrimalRoar extends HeroAbility {
    private static final int TAUNT_ROUNDS = 1;
    public PrimalRoar() {
        setName("PrimalRoar");
        setManaCost(33);
    }
    public void use(HeroTemplate hero, MonsterBase monster) {
        int damage = Dice.getNextNumber(4,4 + hero.getLevel() * 3);
        setDamage(damage);
        applyTauntEffect(monster);

    }
    private void applyTauntEffect(MonsterBase targetMonster) {
        // Apply taunt effect to the target monster
        targetMonster.setTaunted(true, TAUNT_ROUNDS);

        // Print a message indicating that the hero is now the target of the specified monster
        System.out.println(targetMonster.getName() + " is now targeting the hero!");
    }
    public boolean isTauntAbility() {
        return true;
    }
}