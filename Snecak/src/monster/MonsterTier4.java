package monster;

import java.util.ArrayList;
import java.util.Arrays;

public class MonsterTier4 extends MonsterBase {
    final ArrayList<String> names = new ArrayList<String>(
            Arrays.asList(
                    "Goblin",
                    "Chicken",
                    "Rat"));

    public MonsterTier4(int GroupLevel) {
        super(4,GroupLevel);
        Name = getRandomName();
    }

    String getRandomName() {
        var i = Dice.getNextNumber(0, names.size());
        return names.get(i);
    }

}


