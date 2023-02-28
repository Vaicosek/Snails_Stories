package monster;

import java.util.ArrayList;
import java.util.Arrays;

public class MonsterTier4 extends MonsterBase {


    public MonsterTier4(int GroupLevel) {
        super(1, GroupLevel);

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


}


