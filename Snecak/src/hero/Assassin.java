package hero;


import abilities.AbilityBase;

import java.util.List;

public class Assassin extends Hero {



    public Assassin(int XP, int level, int HP, List<AbilityBase> assassinAbilities) {
        super(XP, level, HP, assassinAbilities);
        this.name = "Assassin";
    }


}
