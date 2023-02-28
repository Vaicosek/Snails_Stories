package monster;

import java.util.ArrayList;
import java.util.Arrays;

public class MonsterTier5 extends MonsterBase {


    public MonsterTier5(int GroupLevel) {
        super(1, GroupLevel);

        names = new ArrayList<String>(
                Arrays.asList(
                        "Dragon",
                        "Phoenix",
                        "Archdemon",
                        "Kraken",
                        "Titan",
                        "Dreadnought",
                        "Machine",
                        "Juggernaut",
                        "Demon Lord"));

    }


}


