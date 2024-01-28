package abilities;

import hero.HeroTemplate;
import monster.MonsterBase;

public class MonsterKiller extends AbilityBase {
    public MonsterKiller() {
        setName("MonsterKiller");
    }

    public void use(HeroTemplate hero, MonsterBase monster) {
        int additionalDamage = 0;
        int monsterTier = monster.getTier();

        if (monsterTier == 3 || monsterTier == 4) {
            additionalDamage = 20; // Additional damage for tier 3 and 4 monsters
        }

        int totalDamage = getDamage() + additionalDamage;

        // Apply the total damage to the monster
        monster.takeDamage(totalDamage);


    }
}
