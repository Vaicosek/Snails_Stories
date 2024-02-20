package hero;


import abilities.AbilityBase;
import abilities.AbilityTemplate;

import java.util.List;

public class Druid extends Hero {




    public Druid(int XP, int level, int HP, List<AbilityTemplate> druidAbilities) {
        super(XP, level, HP, druidAbilities);
        this.name = "Druid";
    }


}
