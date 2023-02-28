package hero;

import abilities.HeroAbility;

import java.util.ArrayList;
import java.util.Scanner;

public class Hero implements HeroTemplate {
    protected int XP;
    protected int level;
    protected int HP;
    protected String name;
    protected int Attack;
    protected int Defence;
    protected double Mana;
    protected ArrayList<HeroAbility> Abilities = new ArrayList<HeroAbility>();

    public Hero(int XP, int level, int HP) {
        this.XP = XP;
        this.level = level;
        this.HP = HP;

    }
    public void powers() {
        System.out.println("Possible abilities:");
        for (HeroAbility ability : Abilities) {
            System.out.print(ability.Name);

            if (ability.IsUnlocked) {
                System.out.println(" (already acquired)");
            } else {
                System.out.println(" (not yet acquired)");
            }
        }
    }

    public void gainAbility() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You can gain the  ability by pressing number");

        if (CheckAllAbilitiesUnlocked()) {
            System.out.println("You have unlocked all abilities");
            return;
        }

        for (int i = 0; i < Abilities.size(); i++) {

            HeroAbility ability = Abilities.get(i);
            if (!ability.IsUnlocked) {
                System.out.println(i + ". " + ability.Name);

            }
        }
        System.out.println("Enter the name of the ability you want to gain, or press 'q' to quit:");
        String abilityName = scanner.nextLine();

        if (abilityName.equalsIgnoreCase("q")) {
            return;
        }
    }

    private boolean CheckAllAbilitiesUnlocked() {
        boolean allUnlocked = true;
        for (HeroAbility ability : Abilities) {
            if (!ability.IsUnlocked) {
                allUnlocked = false;
                break;
            }
        }

        return allUnlocked;
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
    public int getDefense() {
        return Defence;
    }

    @Override
    public void SetMana(int mana) {
        this.Mana = mana;
    }
}
