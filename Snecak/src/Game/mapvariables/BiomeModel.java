package Game.mapvariables;


import Game.monster.MonsterBase;

import java.util.ArrayList;

public class BiomeModel {
    public String Name;

    public ArrayList<MonsterBase> monsters;

    public BiomeModel(String name) {
        Name = name;
    }


}
