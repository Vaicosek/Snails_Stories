package monster;

import java.util.ArrayList;
import java.util.Arrays;

public class MonsterTier5 extends MonsterBase {
    final ArrayList<String> names = new ArrayList<String>(
            Arrays.asList(
                    "Goblin",
                    "Chicken",
                    "Rat"));

    public MonsterTier5(int GroupLevel) {
        super(5,GroupLevel);
        Name = getRandomName();
    }

    String getRandomName() {
        var i = Dice.getNextNumber(0, names.size());
        return names.get(i);
    }

}


