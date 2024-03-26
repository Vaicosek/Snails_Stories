package game.abilities;

import game.hero.HeroTemplate;
import game.monster.MonsterBase;

public interface NormalAbilityTemplate extends AbilityTemplate {
        void cast(HeroTemplate hero, MonsterBase monster);

}
