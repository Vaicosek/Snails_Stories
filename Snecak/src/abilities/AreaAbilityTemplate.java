package abilities;

import hero.HeroTemplate;
import monster.MonsterBase;
import players.Player;

import java.util.List;

public interface AreaAbilityTemplate extends AbilityTemplate {

        void castAreaEffect(HeroTemplate hero, List<MonsterBase> monsters, Player player);

}
