package hero;

import abilities.HeroAbility;

import java.util.List;

public class Defender extends Hero {

    public Defender(int XP, int level, int HP, List<HeroAbility> defenderAbilities) {
        super(XP, level, HP, defenderAbilities);
        this.name = "Defender";
    }
}
