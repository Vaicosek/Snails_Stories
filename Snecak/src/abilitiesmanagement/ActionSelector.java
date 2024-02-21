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
        // Assuming getAbilities() now returns a List of AbilityTemplate
        List<AbilityTemplate> abilities = player.getHero().getAbilities();
        for (int i = 0; i < abilities.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, abilities.get(i).getName());
        }

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        try {
            int abilityIndex = Integer.parseInt(input) - 1;
            if (abilityIndex < 0 || abilityIndex >= abilities.size()) {
                System.out.println("Invalid ability selection.");
                performAbility(player, currentMonster, monsters);
                return;
            }

            AbilityTemplate selectedAbility = abilities.get(abilityIndex);

            // Handle different types of abilities
            if (selectedAbility instanceof AreaAbilityTemplate) {
                handleAreaEffectAbility(player, monsters, (AreaAbilityTemplate) selectedAbility);
            } else if (selectedAbility instanceof TauntAbilityTemplate) {
                handleTauntAbility(player, currentMonster, (TauntAbilityTemplate) selectedAbility);
            } else if (selectedAbility instanceof EntityAbilityTemplate) {
                handleEntitySpell(player, selectedAbility);
            } else if (selectedAbility instanceof EntangleAbilityTemplate) {
                handleEntangleAbility(player, currentMonster, (EntangleAbilityTemplate) selectedAbility);
            } else if (selectedAbility instanceof MisdirectionAbilityTemplate) {
                handleMisdirectionAbility(player, monsters, (MisdirectionAbilityTemplate) selectedAbility);
            } else if (selectedAbility instanceof NormalAbilityTemplate) {
                handleNormalAbility(player, currentMonster, (NormalAbilityTemplate) selectedAbility);
            }  if (selectedAbility instanceof TickAbilityTemplate) {
                handleTickEffectAbility(player, currentMonster, monsters, (TickAbilityTemplate) selectedAbility);
            } else {
                System.out.println("This ability type is not supported yet.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            performAbility(player, currentMonster, monsters);
        }
    }

    private static void handleTickEffectAbility(Player player, MonsterBase currentMonster, List<MonsterBase> monsters, TickAbilityTemplate tickAbility) {
        if (!tickAbility.isEffectActive()) {
            tickAbility.cast(player.getHero(), currentMonster); // Initial cast
            System.out.printf("%s uses %s, applying effect for %d turns.%n", player.getName(), tickAbility.getName(), tickAbility.getRemainingTurns());
        } else {
            System.out.println("Effect is already active.");
        }
    }


    private static void handleEntitySpell(Player player, AbilityTemplate entityAbility) {

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

    private static void handleAreaEffectAbility(Player player, List<MonsterBase> monsters, AreaAbilityTemplate areaEffectAbility) {
        // Implement logic for area effect abilities
        areaEffectAbility.castAreaEffect(player.getHero(), monsters, player);

        // Deal damage to monsters
        for (MonsterBase monster : monsters) {
            int damageDealt = areaEffectAbility.getDamage();
            monster.HP -= damageDealt;
            System.out.printf("%s used %s and hit %s for %d damage!%n", player.getName(), areaEffectAbility.getName(), monster.getName(), damageDealt);

            player.getHero().usePassiveMonsterAbilities(monster, 0);

        }
    }


    private static void handleTauntAbility(Player player, MonsterBase currentMonster, TauntAbilityTemplate tauntAbility) {

        tauntAbility.applyTaunt(player.getHero(), currentMonster);
    }

    private static void handleEntangleAbility(Player player, MonsterBase currentMonster, EntangleAbilityTemplate entangleAbility) {

        entangleAbility.applyEntangle(player.getHero(), currentMonster);
    }

    private static void handleMisdirectionAbility(Player player, List<MonsterBase> monsters, MisdirectionAbilityTemplate misdirectionAbility) {

        misdirectionAbility.misdirect(player.getHero(), monsters);
    }

    private static void handleNormalAbility(Player player, MonsterBase currentMonster, NormalAbilityTemplate normalAbility) {
        normalAbility.cast(player.getHero(), currentMonster);
            System.out.printf("%s used %s and hit %s for %d damage!%n", player.getName(), normalAbility.getName(), currentMonster.getName(), normalAbility.getDamage());
            player.getHero().usePassiveMonsterAbilities(currentMonster, 0);
        }


    }


