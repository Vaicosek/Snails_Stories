package abilities;

import hero.HeroTemplate;
import monster.MonsterBase;

import java.util.List;
import java.util.Scanner;

public class MagicalProtection extends AbilityBase {
    public MagicalProtection() {
        setName("MagicalProtection");
    }

    public void use(HeroTemplate hero, List<MonsterBase> monsters) {
        Scanner scanner = new Scanner(System.in);

        int currentHp = hero.getHP();
        int mana = hero.getMana();

        System.out.println("Enter the amount of mana you want to convert to temporary HP (maximum 100):");
        int manaToConvert = scanner.nextInt();
        int maxAllowedBonus = 100;

        if (manaToConvert > mana) {
            System.out.println("You don't have enough mana for this conversion.");
        } else if (manaToConvert > maxAllowedBonus) {
            System.out.println("You can only convert up to 100 mana to temporary HP.");
            manaToConvert = maxAllowedBonus;
        } else {
            int temporaryHp = currentHp + manaToConvert;

            // Ensure temporary HP doesn't exceed the maximum allowed
            hero.setHP(Math.min(temporaryHp, maxAllowedBonus));

            int remainingMana = mana - manaToConvert;
            hero.setMana(remainingMana);

            System.out.println("Converted " + manaToConvert + " mana to temporary HP using " + getName() + "!");
        }
    }
}
