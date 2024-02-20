package abilities;

import hero.Hero;

public interface BonusAbilityTemplate extends AbilityTemplate {
    void applyBonus(Hero hero);
}
