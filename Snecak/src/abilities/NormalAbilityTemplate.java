package abilities;

import hero.HeroTemplate;
import monster.MonsterBase;

public interface NormalAbilityTemplate extends AbilityTemplate {
        void cast(HeroTemplate hero, MonsterBase monster);

}
