package hero;


import abilities.HeroAbility;

import java.util.List;

public class Assassin extends Hero {



    public Assassin(int XP, int level, int HP, List<HeroAbility> assassinAbilities) {
        super(XP, level, HP, assassinAbilities);
        this.name = "Assassin";
    }


}
