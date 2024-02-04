package hero;

import abilities.AbilityBase;
import itemshandling.Armor;
import itemshandling.Consumable;
import itemshandling.Weapon;
import monster.MonsterBase;

import java.util.List;

public interface HeroTemplate {
    int getXP();

    void setXP(int XP);

    int getLevel();

    void setLevel(int level);

    int getHP();

    void setHP(int HP);

    int getAttack();

    void setAttack(int attack);

    String getName();

    void setMana(int Mana);

    int getMana();

    void SetMana(int mana);

    List<AbilityBase> getAbilities();

    void gainAbility();

    void equipWeapon(Weapon weapon);

    void unequipWeapon();

    void equipArmor(Armor armor);

    void unequipArmor();

    void heal(int healthRestored);
    void usePassiveMonsterAbilities(MonsterBase monster, int currentTurn);


    void equipWeapon2(Weapon weapon2);

   int getEquippedArmorProtection();

    List<Weapon> getAvailableWeapons(HeroTemplate hero);

    List<Armor> getAvailableArmors(HeroTemplate hero);

    List<Consumable> getAvailableConsumables(HeroTemplate hero);
}
