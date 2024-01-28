package hero;

import abilities.AbilityBase;

import java.util.List;

public class Wizard extends Hero {
    public Wizard(int XP, int level, int HP, List<AbilityBase> wizardAbilities) {
        super(XP, level, HP, wizardAbilities);
        this.name = "Wizard";
    }
}


