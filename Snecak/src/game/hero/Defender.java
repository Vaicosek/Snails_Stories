package game.hero;

import java.util.List;

public class Defender extends Hero {

    public Defender(int xp, int level, int hp, List<String> abilityNames) {
        super(xp, level, hp, abilityNames);
        this.name = "Defender";
    }
}
