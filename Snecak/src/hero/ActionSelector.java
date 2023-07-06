package hero;

import abilities.HeroAbility;
import itemshandling.ItemBase;
import monster.MonsterBase;
import players.Player;

import java.util.List;
import java.util.Scanner;

public class ActionSelector {
    public static void chooseAction(Player player, List<MonsterBase> monsters) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your action:");
        System.out.println("1. Attack");
        System.out.println("2. Use an ability");

        String input = scanner.nextLine().trim();

        switch (input) {
            case "1" -> performAttack(player, monsters);
            case "2" -> performAbility(player, monsters);
            default -> {
                System.out.println("Invalid input. Please choose a valid action.");
                chooseAction(player, monsters);
            }
        }
    }

    private static void performAttack(Player player, List<MonsterBase> monsters) {
        if (player.getHero().getHP() <= 0) {
            return;
        }

        MonsterBase currentMonster = monsters.size() == 1 ? monsters.get(0) : MonsterBase.chooseMonster(monsters);
        int damageDealt = player.getHero().getAttack();
        currentMonster.HP -= damageDealt;
        System.out.printf("%s hit %s for %d damage!%n", player.getName(), currentMonster.getName(), damageDealt);
        if (currentMonster.getHP() <= 0) {
            System.out.printf("%s has been defeated!%n", currentMonster.getName());
            System.out.printf("%s gained %d XP!%n", player.getName(), currentMonster.MonsterXp);
            player.increaseXP(currentMonster.MonsterXp);
            monsters.remove(currentMonster);
            ItemBase.DropItem(player, player.getInventory());
        }
    }

    private static void performAbility(Player player, List<MonsterBase> monsters) {
        System.out.println("Choose an ability:");
        List<HeroAbility> abilities = player.getHero().getAbilities();
        for (int i = 0; i < abilities.size(); i++) {
            HeroAbility ability = abilities.get(i);
            System.out.println((i + 1) + ". " + ability.getName());
        }

        // Prompt the player to choose an ability
        Scanner scanner = new Scanner(System.in);
        int abilityIndex = scanner.nextInt();

        // Validate the ability index
        if (abilityIndex < 1 || abilityIndex > abilities.size()) {
            System.out.println("Invalid ability selection.");
            performAbility(player, monsters); // Recursively call the method to prompt again
            return;
        }

        // Use the selected ability
        HeroAbility selectedAbility = abilities.get(abilityIndex - 1);
        selectedAbility.use(player.getHero());

        MonsterBase currentMonster = monsters.size() == 1 ? monsters.get(0) : MonsterBase.chooseMonster(monsters);
        int damageDealt = selectedAbility.getDamage();
        currentMonster.HP -= damageDealt;
        System.out.printf("%s used %s and hit %s for %d damage!%n", player.getName(), selectedAbility.getName(), currentMonster.getName(), damageDealt);
        if (currentMonster.getHP() <= 0) {
            System.out.printf("%s has been defeated!%n", currentMonster.getName());
            System.out.printf("%s gained %d XP!%n", player.getName(), currentMonster.MonsterXp);
            player.increaseXP(currentMonster.MonsterXp);
            monsters.remove(currentMonster);
            ItemBase.DropItem(player, player.getInventory());
        }
    }
}
