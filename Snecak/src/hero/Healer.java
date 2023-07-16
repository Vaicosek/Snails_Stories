package hero;


import abilities.HeroAbility;

import java.util.List;

public class Healer extends Hero {


    public Healer(int XP, int level, int HP, List<HeroAbility> healerAbilities) {
        super(XP, level, HP, healerAbilities);
        this.name = "Healer";
    }

}
