package game.players;

import game.hero.*;
import game.mapvariables.PositionModel;
import game.abilities.EntityAbilityTemplate;
import game.abilitiesmanagement.HeroAbilitiesManager;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

@Getter
public class Player {
    private HeroTemplate hero;
    @Setter
    private String name;
    private static final Logger logger = Logger.getLogger(Player.class.getName());
    @Setter
    private PositionModel previousPosition;

    private List<EntityAbilityTemplate> summonedEntities;

    public Player() {

    }


    public void increaseXP(int amount) {
        int actualXp = hero.getXp() + amount;
        if (actualXp >= 100) {
            levelUp();
            hero.setLevel(hero.getLevel() + 1);
            hero.setXp(0);

        }
    }

    public void levelUp() {
        hero.setHp((hero.getLevel() * 100));
        logger.info("LVL UP");
        hero.gainAbility();
    }

    public boolean isAlive() {
        return hero.getHp() > 0;
    }

    public void pickHero() {
        Scanner scanner = new Scanner(System.in);
        logger.info("Please choose your hero by pressing number:");
        logger.info("1. Assassin");
        logger.info("2. Defender");
        logger.info("3. Healer");
        logger.info("4. Wizard");
        logger.info("5. Druid");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> this.hero = new Assassin(0, 1, 100, HeroAbilitiesManager.getAbilitiesForHero("Assassin"));
            case 2 -> this.hero = new Defender(0, 1, 100, HeroAbilitiesManager.getAbilitiesForHero("Defender"));
            case 3 -> this.hero = new Healer(0, 1, 100, HeroAbilitiesManager.getAbilitiesForHero("Healer"));
            case 4 -> this.hero = new Wizard(0, 1, 100, HeroAbilitiesManager.getAbilitiesForHero("Wizard"));
            case 5 -> this.hero = new Druid(0, 1, 100, HeroAbilitiesManager.getAbilitiesForHero("Druid"));
            default -> {
                logger.info("Invalid choice, please try again.");
                pickHero();
            }
        }
    }
    private void gainStartingAbility() {
        hero.gainAbility();
    }


    public void summonEntity(EntityAbilityTemplate entity) {
        summonedEntities.add(entity);
        logger.info(String.format("You have summoned %s!%n", entity.getEntityName()));
    }
}
