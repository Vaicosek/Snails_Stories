package hero;

import abilities.AbilityBase;
import abilities.AbilityTemplate;

import java.util.List;

public class Wizard extends Hero {
    public Wizard(int XP, int level, int HP, List<AbilityTemplate> wizardAbilities) {
        super(XP, level, HP, wizardAbilities);
        this.name = "Wizard";
    }
}


