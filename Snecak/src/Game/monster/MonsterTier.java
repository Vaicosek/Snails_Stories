package Game.monster;

import java.util.List;

public enum MonsterTier {
    TIER_1(1, 10, 20, List.of("Chicken", "Rat", "Bat")),
    TIER_2(2, 20, 30, List.of("Goblin", "Giant spider", "Kobold")),
    TIER_3(3, 30, 40, List.of("Skeleton", "Orc", "Undead")),
    TIER_4(4, 50, 50, List.of("Minotaur", "Yeti", "Banshee")),
    TIER_5(5, 200, 1000, List.of("Dragon", "Phoenix", "Archdemon"));

    private final int tier;
    private final int baseXP;
    private final int baseHPMultiplier;
    private final List<String> names;

    MonsterTier(int tier, int baseXP, int baseHPMultiplier, List<String> names) {
        this.tier = tier;
        this.baseXP = baseXP;
        this.baseHPMultiplier = baseHPMultiplier;
        this.names = names;
    }

    public int getTier() {
        return tier;
    }

    public int getBaseXP() {
        return baseXP;
    }

    public List<String> getNames() {
        return names;
    }

    public int calculateHP(int groupLevel) {
        return this.baseHPMultiplier * groupLevel;
    }

    public int calculateXP(int groupLevel) {
        return this.baseXP + (groupLevel * (this == TIER_5 ? 10 : 2));
    }
}
