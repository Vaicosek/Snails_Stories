package hero;



import abilities.AbilityTemplate;

import java.util.List;

public class Assassin extends Hero {



    public Assassin(int XP, int level, int HP, List<AbilityTemplate> assassinAbilities) {
        super(XP, level, HP, assassinAbilities);
        this.name = "Assassin";
    }


}
