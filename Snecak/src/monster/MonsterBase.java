package monster;

import java.util.ArrayList;

public abstract class MonsterBase {

    private static final int TIER_1 = 1;
    private static final int TIER_2 = 2;
    private static final int TIER_3 = 3;
    private static final int TIER_4 = 4;
    private static final int TIER_5 = 5;
    public int HP;
    public int GroupLevel;
    public int tier;

    public String Name;


    public MonsterBase(int tier, int GroupLevel) {
        this.tier = tier;
        Name = getRandomName();

        switch (tier) {
            case TIER_1 -> {
                HP = 20 * GroupLevel;

            }
            case TIER_2 -> {
                HP = 30 * GroupLevel;

            }
            case TIER_3 -> {
                HP = 40 * GroupLevel;

            }
            case TIER_4 -> {
                HP = 50 * GroupLevel;

            }
            case TIER_5 -> {
                HP = 1000 * GroupLevel;

            }

        }
    }

    protected ArrayList<String> names;

    String getRandomName() {
        var i = Dice.getNextNumber(0, names.size());
        return names.get(i);
    }

    public int Attack() {
        var i = Dice.getNextNumber(1, tier * 10 + (10 * GroupLevel));
        return i;
    }
}


