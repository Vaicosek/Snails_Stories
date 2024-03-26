package game.hero;


import java.util.List;

public class Wizard extends Hero {
    public Wizard(int XP, int level, int HP, List<String> abilityNames) {
        super(XP, level, HP, abilityNames);
        this.name = "Wizard";
    }
}


