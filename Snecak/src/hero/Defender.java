package hero;

import abilities.AbilityTemplate;

import java.util.List;

public class Defender extends Hero {

    public Defender(int XP, int level, int HP, List<AbilityTemplate> defenderAbilities) {
        super(XP, level, HP, defenderAbilities);
        this.name = "Defender";
    }
}
