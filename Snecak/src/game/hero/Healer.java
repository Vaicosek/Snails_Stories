package game.hero;


import java.util.List;

public class Healer extends Hero {


    public Healer(int xp, int level, int hp, List<String> abilityNames) {
        super(xp, level, hp, abilityNames);
        this.name = "Healer";
    }

}
