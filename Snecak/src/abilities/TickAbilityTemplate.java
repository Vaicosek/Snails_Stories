package abilities;

import hero.HeroTemplate;
import monster.MonsterBase;
import players.Player;

import java.util.List;

public interface TickAbilityTemplate extends AbilityTemplate {

        void cast(HeroTemplate hero, MonsterBase monster);
        void onTick(Player player, List<MonsterBase> monsters, int turnCounter);
        int getRemainingTurns();
        void setRemainingTurns(int turns);
        boolean isEffectActive();
    }


