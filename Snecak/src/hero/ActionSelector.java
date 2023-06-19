package hero;

import abilities.HeroAbility;
import players.Player;

import java.util.List;
import java.util.Scanner;

public class ActionSelector {
    public static void chooseAction(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your action:");
        System.out.println("1. Attack");
        System.out.println("2. Use an ability");

        String input = scanner.nextLine().trim();

        switch (input) {
            case "1" -> performAttack(player);
            case "2" -> performAbility(player);
            default -> {
                System.out.println("Invalid input. Please choose a valid action.");
                chooseAction(player);
            }
        }
    }

    private static void performAttack(Player player) {

    }

    private static void performAbility(Player player) {

        System.out.println("Choose an ability:");
        for (int i = 0; i < player.getHero().getAbilities().size(); i++) {
            HeroAbility ability = player.getHero().getAbilities().get(i);
            System.out.println((i + 1) + ". " + ability.Name);
        }

        // Prompt the player to choose an ability
        Scanner scanner = new Scanner(System.in);
        int abilityIndex = scanner.nextInt();

        // Validate the ability index
        if (abilityIndex < 1 || abilityIndex > player.getHero().getAbilities().size()) {
            System.out.println("Invalid ability selection.");
            performAbility(player); // Recursively call the method to prompt again
            return;
        }

        // Use the selected ability
        HeroAbility selectedAbility = player.getHero().getAbilities().get(abilityIndex - 1);
        selectedAbility.Use();


    }
}