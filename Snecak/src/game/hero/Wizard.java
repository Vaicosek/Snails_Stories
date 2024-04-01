package game.hero;


import java.util.List;

public class Wizard extends Hero {
    public Wizard(int xp, int level, int hp, List<String> abilityNames) {
        super(xp, level, hp, abilityNames);
        this.name = "Wizard";
    }
}


