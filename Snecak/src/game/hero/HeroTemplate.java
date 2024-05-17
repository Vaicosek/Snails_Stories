package game.hero;

import game.abilities.AbilityTemplate;
import game.itemshandling.Inventory;
import game.itemshandling.ItemBase;
import game.monster.MonsterBase;


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

    void setMana(int mana);

    int getMana();

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

    void destroyArmor(ItemBase armor);

    void destroyWeapon(ItemBase weapon );

    ItemBase getEquippedArmor();

    ItemBase getEquippedWeapon();

    boolean hasAbility(String abilityName);

    boolean canApplyEnchantment();

    boolean canEquipSecondWeapon();

    void useConsumable(ItemBase consumable);

    public void increaseXP(int amount);

    public void levelUp();


    Inventory getInventory();
}
