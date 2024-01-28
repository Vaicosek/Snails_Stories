package abilities;

import hero.HeroTemplate;
import monster.MonsterBase;



public class Taunt extends AbilityBase {

    private static final int TAUNT_ROUNDS = 2;

    public Taunt() {
        setName("Taunt");
    }


    public void use(HeroTemplate hero, MonsterBase monster) {

        applyTauntEffect(monster);

    }

    private void applyTauntEffect(MonsterBase targetMonster) {
        // Apply taunt effect to the target monster
        targetMonster.setTaunted(true, TAUNT_ROUNDS);

        // Print a message indicating that the hero is now the target of the specified monster
        System.out.println(targetMonster.getName() + " is now targeting the hero!");
    }

    public boolean isTauntAbility() {
        return true; // Indicate that this ability is a taunt ability
    }
}
