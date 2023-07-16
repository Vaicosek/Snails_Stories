package hero;

import abilities.HeroAbility;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hero implements HeroTemplate {
    protected int XP;
    protected int level;
    protected int HP;
    protected String name;
    protected int Attack = 20;
    protected double Mana;
    protected ArrayList<HeroAbility> abilities = new ArrayList<HeroAbility>();

    public Hero(int XP, int level, int HP, List<HeroAbility> allAbilities) {
        this.XP = XP;
        this.level = level;
        this.HP = HP;
        initializeAbilities(allAbilities);
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
    public void setMana(int Mana) {

    }

    @Override
    public int getMana() {
        return 0;
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
        return Attack;
    }

    @Override
    public void SetMana(int mana) {
        this.Mana = mana;
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


}
