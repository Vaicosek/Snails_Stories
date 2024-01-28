package hero;

import abilities.AbilityBase;

import java.util.List;

public class Defender extends Hero {

    public Defender(int XP, int level, int HP, List<AbilityBase> defenderAbilities) {
        super(XP, level, HP, defenderAbilities);
        this.name = "Defender";
    }
}
