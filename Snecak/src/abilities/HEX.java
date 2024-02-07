package abilities;

import hero.HeroTemplate;
import monster.MonsterBase;

public class HEX extends AbilityBase {
    private static final int ENTANGLE_ROUNDS = 2;

    public HEX( ) {
        setName ( "HEX" );
        setManaCost(50);
    }

    public void use(HeroTemplate hero, MonsterBase monster) {

        applyEntangleEffect(monster);

    }

    private void applyEntangleEffect(MonsterBase targetMonster) {
        // Apply taunt effect to the target monster
        targetMonster.setEntangled(true, ENTANGLE_ROUNDS);
    }

    public boolean isEntangleAbility() {
        return true;
    }
}
