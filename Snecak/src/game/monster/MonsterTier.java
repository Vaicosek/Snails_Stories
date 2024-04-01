package game.monster;

import lombok.Getter;

import java.util.List;
import java.util.function.BiFunction;

public enum MonsterTier {
    TIER_1(1, 10, 20, List.of("Chicken", "Rat", "Bat"), (groupLevel, attackReduction) -> 10 * groupLevel * attackReduction),
    TIER_2(2, 20, 30, List.of("Goblin", "Giant spider", "Kobold"), (groupLevel, attackReduction) -> 15 * groupLevel * attackReduction),
    TIER_3(3, 30, 40, List.of("Skeleton", "Orc", "Undead"), (groupLevel, attackReduction) -> 20 * groupLevel * attackReduction),
    TIER_4(4, 50, 50, List.of("Minotaur", "Yeti", "Banshee"), (groupLevel, attackReduction) -> 25 * groupLevel * attackReduction),
    TIER_5(5, 200, 1000, List.of("Dragon", "Phoenix", "Archdemon"), (groupLevel, attackReduction) -> 30 * groupLevel * attackReduction);

    @Getter
    private final int tier;
    @Getter
    private final int baseXP;
    private final int baseHPMultiplier;
    @Getter
    private final List<String> names;
    private final BiFunction<Integer, Integer, Integer> damageCalculation;

    MonsterTier(int tier,
                int baseXP,
                int baseHPMultiplier,
                List<String> names,
                BiFunction<Integer, Integer, Integer> damageCalculation) {
        this.tier = tier;
        this.baseXP = baseXP;
        this.baseHPMultiplier = baseHPMultiplier;
        this.names = names;
        this.damageCalculation = damageCalculation;
    }

    public int calculateAttackDamage(int groupLevel, int attackReduction) {
        return damageCalculation.apply(groupLevel, attackReduction);
    }

    public int calculateHP(int groupLevel) {
        return this.baseHPMultiplier * groupLevel;
    }

    public int calculateXP(int groupLevel) {
        return this.baseXP + groupLevel * (this.tier == 5 ? 10 : 2);
    }

    // Random name getter
    public String getRandomName() {
        return names.get((int) (Math.random() * names.size()));
    }
}
