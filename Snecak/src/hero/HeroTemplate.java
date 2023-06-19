package hero;

import abilities.HeroAbility;

import java.util.List;

public interface HeroTemplate {
    int getXP();

    void setXP(int XP);

    int getLevel();

    void setLevel(int level);

    int getHP();

    void setHP(int HP);

    int getAttack();

    String getName();

    void setMana(int Mana);

    int getMana();

    void SetMana(int mana);

    List<HeroAbility> getAbilities();

    void powers();

    void gainAbility();
}
