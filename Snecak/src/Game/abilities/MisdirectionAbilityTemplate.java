package Game.abilities;

import Game.hero.HeroTemplate;
import Game.monster.MonsterBase;

import java.util.List;

public interface MisdirectionAbilityTemplate extends AbilityTemplate {
    void misdirect(HeroTemplate hero, MonsterBase monster, List<MonsterBase> monsters);
    int getMisdirectionDuration(); // Correct spelling and return type
    void setMisdirectionDuration(int duration);
}
