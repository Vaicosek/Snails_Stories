package hero;

import abilities.*;
import itemshandling.*;
import monster.Dice;
import monster.MonsterBase;
import players.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hero implements HeroTemplate {
    protected int XP;
    protected int level;
    protected int HP;
    protected String name;
    protected int attack;

    protected double mana;
    protected ArrayList<AbilityBase> abilities = new ArrayList<>();
    protected Weapon equippedWeapon;
    protected Weapon equippedWeapon2;

    protected Armor equippedArmor;

    public Hero(int XP, int level, int HP, List<AbilityBase> allAbilities) {
        this.XP = XP;
        this.level = level;
        this.HP = HP;
        initializeAbilities(allAbilities);
        calculateAttack();
    }

    private void initializeAbilities(List<AbilityBase> allAbilities) {
        for (AbilityBase ability : allAbilities) {
            AbilityBase clonedAbility = new AbilityBase();
            clonedAbility.setName(ability.getName());
            clonedAbility.setDamage(ability.getDamage());
            clonedAbility.setManaCost(ability.getManaCost());
            clonedAbility.setUnlocked(false);
            abilities.add(clonedAbility);
        }
    }

    private void calculateAttack() {
        double damageMultiplier = 1.0; // Default damage multiplier

        if (equippedArmor.getName().equals("TurtleShell")) {
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
        System.out.println("You can gain an ability by entering its number:");

        if (checkAllAbilitiesUnlocked()) {
            System.out.println("You have unlocked all abilities.");
            return;
        }

        for (int i = 0; i < abilities.size(); i++) {
            AbilityBase ability = abilities.get(i);
            if (!ability.isUnlocked()) {
                System.out.println((i + 1) + ". " + ability.getName());
            }
        }

        System.out.println("Enter the number of the ability you want to gain, or press 'q' to quit:");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("q")) {
            return;
        }

        try {
            int abilityIndex = Integer.parseInt(input) - 1;
            if (abilityIndex >= 0 && abilityIndex < abilities.size()) {
                AbilityBase selectedAbility = abilities.get(abilityIndex);
                if (!selectedAbility.isUnlocked()) {
                    selectedAbility.setUnlocked(true);
                    System.out.println("You have gained the ability: " + selectedAbility.getName());
                } else {
                    System.out.println("You have already acquired that ability.");
                }
            } else {
                System.out.println("Invalid ability number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number or 'q' to quit.");
        }
        updateHeroBonuses();
    }

    private boolean checkAllAbilitiesUnlocked() {
        for (AbilityBase ability : abilities) {
            if (!ability.isUnlocked()) {
                return false;
            }
        }
        return true;
    }

    public int getXP() {
        return XP;
    }

    public int getLevel() {
        return level;
    }

    public int getHP() {
        return HP;
    }

    public void increaseXP(int amount) {
        this.XP += amount;
        if (this.XP >= 100) {
            this.level++;
            this.XP = this.XP - 100;
            gainAbility();
        }
    }

    public boolean isAlive() {
        return HP > 0;
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
    public void setXP(int xp) {
        this.XP = xp;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void setHP(int hp) {
        this.HP = hp;
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
    public List<AbilityBase> getAbilities() {
        List<AbilityBase> heroAbilities = new ArrayList<>();
        for (AbilityBase ability : abilities) {
            if (ability.isUnlocked()) {
                heroAbilities.add(ability);
            }
        }
        return heroAbilities;
    }

    public void equipWeapon(Weapon weapon) {
        unequipWeapon();
        this.equippedWeapon = weapon;
        System.out.println("Equipped " + weapon.getName() + " (Damage: " + weapon.getDamage() + ")");

        for (AbilityBase ability : abilities) {
            if (ability.getName().equals("SharpWeapons") && ability.isUnlocked()) {
                SharpWeapons sharpWeapons = new SharpWeapons();
                sharpWeapons.passiveEffect(this);
            }
        }
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void unequipWeapon() {
        if (this.equippedWeapon != null) {
            System.out.println("Unequipped " + this.equippedWeapon.getName());
            this.equippedWeapon = null;
        } /* else {
            System.out.println("No weapon equipped.");
        } */
    }


    public void equipWeapon2(Weapon weapon) {
        unequipWeapon2();
        this.equippedWeapon2 = weapon;
        System.out.println("Equipped " + weapon.getName() + " (Damage: " + weapon.getDamage() + ")");

    }


    public void unequipWeapon2() {
        if (this.equippedWeapon2 != null) {
            System.out.println("Unequipped " + this.equippedWeapon2.getName());
            this.equippedWeapon2 = null;
        } /* else {
            System.out.println("No second weapon equipped.");
        } */
    }

    public void equipArmor(Armor armor) {
        this.equippedArmor = armor;
        System.out.println("Equipped " + armor.getName() + " (Protection: " + armor.getProtection() + ")");
    }

    public int getEquippedArmorProtection() {
        if (equippedArmor != null) {
            return equippedArmor.Protection;
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
            System.out.println("Unequipped " + this.equippedArmor.getName());
            this.equippedArmor = null;
        } else {
            System.out.println("No armor equipped.");
        }
    }

    public void destroyArmor(Armor armor, Player player) {
        if (armor != null) {
            // Remove the armor from the hero's inventory
            if (equippedArmor == armor) {
                unequipArmor();  // Unequip the armor if it's currently equipped
            }

            // Remove the armor from the inventory list
            player.getInventory().removeItem(armor);

            System.out.println("Destroyed and removed " + armor.getName() + " from the inventory.");
        } else {
            System.out.println("Cannot destroy null armor.");
        }
    }

    @Override
    public void destroyWeapon(Weapon weapon, Player player) {
        if (weapon != null) {
            // Remove the armor from the hero's inventory
            if (equippedWeapon == weapon) {
                unequipArmor();  // Unequip the armor if it's currently equipped
            }

            // Remove the armor from the inventory list
            player.getInventory().removeItem(weapon);

            System.out.println("Destroyed and removed " + weapon.getName() + " from the inventory.");
        } else {
            System.out.println("Cannot destroy null armor.");
        }
    }

    @Override
    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    public Weapon getEquippedWeapon2() {
        return equippedWeapon2;
    }



    @Override
    public void heal(int healthRestored) {

    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void usePassiveMonsterAbilities(MonsterBase monster, int currentTurn) {
        Inquisition inquisition = new Inquisition();
        MonsterKiller monsterKiller = new MonsterKiller();
        CriticalHit criticalHit = new CriticalHit();

        if (inquisition.isUnlocked()) {
            inquisition.use(this, monster);
        }

        if (monsterKiller.isUnlocked()) {
            monsterKiller.use(this, monster);
        }
        if (criticalHit.isUnlocked()){
            monsterKiller.use(this,monster);
        }
    }

    public void updateHeroBonuses() {
        for (AbilityBase ability : abilities) {
            if (ability.isUnlocked()) {
                ability.useOnSelf(this);
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