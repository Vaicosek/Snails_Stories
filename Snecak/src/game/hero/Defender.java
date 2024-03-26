package game.hero;

import java.util.List;

public class Defender extends Hero {

    public Defender(int XP, int level, int HP, List<String> abilityNames) {
        super(XP, level, HP, abilityNames);
        this.name = "Defender";
    }
}
