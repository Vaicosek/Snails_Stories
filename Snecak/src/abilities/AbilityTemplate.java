package abilities;

public interface AbilityTemplate {

    int getDamage();
    void setDamage(int totalDamage);

    int getManaCost();
    void setManaCost(int manaCost);

    void setName(String name);
    String getName();

    boolean isUnlocked();
    void setUnlocked();
}
