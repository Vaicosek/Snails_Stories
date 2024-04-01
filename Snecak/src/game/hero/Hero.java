package game.hero;

import game.abilities.*;
import game.itemshandling.*;
import game.monster.Dice;
import game.monster.MonsterBase;
import game.players.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
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
    protected Weapon equippedWeapon;
    @Getter
    protected Weapon equippedWeapon2;
    private static final Logger logger = Logger.getLogger(Hero.class.getName());
    protected Armor equippedArmor;
    private static final String EQUIPPED = "Equipped";
    private static final String UNEQUIPPED = "Unequipped";

    public Hero(int xp, int level, int hp, List<String> abilityTypes) {
        this.xp = xp;
        this.level = level;
        this.hp = hp;
        initializeAbilities(abilityTypes);
        calculateAttack();
    }

    private void initializeAbilities(List<String> abilityTypes) {
        this.abilities = abilityTypes.stream()
                .map(AbilityFactory::createAbility)
                .collect(Collectors.toList());
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

    public void increaseXP(int amount) {
        this.xp += amount;
        if (this.xp >= 100) {
            this.level++;
            this.xp = this.xp - 100;
            gainAbility();
        }
    }

    public boolean isAlive() {
        return hp > 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setMana(int mana) {
        this.mana = mana;
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
    public void SetMana(int mana) {
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

    public void equipWeapon(Weapon weapon) {
        unequipWeapon();
        this.equippedWeapon = weapon;
        logger.info(EQUIPPED + weapon.getName() + " (Damage: " + weapon.getDamage() + ")");

        for (AbilityTemplate ability : abilities) {
            if (ability.getName().equals("SharpWeapons") && ability.isUnlocked()) {
                SharpWeapons sharpWeapons = new SharpWeapons();
                sharpWeapons.applyBonus(this);
            }
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


    public void equipWeapon2(Weapon weapon) {
        unequippedWeapon2();
        this.equippedWeapon2 = weapon;
        logger.info(EQUIPPED + weapon.getName() + " (Damage: " + weapon.getDamage() + ")");

    }


    public void unequippedWeapon2() {
        if (this.equippedWeapon2 != null) {
            logger.info(UNEQUIPPED + this.equippedWeapon2.getName());
            this.equippedWeapon2 = null;
        }
    }

    public void equipArmor(Armor armor) {
        this.equippedArmor = armor;
        logger.info(EQUIPPED + armor.getName() + " (Protection: " + armor.getProtection() + ")");
    }

    public int getEquippedArmorProtection() {
        if (equippedArmor != null) {
            return equippedArmor.getProtection();
        } else {
            // handle the case when no armor is equipped, for example, return a default value
            return 0; // or any other default value
        }
    }

    @Override
    public int getEquippedWeaponDamage() {
        if (equippedArmor != null) {
            return equippedArmor.getDamage();
        } else {
            // handle the case when no armor is equipped, for example, return a default value
            return 0; // or any other default value
        }
    }

    public void unequipArmor() {
        if (this.equippedArmor != null) {
            logger.info(UNEQUIPPED + this.equippedArmor.getName());
            this.equippedArmor = null;
        } else {
            logger.warning("No armor equipped.");
        }
    }

    public void destroyArmor(Armor armor, Player player) {
        if (armor != null) {
            // Remove the armor from the hero's inventory
            if (equippedArmor == armor) {
                unequipArmor();  // Unequipped the armor if it's currently equipped
            }

            // Remove the armor from the inventory list
            player.getInventory().removeItem(armor);

            logger.info("Destroyed and removed " + armor.getName() + " from the inventory.");
        } else {
            logger.info("Cannot destroy null armor.");
        }
    }

    @Override
    public void destroyWeapon(Weapon weapon, Player player) {
        if (weapon != null) {
            // Remove the armor from the hero's inventory
            if (equippedWeapon == weapon) {
                unequipArmor();  // Unequipped the armor if it's currently equipped
            }

            // Remove the armor from the inventory list
            player.getInventory().removeItem(weapon);

            logger.info("Destroyed and removed " + weapon.getName() + " from the inventory.");
        } else {
            logger.info("Cannot destroy null armor.");
        }
    }

    @Override
    public Armor getEquippedArmor() {
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


    public List<Weapon> getAvailableWeapons(HeroTemplate hero, Player player) {
        List<Weapon> availableWeapons = new ArrayList<>();
        for (ItemBase item : player.getInventory().getItems()) {
            if (item.getItemType() == ItemType.WEAPON) {
                availableWeapons.add((Weapon) item);
            }
        }
        return availableWeapons;
    }

    public List<Armor> getAvailableArmors(HeroTemplate hero, Player player) {
        List<Armor> availableArmors = new ArrayList<>();
        for (ItemBase item : player.getInventory().getItems()) {
            if (item.getItemType() == ItemType.ARMOR) {
                availableArmors.add((Armor) item);
            }
        }
        return availableArmors;
    }

    public List<Consumable> getAvailableConsumables(HeroTemplate hero, Player player) {
        List<Consumable> availableConsumables = new ArrayList<>();
        for (ItemBase item : player.getInventory().getItems()) {
            if (item.getItemType() == ItemType.CONSUMABLE) {
                availableConsumables.add((Consumable) item);
            }
        }
        return availableConsumables;
    }

}