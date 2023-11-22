package abilities;

import hero.HeroTemplate;
import monster.MonsterBase;

public class Inquisition extends HeroAbility {
    public Inquisition() {
        setName("Inquisition");
        setDamage(0);
        setManaCost(0);
    }

    public void use(HeroTemplate hero, MonsterBase monster, int currentTurn) {
        int additionalDamage = 0;
        int monsterTier = monster.getTier();

        if (monsterTier == 1 || monsterTier == 2) {
            additionalDamage = 15; // Additional damage for tier 1 and 2 monsters
        }

        int totalDamage = getDamage() + additionalDamage;

        // Apply the total damage to the monster
        monster.takeDamage(totalDamage);

    }
}
