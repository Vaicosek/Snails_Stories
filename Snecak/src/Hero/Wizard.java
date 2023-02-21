package Hero;

import Abilities.HeroAbility;
import Abilities.MagicalPower;

import java.sql.Array;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Scanner;

public class Wizard implements HeroTemplate {
    //private EnumMap<Ability, Object> abilities;
    private int XP;
    private int level;
    private int HP;
    private int Attack;
    private int Defence;

    public double Mana = 0;

    // public enum Ability { Scrolls, MagicalPower, BookWorm, FocusI, FocusII, IceFire, TranslationRunes, Illusion, Artefacts, Conjurer }
public ArrayList<HeroAbility> Abilities = new ArrayList<HeroAbility>();

    public Wizard(int XP, int level, int HP) {
        this.XP = XP;
        this.level = level;
        this.HP = HP;
        Abilities.add(new MagicalPower(this));
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

        if(CheckAllAbilitiesUnlocked()==true){
            System.out.println("You have unlocked all abilities");
            return ;
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

//            try {
//
//                if (hasAbility(chosenAbility)) {
//                    System.out.println("You already have the " + chosenAbility + " ability.");
//                } else {
//                    abilities.put(chosenAbility, true);
//                    System.out.println("You have gained the " + chosenAbility + " ability.");
//                }
//            } catch (IllegalArgumentException e) {
//                System.out.println("Invalid ability name.");
//            }
//
   }
    private boolean CheckAllAbilitiesUnlocked() {
        Boolean allUnlocked = true;
        for (HeroAbility ability : Abilities) {
            if (ability.IsUnlocked == false) {
                allUnlocked = false;
                break;
            }
        }

        if (allUnlocked == true) {
            return true;
        }
        return false;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

//    public boolean hasAbility(Enum ability) {
//        return (boolean) abilities.get(ability);
//    }

    @Override
    public void SetMana(int Mana) {
        this.Mana = Mana;
    }

    @Override
    public int getAttack() {
        return Attack;
    }

    @Override
    public int getDefense() {
        return Defence;
    }

}
