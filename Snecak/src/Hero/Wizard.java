package hero;

import abilities.HeroAbility;
import abilities.MagicalPower;

import java.util.ArrayList;
import java.util.Scanner;

public class Wizard extends Hero {
    public Wizard(int XP, int level, int HP) {
        super(XP, level, HP);
        this.name = "Wizard";
        Abilities.add(new MagicalPower(this));
    }
}


