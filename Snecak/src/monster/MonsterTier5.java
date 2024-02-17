package monster;

import java.util.ArrayList;
import java.util.Arrays;

public class MonsterTier5 extends MonsterBase {

    @Override
    public void InitializeName() {
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

    public MonsterTier5(int GroupLevel) {
        super(5, GroupLevel);


    }


}


