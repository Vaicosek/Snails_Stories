package hero;


import java.util.List;

public class Healer extends Hero {


    public Healer(int XP, int level, int HP, List<String> abilityNames) {
        super(XP, level, HP, abilityNames);
        this.name = "Healer";
    }

}
