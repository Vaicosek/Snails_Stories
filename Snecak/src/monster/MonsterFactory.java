package monster;

import engine.GameEngine;
import mapvariables.BiomeModel;
import mapvariables.Map;

import java.util.ArrayList;
import java.util.Arrays;

public class MonsterFactory {
    Map map;
    GameEngine gameEngine;

    public MonsterFactory(Map map, GameEngine gameEngine) {
        this.map = map;
        this.gameEngine = gameEngine;
    }
    public ArrayList<MonsterBase> CreateMonsters(){
        //TODO : DODĚLAT LOGIKU
        return new ArrayList<MonsterBase>(
                Arrays.asList(new MonsterTier1(2)));
    }
}
