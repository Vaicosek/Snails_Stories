package abilitiesmanagement;

import abilities.*;
import heroalliedEntities.AllyEntityFactory;
import monster.MonsterBase;
import players.Player;

import java.util.List;
import java.util.Scanner;




public class ActionSelector {


    public static void chooseAction(Player player, MonsterBase currentMonster, List<MonsterBase> monsters) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your action:");
        System.out.println("1. Attack");
        System.out.println("2. Use an ability");

        String input = scanner.nextLine().trim();

        switch (input) {
            case "1" -> performAttack(player, currentMonster, monsters);
            case "2" -> performAbility(player, currentMonster, monsters);
            default -> {
                System.out.println("Invalid input. Please choose a valid action.");
                chooseAction(player, currentMonster, monsters);
            }
        }
    }


    private static void performAttack(Player player, MonsterBase currentMonster, List<MonsterBase> monsters) {
        if (player.getHero().getHP() <= 0) {
            return;
        }

        int damageDealt = player.getHero().getAttack();
        currentMonster.HP -= damageDealt;
        System.out.printf("%s hit %s for %d damage!%n", player.getName(), currentMonster.getName(), damageDealt);

        // Trigger passive effects after the attack
        player.getHero().usePassiveMonsterAbilities(currentMonster, 0);

    }


    public static void performAbility(Player player, MonsterBase currentMonster, List<MonsterBase> monsters) {
        System.out.println("Choose an ability:");
        List<AbilityBase> abilities = player.getHero().getAbilities();
        for (int i = 0; i < abilities.size(); i++) {
            AbilityBase ability = abilities.get(i);
            System.out.printf("%d. %s%n", i + 1, ability.getName());
        }

        // Prompt the player to choose an ability
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        try {
            int abilityIndex = Integer.parseInt(input);
            // Validate the ability index
            if (abilityIndex < 1 || abilityIndex > abilities.size()) {
                System.out.println("Invalid ability selection.");
                performAbility(player, currentMonster, monsters); // Recursively call the method to prompt again
                return;
            }



            AbilityBase selectedAbility = abilities.get(abilityIndex - 1);

            if (selectedAbility instanceof Bite) {
                // Update the hero level and monster HP for the Bite ability
                Bite biteAbility = (Bite) selectedAbility;
                biteAbility.calculateDamage(player.getHero(), currentMonster.getHP());
            }

            // Calculate damage for the selected ability
            selectedAbility.calculateDamage(player.getHero(), currentMonster.getHP());

            if (selectedAbility.isSpellAreaEffect()) {
                // Handle area effect abilities
                handleAreaEffectAbility(player, monsters, selectedAbility);
            } else if (selectedAbility.isSpellTaunt()) {
                // Handle taunt abilities
                handleTauntAbility(player, currentMonster, selectedAbility);
            } else if (selectedAbility.isEntitySpell()) {
                handleEntitySpell(player, currentMonster, selectedAbility);
            } else if (selectedAbility.isEntangleAbility()) {
                handleEntangleAbility(player, currentMonster, selectedAbility);
            } else if (selectedAbility.isMisdirectionAbility()) {
                handleMisdirectionAbility(player, currentMonster, selectedAbility);
            } else {
                handleNormalAbility(player, currentMonster, selectedAbility);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid ability number.");
            performAbility(player, currentMonster, monsters); // Recursively call the method to prompt again
        }
    }




    private static void handleEntitySpell(Player player, MonsterBase currentMonster, AbilityBase entityAbility) {

        switch (entityAbility.getName().toLowerCase()) {
            case "animal companion":
                AnimalCompanion summonedAnimal = AllyEntityFactory.createAnimalCompanion(player.getHero());
                player.summonEntity(summonedAnimal);
                System.out.printf("%s summoned %s!%n", player.getName(), summonedAnimal.getEntityName());
                break;
            case "conjurer":
                Conjurer summonedConjurer = AllyEntityFactory.createConjuredEntity(player.getHero());
                player.summonEntity(summonedConjurer);
                System.out.printf("%s summoned %s!%n", player.getName(), summonedConjurer.getEntityName());
                break;
            case "shadow clone":
                ShadowClone summonedShadowClone = AllyEntityFactory.createShadowClone(player.getHero());
                player.summonEntity(summonedShadowClone);
                System.out.printf("%s summoned %s!%n", player.getName(), summonedShadowClone.getEntityName());
                break;
            case "illusion":
                Illusion summonedIllusion = AllyEntityFactory.createIllusion(player.getHero());
                player.summonEntity(summonedIllusion);
                System.out.printf("%s summoned %s!%n", player.getName(), summonedIllusion.getEntityName());
                break;
            default:
                System.out.println("Invalid entity variant.");
                break;
        }
    }

    private static void handleAreaEffectAbility(Player player, List<MonsterBase> monsters, AbilityBase areaEffectAbility) {
        // Implement logic for area effect abilities
        areaEffectAbility.useAreaSpell(player.getHero(), monsters);

        // Deal damage to monsters
        for (MonsterBase monster : monsters) {
            int damageDealt = areaEffectAbility.getDamage();
            monster.HP -= damageDealt;
            System.out.printf("%s used %s and hit %s for %d damage!%n", player.getName(), areaEffectAbility.getName(), monster.getName(), damageDealt);

            player.getHero().usePassiveMonsterAbilities(monster, 0);

        }
    }


    private static void handleTauntAbility(Player player, MonsterBase currentMonster, AbilityBase tauntAbility) {

        tauntAbility.use(player.getHero(), currentMonster);
    }

    private static void handleEntangleAbility(Player player, MonsterBase currentMonster, AbilityBase entangleAbility) {

        entangleAbility.use(player.getHero(), currentMonster);
    }

    private static void handleMisdirectionAbility(Player player, MonsterBase currentMonster, AbilityBase misdirectionAbility) {

        misdirectionAbility.use(player.getHero(), currentMonster);
    }

    private static void handleNormalAbility(Player player, MonsterBase currentMonster, AbilityBase normalAbility) {
        currentMonster.HP -= normalAbility.getDamage(); // Deduct damage from the monster's HP

            System.out.printf("%s used %s and hit %s for %d damage!%n", player.getName(), normalAbility.getName(), currentMonster.getName(), normalAbility.getDamage());
            player.getHero().usePassiveMonsterAbilities(currentMonster, 0);
        }


    }


