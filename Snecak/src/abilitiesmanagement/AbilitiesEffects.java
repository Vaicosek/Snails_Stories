package abilitiesmanagement;

import abilities.HeroAbility;
import hero.Hero;

import java.util.List;

public class AbilitiesEffects {
    private List<HeroAbility> abilities;

    public AbilitiesEffects(List<HeroAbility> abilities) {
        this.abilities = abilities;
    }

    public void applyPassiveEffects(Hero hero) {
        for (HeroAbility ability : abilities) {
            if (ability.isUnlocked()) {
                ability.applyPassiveEffect(hero);
            }
        }
    }

    public void applyActiveEffects(Hero hero) {
        for (HeroAbility ability : abilities) {
            if (ability.isUnlocked()) {
                ability.applyActiveEffect(hero);
            }
        }
    }
}
