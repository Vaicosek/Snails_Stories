public interface HeroTemplate {
    int getXP();
    void setXP(int XP);
    int getLevel();
    void setLevel(int level);
    int getHP();
    void setHP(int HP);
    boolean hasAbility(Enum ability);

}
