package game.abilitiesmanagement;

import game.abilities.*;
import game.hero.HeroTemplate;
import game.heroalliedentities.AllyEntityFactory;
import game.monster.MonsterBase;
import game.players.Player;
import java.util.logging.Logger;
import java.util.List;
import java.util.Scanner;




public class ActionSelector {
    private static final Logger logger = Logger.getLogger(ActionSelector.class.getName());

    private ActionSelector() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static void chooseAction(Player player, MonsterBase currentMonster, List<MonsterBase> monsters) {
        Scanner scanner = new Scanner(System.in);
        logger.info("Choose your action:");
        logger.info("1. Attack");
        logger.info("2. Use an ability");

        String input = scanner.nextLine().trim();

        switch (input) {
            case "1" -> performAttack(player, currentMonster);
            case "2" -> performAbility(player, currentMonster, monsters);
            default -> {
                logger.warning("Invalid input. Please choose a valid action.");
                chooseAction(player, currentMonster, monsters);
            }
        }
    }


    private static void performAttack(Player player, MonsterBase currentMonster) {
        if (player.getHero().getHp() <= 0) {
            return;
        }

        int totalDamage = player.getHero().getAttack();

       currentMonster.takeDamage(totalDamage);
        logger.info(String.format("%s hit %s for %d damage!", player.getName(), currentMonster.getName(), totalDamage));

        player.getHero().usePassiveMonsterAbilities(currentMonster);

    }


    public static void performAbility(Player player, MonsterBase currentMonster, List<MonsterBase> monsters) {
        logger.info("Choose an ability:");

        List<AbilityTemplate> abilities = player.getHero().getAbilities();
        for (int i = 0; i < abilities.size(); i++) {
            logger.info(String.format("%d. %s%n", i + 1, abilities.get(i).getName()));
        }

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        try {
            int abilityIndex = Integer.parseInt(input) - 1;
            if (abilityIndex < 0 || abilityIndex >= abilities.size()) {
                logger.warning("Invalid ability selection.");
                performAbility(player, currentMonster, monsters);
                return;
            }

            AbilityTemplate selectedAbility = abilities.get(abilityIndex);

           
            switch (selectedAbility) {
                case AreaAbilityTemplate areaAbilityTemplate ->
                        handleAreaEffectAbility(player, monsters, areaAbilityTemplate);
                case TauntAbilityTemplate tauntAbilityTemplate ->
                        handleTauntAbility(player, currentMonster, tauntAbilityTemplate);
                case EntityAbilityTemplate entityAbilityTemplate -> handleEntitySpell(player, entityAbilityTemplate);
                case EntangleAbilityTemplate entangleAbilityTemplate ->
                        handleEntangleAbility(player, currentMonster, entangleAbilityTemplate);
                case MisdirectionAbilityTemplate misdirectionAbilityTemplate ->
                        handleMisdirectionAbility(player, currentMonster, monsters, misdirectionAbilityTemplate);
                case NormalAbilityTemplate normalAbilityTemplate ->
                        handleNormalAbility(player, currentMonster, normalAbilityTemplate);
                case TickAbilityTemplate tickAbilityTemplate ->
                        handleTickEffectAbility(player, currentMonster, monsters, tickAbilityTemplate);
                case ThrowAbilityTemplate throwAbilityTemplate ->
                        handleThrowAbility(player, player.getHero(), currentMonster, throwAbilityTemplate);
                case null, default -> logger.warning("This ability type is not supported yet.");
            }
        } catch (NumberFormatException e) {
            logger.warning("Invalid input. Please enter a number.");
            performAbility(player, currentMonster, monsters);
        }
    }

    private static void handleThrowAbility(Player player, HeroTemplate hero, MonsterBase currentMonster, ThrowAbilityTemplate throwAbility) {
        throwAbility.cast(player,hero,currentMonster);
    }

    private static void handleTickEffectAbility(Player player, MonsterBase currentMonster, List<MonsterBase> monsters, TickAbilityTemplate tickAbility) {
        if (!tickAbility.isEffectActive()) {
            tickAbility.cast(player.getHero(), currentMonster, monsters); // Initial cast
            logger.info(String.format("%s uses %s, applying effect for %d turns.%n", player.getName(), tickAbility.getName(), tickAbility.getRemainingTurns()));
        } else {
            logger.warning("Effect is already active.");
        }
    }


    private static void handleEntitySpell(Player player, EntityAbilityTemplate entityAbility) {
        String summons = "%s summoned %s!%n";

        switch (entityAbility.getName().toLowerCase()) {
            case "animal companion":
                AnimalCompanion summonedAnimal = AllyEntityFactory.createAnimalCompanion(player.getHero());
                player.summonEntity(summonedAnimal);
                logger.info(String.format(summons, player.getName(), summonedAnimal.getEntityName()));
                break;
            case "conjurer":
                Conjurer summonedConjurer = AllyEntityFactory.createConjuredEntity(player.getHero());
                player.summonEntity(summonedConjurer);
                logger.info(String.format(summons, player.getName(), summonedConjurer.getEntityName()));
                break;
            case "shadow clone":
                ShadowClone summonedShadowClone = AllyEntityFactory.createShadowClone(player.getHero());
                player.summonEntity(summonedShadowClone);
                logger.info(String.format(summons, player.getName(), summonedShadowClone.getEntityName()));
                break;
            case "illusion":
                Illusion summonedIllusion = AllyEntityFactory.createIllusion(player.getHero());
                player.summonEntity(summonedIllusion);
                logger.info(String.format(summons, player.getName(), summonedIllusion.getEntityName()));
                break;
            default:
                logger.warning("Invalid entity variant.");
                break;
        }
    }

    private static void handleAreaEffectAbility(Player player, List<MonsterBase> monsters, AreaAbilityTemplate areaEffectAbility) {

        areaEffectAbility.castAreaEffect(player.getHero(), monsters, player);

        }

    private static void handleTauntAbility(Player player, MonsterBase currentMonster, TauntAbilityTemplate tauntAbility) {

        tauntAbility.applyTaunt(player.getHero(), currentMonster);
    }

    private static void handleEntangleAbility(Player player, MonsterBase currentMonster, EntangleAbilityTemplate entangleAbility) {

        entangleAbility.applyEntangle(player.getHero(), currentMonster);
    }

    private static void handleMisdirectionAbility(Player player,MonsterBase currentMonster, List<MonsterBase> monsters, MisdirectionAbilityTemplate misdirectionAbility) {

        misdirectionAbility.misdirect(player.getHero(),currentMonster, monsters);
    }

    private static void handleNormalAbility(Player player, MonsterBase currentMonster, NormalAbilityTemplate normalAbility) {
        normalAbility.cast(player.getHero(), currentMonster);

        logger.info(String.format("%s used %s and hit %s for %d damage!%n", player.getName(), normalAbility.getName(), currentMonster.getName(), normalAbility.getDamage()));

        }


    }


