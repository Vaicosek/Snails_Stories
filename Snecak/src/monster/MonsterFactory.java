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

    public ArrayList<MonsterBase> CreateMonsters(int numPlayers) {
        int[] tierProbabilities = {35, 30, 20, 10, 5};
        int totalProbability = Arrays.stream(tierProbabilities).sum();

        ArrayList<MonsterBase> monsters = new ArrayList<>();

        int numMonsters = numPlayers + 1;
        for (int i = 0; i < numMonsters; i++) {
            int rand = Dice.getNextNumber(1, totalProbability);
            int monsterTier = 0;
            for (int j = 0; j < tierProbabilities.length; j++) {
                rand -= tierProbabilities[j];
                if (rand <= 0) {
                    monsterTier = j + 1;
                    break;
                }
            }

            int groupLevel = gameEngine.getGroupLevel();
            switch (monsterTier) {
                case 1 -> monsters.add(new MonsterTier1(groupLevel));
                case 2 -> monsters.add(new MonsterTier2(groupLevel));
                case 3 -> monsters.add(new MonsterTier3(groupLevel));
                case 4 -> monsters.add(new MonsterTier4(groupLevel));
                case 5 -> monsters.add(new MonsterTier5(groupLevel));
                default -> {
                }
            }
        }
        return monsters;
    }

}




