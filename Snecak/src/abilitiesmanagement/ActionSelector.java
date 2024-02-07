package abilitiesmanagement;

import abilities.*;
import heroalliedEntities.AllyEntityFactory;
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

    }


    private static void performAbility(Player player, List<MonsterBase> monsters) {
        System.out.println("Choose an ability:");
        List<AbilityBase> abilities = player.getHero().getAbilities();
        for (int i = 0; i < abilities.size(); i++) {
            AbilityBase ability = abilities.get(i);
            System.out.printf("%d. %s%n", i + 1, ability.getName());
        }

        // Prompt the player to choose an ability
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        AbilityBase selectedAbility = null;

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
                // Handle area effect abilities
                handleAreaEffectAbility(player, monsters, selectedAbility);
            } else if (selectedAbility.isSpellTaunt()) {
                // Handle taunt abilities
                handleTauntAbility(player, monsters, selectedAbility);
            } else if (selectedAbility.isEntitySpell()){
                handleEntitySpell(player, monsters, selectedAbility);
            } else if (selectedAbility.isEntangleAbility()) {
                handleEntangleAbility(player, monsters, selectedAbility);
            } else if (selectedAbility.isMisdirectionAbility()) {
                handleMisdirectionAbility(player,monsters, selectedAbility);
            } else {
                handleNormalAbility(player, monsters, selectedAbility);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid ability number.");
            performAbility(player, monsters); // Recursively call the method to prompt again
        }
    }

    private static void handleEntitySpell(Player player, List<MonsterBase> monsters, AbilityBase entityAbility) {

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
        areaEffectAbility.use(player.getHero(), monsters);

        // Deal damage to monsters
        for (MonsterBase monster : monsters) {
            int damageDealt = areaEffectAbility.getDamage();
            monster.HP -= damageDealt;
            System.out.printf("%s used %s and hit %s for %d damage!%n", player.getName(), areaEffectAbility.getName(), monster.getName(), damageDealt);

            player.getHero().usePassiveMonsterAbilities(monster, 0);

        }
    }


    private static void handleTauntAbility(Player player, List<MonsterBase> monster, AbilityBase tauntAbility) {

        tauntAbility.use(player.getHero(), monster);
    }

    private static void handleEntangleAbility(Player player, List<MonsterBase> monster, AbilityBase entangleAbility) {

        entangleAbility.use(player.getHero(), monster);
    }

    private static void handleMisdirectionAbility(Player player, List<MonsterBase> monster, AbilityBase misdirectionAbility) {

        misdirectionAbility.use(player.getHero(), monster);
    }

    private static void handleNormalAbility(Player player, List<MonsterBase> monsters, AbilityBase normalAbility) {

        normalAbility.use(player.getHero(), monsters);

        MonsterBase currentMonster = monsters.size() == 1 ? monsters.get(0) : MonsterBase.chooseMonster(monsters);
        int damageDealt = normalAbility.getDamage();
        currentMonster.HP -= damageDealt;

        System.out.printf("%s used %s and hit %s for %d damage!%n", player.getName(), normalAbility.getName(), currentMonster.getName(), damageDealt);

        player.getHero().usePassiveMonsterAbilities(currentMonster, 0);

    }

}
