package Game.monster;

import Game.engine.GameEngine;
import Game.mapvariables.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MonsterFactory {
    private final GameMap gameMap;
    private final GameEngine gameEngine;
    private final Random random = new Random();

    public MonsterFactory(GameMap gameMap, GameEngine gameEngine) {
        this.gameMap = gameMap;
        this.gameEngine = gameEngine;
    }

    public List<MonsterBase> createMonsters(int numMonsters, int groupLevel) {
        List<MonsterBase> monsters = new ArrayList<>();
        for (int i = 0; i < numMonsters; i++) {
            MonsterTier selectedTier = selectTierBasedOnProbability();
            String randomName = selectedTier.getRandomName();
            int hp = selectedTier.calculateHP(groupLevel);
            int xp = selectedTier.calculateXP(groupLevel);
            // Now create the MonsterBase with the properties from the selected tier
            monsters.add(new MonsterBase(randomName, groupLevel, hp, xp, selectedTier));
        }
        return monsters;
    }

    private MonsterTier selectTierBasedOnProbability() {
        // Example probability distribution: 35% TIER_1, 30% TIER_2, 20% TIER_3, 10% TIER_4, 5% TIER_5
        int[] tierProbabilities = {35, 30, 20, 10, 5};
        int probabilitySum = 100;
        int randomValue = random.nextInt(probabilitySum);
        int sum = 0;
        MonsterTier selectedTier = MonsterTier.TIER_1;

        for (int i = 0; i < tierProbabilities.length; i++) {
            sum += tierProbabilities[i];
            if (randomValue < sum) {
                selectedTier = MonsterTier.values()[i];
                break;
            }
        }
        return selectedTier;
    }
}






