package monster;

import java.util.ArrayList;
import java.util.Arrays;

public class MonsterTier1 extends MonsterBase {
    final ArrayList<String> names = new ArrayList<String>(
            Arrays.asList(
                    "Goblin",
                    "Chicken",
                    "Rat"));

    public MonsterTier1(int GroupLevel) {
        super(1,GroupLevel);
        Name = getRandomName();
    }

    String getRandomName() {
        var i = Dice.getNextNumber(0, names.size());
        return names.get(i);
    }

}


