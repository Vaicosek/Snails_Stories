package monster;

import java.util.ArrayList;
import java.util.Arrays;

public class MonsterTier4 extends MonsterBase {

    @Override
    void InitilizeName() {
        names = new ArrayList<String>(
                Arrays.asList(
                        "Minotaur",
                        "Yeti",
                        "Banshee",
                        "Giant",
                        "Werewolf ",
                        "Vampire",
                        "Hydra",
                        "Griffon",
                        "Cerberus"
                ));
    }

    public MonsterTier4(int GroupLevel) {
        super(4, GroupLevel);


    }


}


