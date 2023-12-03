package hero;

import abilities.*;
import itemshandling.Armor;
import itemshandling.Weapon;
import monster.Dice;
import monster.MonsterBase;

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
    protected ArrayList<HeroAbility> abilities = new ArrayList<>();
    protected Weapon equippedWeapon;
    protected Weapon equippedWeapon2;

    protected Armor equippedArmor;

    public Hero(int XP, int level, int HP, List<HeroAbility> allAbilities) {
        this.XP = XP;
        this.level = level;
        this.HP = HP;
        initializeAbilities(allAbilities);
        calculateAttack();
    }

    private void initializeAbilities(List<HeroAbility> allAbilities) {
        for (HeroAbility ability : allAbilities) {
            HeroAbility clonedAbility = new HeroAbility();
            clonedAbility.setName(ability.getName());
            clonedAbility.setDamage(ability.getDamage());
            clonedAbility.setManaCost(ability.getManaCost());
            clonedAbility.setUnlocked(false);
            abilities.add(clonedAbility);
        }
    }

    private void calculateAttack() {
        if (equippedWeapon != null && equippedWeapon2 != null) {
            attack = Dice.getNextNumber(1, level * 10 + equippedWeapon.getDamage() + equippedWeapon2.getDamage());
        } else if (equippedWeapon != null) {
            attack = Dice.getNextNumber(1, level * 10 + equippedWeapon.getDamage());
        } else {
            attack = Dice.getNextNumber(1, level * 10);
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
            HeroAbility ability = abilities.get(i);
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
                HeroAbility selectedAbility = abilities.get(abilityIndex);
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
        for (HeroAbility ability : abilities) {
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
    public List<HeroAbility> getAbilities() {
        List<HeroAbility> heroAbilities = new ArrayList<>();
        for (HeroAbility ability : abilities) {
            if (ability.isUnlocked()) {
                heroAbilities.add(ability);
            }
        }
        return heroAbilities;
    }

    public void equipWeapon(Weapon weapon) {
        this.equippedWeapon = weapon;
        System.out.println("Equipped " + weapon.getName() + " (Damage: " + weapon.getDamage() + ")");

        for (HeroAbility ability : abilities) {
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
        } else {
            System.out.println("No weapon equipped.");
        }
    }

    public void equipWeapon2(Weapon weapon) {
        this.equippedWeapon2 = weapon;
        System.out.println("Equipped " + weapon.getName() + " (Damage: " + weapon.getDamage() + ")");
        // Add any additional logic or effects related to equipping the second weapon
    }




    // New method to unequip the second weapon
    public void unequipWeapon2() {
        if (this.equippedWeapon2 != null) {
            System.out.println("Unequipped " + this.equippedWeapon2.getName());
            this.equippedWeapon2 = null;
        } else {
            System.out.println("No second weapon equipped.");
        }
    }

    public void equipArmor(Armor armor) {
        this.equippedArmor = armor;
        System.out.println("Equipped " + armor.getName() + " (Protection: " + armor.getProtection() + ")");
    }

    public int getEquippedArmorProtection() {
        return equippedArmor.Protection;
    }

    public void unequipArmor() {
        if (this.equippedArmor != null) {
            System.out.println("Unequipped " + this.equippedArmor.getName());
            this.equippedArmor = null;
        } else {
            System.out.println("No armor equipped.");
        }
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
        for (HeroAbility ability : abilities) {
            if (ability.isUnlocked()) {
                ability.use(this);
            }
        }
    }
}

