package hero;


import abilities.AbilityBase;

import java.util.List;

public class Druid extends Hero {




    public Druid(int XP, int level, int HP, List<AbilityBase> druidAbilities) {
        super(XP, level, HP, druidAbilities);
        this.name = "Druid";
    }


}
