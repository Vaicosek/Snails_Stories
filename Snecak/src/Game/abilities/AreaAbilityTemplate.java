package Game.abilities;

import Game.hero.HeroTemplate;
import Game.monster.MonsterBase;
import Game.players.Player;

import java.util.List;

public interface AreaAbilityTemplate extends AbilityTemplate {

        void castAreaEffect(HeroTemplate hero, List<MonsterBase> monsters, Player player);

}
