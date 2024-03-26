package game.abilities;

import game.hero.HeroTemplate;
import game.monster.MonsterBase;
import game.players.Player;

import java.util.List;

public interface TickAbilityTemplate extends AbilityTemplate {

        void cast(HeroTemplate hero, MonsterBase monster,List<MonsterBase> monsters);
        void onTick(Player player,  MonsterBase monster,List<MonsterBase> monsters,  int turnCounter);
        int getRemainingTurns();
        void setRemainingTurns(int turns);
        boolean isEffectActive();
    }


