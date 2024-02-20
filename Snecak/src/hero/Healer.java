package hero;


import abilities.AbilityBase;
import abilities.AbilityTemplate;

import java.util.List;

public class Healer extends Hero {


    public Healer(int XP, int level, int HP, List<AbilityTemplate> healerAbilities) {
        super(XP, level, HP, healerAbilities);
        this.name = "Healer";
    }

}
