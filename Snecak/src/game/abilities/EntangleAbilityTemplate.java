package game.abilities;

import game.hero.HeroTemplate;
import game.monster.MonsterBase;

public interface EntangleAbilityTemplate extends AbilityTemplate {
    void applyEntangle(HeroTemplate hero, MonsterBase monster);
    int getEntangleDuration(); // Adjust to return int
    void setEntangleDuration(int duration);
}

