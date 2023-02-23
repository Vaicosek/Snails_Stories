package monster;

public abstract class MonsterBase {
    public static final int TIER_1 = 1;
    public static final int TIER_2 = 2;
    public static final int TIER_3 = 3;
    public static final int TIER_4 = 4;
    public static final int TIER_5 = 5;
    public int HP;
    public int Damage;
    public int GroupLevel;
    public int tier;
    public String Name;



    public MonsterBase(int tier, int GroupLevel) {
        this.tier = tier;
        this.GroupLevel = GroupLevel;
        switch (tier) {
            case TIER_1 -> {
                HP = 40 * GroupLevel;
                Damage = GroupLevel/2;
            }
            case TIER_2 -> {
                HP = 60 * GroupLevel;
                Damage = 2 + GroupLevel;
            }
            case TIER_3 -> {
                HP = 80 * GroupLevel;
                Damage = 3 + GroupLevel;
            }
            case TIER_4 -> {
                HP = 100 * GroupLevel;
                Damage = 4 + GroupLevel;
            }
            case TIER_5 -> {
                HP = 2000;
                Damage = 5 * GroupLevel;
            }
        }
    }
    public int Attack () {
        Dice.getNextRoll() * Damage;

    }

}
