package abilities;

import monster.MonsterBase;

import java.util.List;
import java.util.Scanner;

public class Sleep extends HeroAbility {
    private static final int SLEEP_DURATION = 2;

    public Sleep() {
        setName("Sleep");
        // Set an appropriate mana cost for Sleep
        setManaCost(33);
    }

    public void use(List<MonsterBase> monsters) {
        MonsterBase chosenMonster = chooseMonsterToSleep(monsters);

        if (chosenMonster != null) {
            applySleepEffect(chosenMonster);
        }
    }

    private MonsterBase chooseMonsterToSleep(List<MonsterBase> monsters) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a monster to put to sleep:");
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

    private void applySleepEffect(MonsterBase targetMonster) {



        System.out.printf("%s used Sleep! %s is now asleep for %d round(s)!\n",
                targetMonster.getName(), SLEEP_DURATION);
    }
}
