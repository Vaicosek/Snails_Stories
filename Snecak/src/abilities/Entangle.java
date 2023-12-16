package abilities;

import monster.MonsterBase;

import java.util.List;
import java.util.Scanner;

public class Entangle extends HeroAbility {
    public Entangle() {
        setName("Entangle");
        setManaCost(30);
    }
    int duration = 1;

    private MonsterBase chooseMonsterToEntangle(List<MonsterBase> monsters) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a monster to entangle:");
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
        MonsterBase chosenMonster = monsters.get(choice - 1);

        chosenMonster.setEntangled(true, duration);

        return monsters.get(choice - 1);

    }
}
