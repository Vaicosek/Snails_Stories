class Monster {
    public static final int TIER_1 = 1;
    public static final int TIER_2 = 2;
    public static final int TIER_3 = 3;
    public static final int TIER_4 = 4;
    public static final int TIER_5 = 5;
    public int HP;
    public int Damage;
    public int GroupLevel;
    public int tier;
    public Monster(int tier,int Grouplevel) {
        this.tier = tier;
        this.GroupLevel = Grouplevel;
        switch(tier){
            case TIER_1:
                HP = 40* Grouplevel;
                Damage = 10* Grouplevel;
                break;
            case TIER_2:
                HP = 60* Grouplevel;
                Damage = 20* Grouplevel;
                break;
            case TIER_3:
                HP = 80* Grouplevel;
                Damage = 30* Grouplevel;
                break;
            case TIER_4:
                HP = 100* Grouplevel;
                Damage = 40* Grouplevel;
                break;
            case TIER_5:
                HP = 2000;
                Damage = 50 * Grouplevel;
                break;
        }
    }
}
