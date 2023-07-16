package hero;


import abilities.HeroAbility;

import java.util.List;

public class Druid extends Hero {




    public Druid(int XP, int level, int HP, List<HeroAbility> druidAbilities) {
        super(XP, level, HP, druidAbilities);
        this.name = "Druid";
    }


}
