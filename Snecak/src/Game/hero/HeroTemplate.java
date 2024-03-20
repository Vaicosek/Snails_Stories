package Game.hero;

import Game.abilities.AbilityTemplate;
import Game.itemshandling.Armor;
import Game.itemshandling.Consumable;
import Game.itemshandling.Weapon;
import Game.monster.MonsterBase;
import Game.players.Player;

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

    List<AbilityTemplate> getAbilities();

    void gainAbility();

    void equipWeapon(Weapon weapon);


    void unequipWeapon();

    void equipArmor(Armor armor);

    void unequipArmor();

    void heal(int healthRestored);
    void usePassiveMonsterAbilities(MonsterBase monster);


    void equipWeapon2(Weapon weapon);

   int getEquippedArmorProtection();
    int getEquippedWeaponDamage();

    List<Weapon> getAvailableWeapons(HeroTemplate hero, Player player);

    List<Armor> getAvailableArmors(HeroTemplate hero, Player player);

    List<Consumable> getAvailableConsumables(HeroTemplate hero, Player player);

    void destroyArmor(Armor armor, Player player);

    void destroyWeapon(Weapon weapon, Player player);

    Armor getEquippedArmor();

    Weapon getEquippedWeapon();
}
