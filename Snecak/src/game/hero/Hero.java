package game.hero;

import game.abilities.*;
import game.itemshandling.*;
import game.monster.Dice;
import game.monster.MonsterBase;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Hero implements HeroTemplate {
    @Getter
    protected int xp;
    @Getter
    protected int level;
    @Getter
    protected int hp;
    protected String name;
    @Setter
    protected int attack;

    protected double mana;
    protected List<AbilityTemplate> abilities = new ArrayList<>();
    @Getter
    protected ItemBase equippedWeapon;
    @Getter
    protected ItemBase equippedWeapon2;
    private static final Logger logger = Logger.getLogger(Hero.class.getName());
    protected ItemBase equippedArmor;
    private static final String EQUIPPED = "Equipped";
    private static final String UNEQUIPPED = "Unequipped";
    @Getter
    private Inventory inventory;

    public Hero(int xp, int level, int hp, List<String> abilityTypes) {
        this.xp = xp;
        this.level = level;
        this.hp = hp;
        initializeAbilities(abilityTypes);
        this.inventory = new Inventory(this);
        calculateAttack();
    }

    private void initializeAbilities(List<String> abilityTypes) {
        this.abilities = abilityTypes.stream()
                .map(AbilityType::fromString)
                .filter(Objects::nonNull)
                .map(AbilityType::createInstance)
                .collect(Collectors.toList());
    }




    public void increaseXP(int amount) {
        int actualXp = getXp() + amount;
        if (actualXp >= 100) {
            levelUp();
            setLevel(getLevel() + 1);
            setXp(0);

        }
    }

    public void levelUp() {
        setHp((getLevel() * 100));
        logger.info("LVL UP");
        gainAbility();
    }


    private void calculateAttack() {
        double damageMultiplier = 1.0; // Default damage multiplier

        if (equippedArmor != null && equippedArmor.getName().equals("TurtleShell")) {
            // If Turtle ability is active and TurtleShell is equipped, reduce damage by 50%
            damageMultiplier = 0.5;
        }

        if (equippedWeapon != null && equippedWeapon2 != null) {
            attack = (int) (Dice.getNextNumber(1, level * 10 + equippedWeapon.getDamage() + equippedWeapon2.getDamage()) * damageMultiplier);
        } else if (equippedWeapon != null) {
            attack = (int) (Dice.getNextNumber(1, level * 10 + equippedWeapon.getDamage()) * damageMultiplier);
        } else {
            attack = (int) (Dice.getNextNumber(1, level * 10) * damageMultiplier);
        }
    }

    public void gainAbility() {
        Scanner scanner = new Scanner(System.in);
        logger.info("You can gain an ability by entering its number:");

        if (checkAllAbilitiesUnlocked()) {
            logger.info("You have unlocked all abilities.");
            return;
        }

        for (int i = 0; i < abilities.size(); i++) {
            AbilityTemplate ability = abilities.get(i);
            if (!ability.isUnlocked()) {
                logger.info((i + 1) + ". " + ability.getName());
            }
        }

        logger.info("Enter the number of the ability you want to gain, or press 'q' to quit:");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("q")) {
            return;
        }

        try {
            int abilityIndex = Integer.parseInt(input) - 1;
            if (abilityIndex >= 0 && abilityIndex < abilities.size()) {
                AbilityTemplate selectedAbility = abilities.get(abilityIndex);
                if (!selectedAbility.isUnlocked()) {
                    selectedAbility.setUnlocked();
                    logger.info("You have gained the ability: " + selectedAbility.getName());
                } else {
                    logger.info("You have already acquired that ability.");
                }
            } else {
                logger.info("Invalid ability number.");
            }
        } catch (NumberFormatException e) {
            logger.warning("Invalid input. Please enter a number or 'q' to quit.");
        }
        updateHeroBonuses();
    }

    private boolean checkAllAbilitiesUnlocked() {
        for (AbilityTemplate ability : abilities) {
            if (!ability.isUnlocked()) {
                return false;
            }
        }
        return true;
    }



    public boolean isAlive() {
        return hp > 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMana() {
        return (int) mana;
    }

    @Override
    public void setXp(int xp) {
        this.xp = xp;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public int getAttack() {
        return attack;
    }


    @Override
    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public List<AbilityTemplate> getAbilities() {
        List<AbilityTemplate> heroAbilities = new ArrayList<>();
        for (AbilityTemplate ability : abilities) {
            if (ability.isUnlocked()) {
                heroAbilities.add(ability);
            }
        }
        return heroAbilities;
    }

    public void equipWeapon(ItemBase weapon) {
        if (this.equippedWeapon == null) {
            logger.warning("This item is not a weapon.");
            return;
        }
        else {
            unequipWeapon();
            this.equippedWeapon = weapon;
            logger.info("Equipped weapon: " + weapon.getName());
        }

        if (hasAbility("SharpWeapons")) {
            SharpWeapons sharpWeapons = new SharpWeapons();
            sharpWeapons.applyBonus(this);
        }
    }

    public void equipWeapon2(ItemBase weapon2) {
        if (this.equippedWeapon2 == null) {
            logger.warning("This item is not a weapon.");
            return;
        }
            else {
            unequipWeapon2();
            this.equippedWeapon = weapon2;
            logger.info("Equipped weapon: " + weapon2.getName());
        }

        if (hasAbility("SharpWeapons")) {
            SharpWeapons sharpWeapons = new SharpWeapons();
            sharpWeapons.applyBonus(this);
        }
    }

    public void unequipWeapon() {
        if (this.equippedWeapon != null) {
            logger.info(UNEQUIPPED + this.equippedWeapon.getName());
            this.equippedWeapon = null;
        }  else {
            logger.info("No weapon equipped.");
        }
    }


    public void unequipWeapon2() {
        if (this.equippedWeapon2 != null) {
            logger.info(UNEQUIPPED + this.equippedWeapon2.getName());
            this.equippedWeapon2 = null;
        }
    }

    public void destroyWeapon(ItemBase weapon)  {
        if (weapon != null) {
            // Remove the armor from the hero's inventory
            if (equippedWeapon == weapon) {
                unequipArmor();  // Unequipped the armor if it's currently equipped
            }

            // Remove the armor from the inventory list
            inventory.removeItem(weapon);

            logger.info("Destroyed and removed " + weapon.getName() + " from the inventory.");
        } else {
            logger.info("Cannot destroy null armor.");
        }
    }


    @Override
    public int getEquippedWeaponDamage() {
        if (equippedArmor != null) {
            return equippedArmor.getDamage();
        } else {
            return 0;
        }
    }

    public void useConsumable(ItemBase consumable) {
        // Assuming consumable has an effect, like restoring health
        this.hp += consumable.getHealth();
        System.out.println("Used " + consumable.getName() + ", health increased by " + consumable.getHealth());
    }


    public boolean hasAbility(String abilityName) {
        return abilities.stream()
                .anyMatch(ability -> ability.getName().equals(abilityName) && ability.isUnlocked());
    }

    @Override
    public boolean canApplyEnchantment() {
        return hasAbility("Enchantment");
    }

    @Override
    public boolean canEquipSecondWeapon() {
        return hasAbility("StrongHands");
    }


    public void equipArmor(ItemBase armor) {
        this.equippedArmor = armor;
        logger.info(EQUIPPED + armor.getName() + " (Protection: " + armor.getProtection() + ")");
    }

    public void unequipArmor() {
        if (this.equippedArmor != null) {
            logger.info(UNEQUIPPED + this.equippedArmor.getName());
            this.equippedArmor = null;
        } else {
            logger.warning("No armor equipped.");
        }
    }

    public void destroyArmor(ItemBase armor ) {
        if (armor != null) {
            // Remove the armor from the hero's inventory
            if (equippedArmor == armor) {
                unequipArmor();  // Unequipped the armor if it's currently equipped
            }

            // Remove the armor from the inventory list
            inventory.removeItem(armor);

            logger.info("Destroyed and removed " + armor.getName() + " from the inventory.");
        } else {
            logger.info("Cannot destroy null armor.");
        }
    }


    public int getEquippedArmorProtection() {
        if (equippedArmor != null) {
            return equippedArmor.getProtection();
        } else {
            return 0;
        }
    }

    @Override
    public ItemBase getEquippedArmor() {
        return equippedArmor;
    }


    @Override
    public void heal(int healthRestored) {
   hp = hp + healthRestored;
    }

    public void usePassiveMonsterAbilities(MonsterBase monster) {
        Inquisition inquisition = new Inquisition();
        MonsterKiller monsterKiller = new MonsterKiller();
        CriticalHit criticalHit = new CriticalHit();

        if (inquisition.isUnlocked()) {
            inquisition.apply(this, monster);
        }

        if (monsterKiller.isUnlocked()) {
            monsterKiller.apply(this, monster);
        }
        if (criticalHit.isUnlocked()){
            criticalHit.apply(this,monster);
        }
    }

    public void updateHeroBonuses() {
        for (AbilityTemplate ability : abilities) {
            if (ability.isUnlocked() && ability instanceof BonusAbilityTemplate) {
                ((BonusAbilityTemplate) ability).applyBonus(this);
            }
        }
    }

}



