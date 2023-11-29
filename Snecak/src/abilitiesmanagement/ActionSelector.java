package abilitiesmanagement;

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

        // Trigger passive effects after the attack
        player.getHero().usePassiveMonsterAbilities(currentMonster, 0);

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
            System.out.printf("%d. %s%n", i + 1, ability.getName());
        }

        // Prompt the player to choose an ability
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        HeroAbility selectedAbility = null;

        try {
            int abilityIndex = Integer.parseInt(input);
            // Validate the ability index
            if (abilityIndex < 1 || abilityIndex > abilities.size()) {
                System.out.println("Invalid ability selection.");
                performAbility(player, monsters); // Recursively call the method to prompt again
                return;
            }

            // Use the selected ability
            selectedAbility = abilities.get(abilityIndex - 1);

            if (selectedAbility.isSpellAreaEffect()) {
                HeroAbility finalSelectedAbility = selectedAbility;
                monsters.forEach(monster -> {
                    finalSelectedAbility.use(player.getHero());
                    int damageDealt = finalSelectedAbility.getDamage();
                    monster.HP -= damageDealt;
                    System.out.printf("%s used %s and hit %s for %d damage!%n", player.getName(), finalSelectedAbility.getName(), monster.getName(), damageDealt);
                    player.getHero().usePassiveMonsterAbilities(monster, 0);

                    if (monster.getHP() <= 0) {
                        System.out.printf("%s has been defeated!%n", monster.getName());
                        System.out.printf("%s gained %d XP!%n", player.getName(), monster.MonsterXp);
                        player.increaseXP(monster.MonsterXp);
                    }
                });

                monsters.removeIf(monster -> monster.getHP() <= 0);
                monsters.forEach(monster -> ItemBase.DropItem(player, player.getInventory()));
            }

             else {
                selectedAbility.use(player.getHero());

                MonsterBase currentMonster = monsters.size() == 1 ? monsters.get(0) : MonsterBase.chooseMonster(monsters);
                int damageDealt = selectedAbility.getDamage();
                currentMonster.HP -= damageDealt;

                System.out.printf("%s used %s and hit %s for %d damage!%n", player.getName(), selectedAbility.getName(), currentMonster.getName(), damageDealt);

                player.getHero().usePassiveMonsterAbilities(currentMonster, 0);

                if (currentMonster.getHP() <= 0) {
                    System.out.printf("%s has been defeated!%n", currentMonster.getName());
                    System.out.printf("%s gained %d XP!%n", player.getName(), currentMonster.MonsterXp);
                    player.increaseXP(currentMonster.MonsterXp);
                    monsters.remove(currentMonster);
                    ItemBase.DropItem(player, player.getInventory());
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid ability number.");
            performAbility(player, monsters); // Recursively call the method to prompt again
        }
    }
}
