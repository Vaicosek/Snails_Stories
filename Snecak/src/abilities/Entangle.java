package abilities;

import hero.HeroTemplate;
import monster.MonsterBase;

import java.util.List;
import java.util.Scanner;

public class Entangle extends AbilityBase {
    private int duration = 1;

    public Entangle() {
        setName("Entangle");
        setManaCost(30);
    }


    public void use(HeroTemplate hero, List<MonsterBase> monsters) {
        // Implement logic for using Entangle on a monster
        MonsterBase selectedMonster = chooseMonsterToEntangle(monsters);
        System.out.println(hero.getName() + " used " + getName() + " on " + selectedMonster.getName() + "!");
        selectedMonster.setEntangled(true, duration);
    }

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

        return monsters.get(choice - 1);
    }
}
