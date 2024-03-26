package game.abilities;

import game.hero.Hero;

public interface BonusAbilityTemplate extends AbilityTemplate {
    void applyBonus(Hero hero);
}
