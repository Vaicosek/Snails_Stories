package Game.abilities;

import Game.hero.HeroTemplate;
import Game.monster.MonsterBase;

public interface TauntAbilityTemplate extends AbilityTemplate {
    void applyTaunt(HeroTemplate hero, MonsterBase monster);

    int getTauntDuration(); // Adjust to return int
    void setTauntDuration(int duration); // Include parameter
}
