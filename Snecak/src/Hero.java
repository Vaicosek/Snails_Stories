class Hero {
    private int XP;
    private int level;
    private int HP;

    public Hero(int XP, int level, int HP) {
        this.XP = XP;
        this.level = level;
        this.HP = HP;

    }

    public int getXP() { return XP; }
    public int getLevel() { return level; }
    public int getHP() { return HP; }

    public void increaseXP(int amount) {
        this.XP += amount;
        if (this.XP >= 100) {
            this.level++;
            this.XP = this.XP - 100;
        }
    }
    public boolean isAlive() { return HP > 0; }
}
