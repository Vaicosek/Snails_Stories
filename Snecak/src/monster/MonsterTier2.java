package monster;

import java.util.ArrayList;
import java.util.Arrays;

public class MonsterTier2 extends MonsterBase {

    @Override
    void InitializeName() {
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

    public MonsterTier2(int GroupLevel) {
        super(2, GroupLevel);



    }


}


