package abilities;

import hero.HeroTemplate;
import monster.MonsterBase;

import java.util.List;
import java.util.Scanner;

public class BloodPact extends AbilityBase {
    public BloodPact() {
        setName("Blood Pact");
    }

    public void use(HeroTemplate hero, List<MonsterBase> monsters) {
        Scanner scanner = new Scanner(System.in);

        int currentHp = hero.getHP();
        int attackDamage = hero.getAttack();

        System.out.println("Enter the amount of HP you want to sacrifice:");
        int hpToSacrifice = scanner.nextInt();

        if (hpToSacrifice > currentHp) {
            System.out.println("You don't have enough HP for this sacrifice.");
        } else {
            int increasedDamage = hpToSacrifice * 3; // For every 1 HP, increase attack by 3
            setDamage(increasedDamage+attackDamage);

            int remainingHp = currentHp - hpToSacrifice;
            hero.setHP(remainingHp);

            System.out.println("Sacrificed " + hpToSacrifice + " HP to increase attack by " + increasedDamage + "!");
        }
    }
}
