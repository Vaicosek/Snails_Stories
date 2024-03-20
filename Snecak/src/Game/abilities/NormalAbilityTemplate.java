package Game.abilities;

import Game.hero.HeroTemplate;
import Game.monster.MonsterBase;

public interface NormalAbilityTemplate extends AbilityTemplate {
        void cast(HeroTemplate hero, MonsterBase monster);

}
