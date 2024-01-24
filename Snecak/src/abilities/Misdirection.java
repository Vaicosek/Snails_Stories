package abilities;

import hero.HeroTemplate;
import monster.Dice;
import monster.MonsterBase;

import java.util.List;
import java.util.Scanner;

public class Misdirection extends HeroAbility {
    private int misdirectionDuration;

    public Misdirection() {
        setName("Misdirection");
        setManaCost(40); // Set an appropriate mana cost
        misdirectionDuration = 2; // Set an appropriate duration for misdirection
    }


    public void use(HeroTemplate hero, List<MonsterBase> monsters) {
        // Implement logic for using Misdirection on a monster
        setDamage (Dice.getNextNumber(3,5 + hero.getLevel() * 2));
        MonsterBase selectedMonster = chooseMonsterToMisdirect(monsters);
        System.out.println(hero.getName() + " used " + getName() + " on " + selectedMonster.getName() + "!");
        selectedMonster.setMisdirected(true, misdirectionDuration);
    }

    private MonsterBase chooseMonsterToMisdirect(List<MonsterBase> monsters) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a monster to misdirect:");
        int i = 1;
        for (MonsterBase monster : monsters) {
            System.out.printf("%d. %s (HP: %d)%n", i, monster.getName(), monster.getHP());
            i++;
        }

        int choice = scanner.nextInt();
        while (choice < 1 || choice > monsters.size()) {
            System.out.println("Invalid choice. Choose a number between 1 and " + monsters.size() + ":");
            choice = scanner.nextInt();
        }

        return monsters.get(choice - 1);
    }
}
