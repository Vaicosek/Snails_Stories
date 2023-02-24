package monster;

public abstract class MonsterBase {
    public static final int TIER_1 = 1;
    public static final int TIER_2 = 2;
    public static final int TIER_3 = 3;
    public static final int TIER_4 = 4;
    public static final int TIER_5 = 5;
    public int HP;
    public int GroupLevel;
    public int tier;
    public String Name;


    public MonsterBase(int tier, int GroupLevel) {
        this.tier = tier;
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

    public int Attack() {
        var i = Dice.getNextNumber(1, TIER_1* 10  + (10 * GroupLevel) +1);
        return i;
    }

}


