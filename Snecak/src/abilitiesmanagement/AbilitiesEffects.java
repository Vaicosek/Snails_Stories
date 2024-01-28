package abilitiesmanagement;

import abilities.AbilityBase;
import hero.Hero;

import java.util.List;

public class AbilitiesEffects {
    private List<AbilityBase> abilities;

    public AbilitiesEffects(List<AbilityBase> abilities) {
        this.abilities = abilities;
    }

    public void applyPassiveEffects(Hero hero) {
        for (AbilityBase ability : abilities) {
            if (ability.isUnlocked()) {
                ability.applyPassiveEffect(hero);
            }
        }
    }

}

