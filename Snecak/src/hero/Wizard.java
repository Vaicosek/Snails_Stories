package hero;

import abilities.HeroAbility;

import java.util.List;

public class Wizard extends Hero {
    public Wizard(int XP, int level, int HP, List<HeroAbility> wizardAbilities) {
        super(XP, level, HP, wizardAbilities);
        this.name = "Wizard";
    }
}


