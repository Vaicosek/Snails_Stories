package players;

import abilities.EntityAbilityTemplate;
import abilitiesmanagement.HeroAbilitiesManager;
import itemshandling.*;
import mapvariables.PositionModel;


import java.util.Scanner;
import java.util.List;

public class Player {
    private hero.HeroTemplate hero;
    private String name;

    private Inventory inventory;

    public Player() {
        this.inventory = new Inventory();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    private PositionModel previousPosition;

    private List<EntityAbilityTemplate> summonedEntities;


    public void increaseXP(int amount) {
        var actualXp = hero.getXP() + amount;
        if (actualXp >= 100) {
            LevelUp();
            hero.setLevel(hero.getLevel() + 1);
            hero.setXP(0);

        }
    }

    public void LevelUp() {
        hero.setHP((hero.getLevel() * 100));
        System.out.println("LVL UP");
       hero.gainAbility();
    }

    public boolean isAlive() {
        return hero.getHP() > 0;
    }

    public void pickHero() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose your hero by pressing number:");
        System.out.println("1. Assassin");
        System.out.println("2. Defender");
        System.out.println("3. Healer");
        System.out.println("4. Wizard");
        System.out.println("5. Druid");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                this.hero = new hero.Assassin(0, 1, 100, HeroAbilitiesManager.getAssassinAbilities());

            }
            case 2 -> {
                this.hero = new hero.Defender(0, 1, 100, HeroAbilitiesManager.getDefenderAbilities());

            }
            case 3 -> {
                this.hero = new hero.Healer(0, 1, 100, HeroAbilitiesManager.getHealerAbilities());

            }
            case 4 -> {
                this.hero = new hero.Wizard(0, 1, 100, HeroAbilitiesManager.getWizardAbilities());

            }
            case 5 -> {
                this.hero = new hero.Druid(0, 1, 100, HeroAbilitiesManager.getDruidAbilities());

            }
            default -> {
                System.out.println("Invalid choice, please try again.");
                pickHero();
            }
        }
    }
    private void gainStartingAbility() {
        hero.gainAbility();
    }
    public hero.HeroTemplate getHero() {
        return hero;
    }

    public void setPreviousPosition(PositionModel previousPosition) {
        this.previousPosition = previousPosition;
    }

    public PositionModel getPreviousPosition() {
        return previousPosition;
    }


    public void summonEntity(EntityAbilityTemplate entity) {
        summonedEntities.add(entity);
        System.out.printf("You have summoned %s!%n", entity.getEntityName());
    }
    public List<EntityAbilityTemplate> getSummonedEntities() {
        return summonedEntities;
    }
}
