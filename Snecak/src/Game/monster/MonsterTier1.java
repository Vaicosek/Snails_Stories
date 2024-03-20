package Game.monster;

import java.util.ArrayList;
import java.util.Arrays;

public class MonsterTier1 extends MonsterBase {


    @Override
    public void InitializeName() {
        names = new ArrayList<String>(
                Arrays.asList(
                        "Chicken",
                        "Rat",
                        "Bat",
                        "Snake",
                        "Lizard",
                        "Beetle",
                        "Frog",
                        "Crab",
                        "Scorpion"));

    }

    public MonsterTier1(int GroupLevel) {
        super(1, GroupLevel);

 

    }


}


