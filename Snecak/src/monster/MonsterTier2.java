package monster;

import java.util.ArrayList;
import java.util.Arrays;

public class MonsterTier2 extends MonsterBase {
    final ArrayList<String> names = new ArrayList<String>(
            Arrays.asList(
                    "Goblin",
                    "Chicken",
                    "Rat"));

    public MonsterTier2(int GroupLevel) {
        super(2,GroupLevel);
        Name = getRandomName();
    }

    String getRandomName() {
        var i = Dice.getNextNumber(0, names.size());
        return names.get(i);
    }

}


