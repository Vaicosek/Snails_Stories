package Game.abilities;

import Game.hero.Hero;

public interface BonusAbilityTemplate extends AbilityTemplate {
    void applyBonus(Hero hero);
}
