package monster;

import java.util.ArrayList;
import java.util.Arrays;

public class MonsterTier3 extends MonsterBase {

    @Override
    public void InitializeName() {
        names = new ArrayList<String>(
                Arrays.asList(
                        "Skeleton",
                        "Orc",
                        "Undead",
                        "Harpy",
                        "Ghost",
                        "Zombie",
                        "Mummy",
                        "Draugr",
                        "Satyr"));
    }

    public MonsterTier3(int GroupLevel) {
        super(3, GroupLevel);


    }


}


