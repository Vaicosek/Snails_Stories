package game.abilities;

import game.hero.HeroTemplate;
import game.monster.MonsterBase;
import game.players.Player;

import java.util.List;

public interface AreaAbilityTemplate extends AbilityTemplate {

        void castAreaEffect(HeroTemplate hero, List<MonsterBase> monsters, Player player);

}
