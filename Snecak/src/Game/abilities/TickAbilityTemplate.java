package Game.abilities;

import Game.hero.HeroTemplate;
import Game.monster.MonsterBase;
import Game.players.Player;

import java.util.List;

public interface TickAbilityTemplate extends AbilityTemplate {

        void cast(HeroTemplate hero, MonsterBase monster,List<MonsterBase> monsters);
        void onTick(Player player,  MonsterBase monster,List<MonsterBase> monsters,  int turnCounter);
        int getRemainingTurns();
        void setRemainingTurns(int turns);
        boolean isEffectActive();
    }


