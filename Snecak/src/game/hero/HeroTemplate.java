package game.hero;

import game.abilities.AbilityTemplate;
import game.itemshandling.Armor;
import game.itemshandling.Consumable;
import game.itemshandling.ItemBase;
import game.itemshandling.Weapon;
import game.monster.MonsterBase;
import game.players.Player;

import java.util.List;

public interface HeroTemplate {
    int getXp();

    void setXp(int XP);

    int getLevel();

    void setLevel(int level);

    int getHp();

    void setHp(int HP);

    int getAttack();

    void setAttack(int attack);

    String getName();

    void setMana(int Mana);

    int getMana();

    void SetMana(int mana);

    List<AbilityTemplate> getAbilities();

    void gainAbility();

    void equipWeapon(ItemBase weapon);


    void unequipWeapon();

    void equipArmor(ItemBase armor);

    void unequipArmor();

    void heal(int healthRestored);
    void usePassiveMonsterAbilities(MonsterBase monster);


    void equipWeapon2(ItemBase weapon2);

   int getEquippedArmorProtection();
    int getEquippedWeaponDamage();

    List<Weapon> getAvailableWeapons(HeroTemplate hero, Player player);

    List<Armor> getAvailableArmors(HeroTemplate hero, Player player);

    List<Consumable> getAvailableConsumables(HeroTemplate hero, Player player);

    void destroyArmor(ItemBase armor, Player player);

    void destroyWeapon(ItemBase weapon, Player player);

    ItemBase getEquippedArmor();

    ItemBase getEquippedWeapon();
}
