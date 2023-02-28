package monster;

import java.util.ArrayList;
import java.util.Arrays;

public class MonsterTier2 extends MonsterBase {


    public MonsterTier2(int GroupLevel) {
        super(1, GroupLevel);

        names = new ArrayList<String>(
                Arrays.asList(
                        "Goblin",
                        "Giant spider",
                        "Kobold",
                        "Imp",
                        "Gremlin",
                        "Harpy",
                        "Gnome",
                        "Bandit"));

    }


}


