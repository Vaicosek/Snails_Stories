package players;

import hero.*;
import itemshandling.ItemBase;


import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private HeroTemplate hero;
    private String name;

    private List<ItemBase> inventory;

    public Player() {
        this.inventory = new ArrayList<ItemBase>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemBase> getInventory() {
        return inventory;
    }

    public void addItem(ItemBase item) {
        inventory.add(item);
    }

    public void removeItem(ItemBase item) {
        inventory.remove(item);
    }

    public boolean hasItem(ItemBase item) {
        return inventory.contains(item);
    }

    public boolean hasItemByName(String itemName) {
        for (ItemBase item : inventory) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }


    public void increaseXP(int amount) {
        var actualXp = hero.getXP() + amount;
        if (actualXp >= 100) {
            LevelUp();
            hero.setLevel(hero.getLevel() + 1);
            hero.setXP(0);

        }
    }

    private void LevelUp() {
        hero.setHP((hero.getLevel() * 100));
      //  System.out.println("LVL UP");
       // hero.gainAbility();
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
            case 1 -> this.hero = new Assassin(0, 1, 100);
            case 2 -> this.hero = new Defender(0, 1, 100);
            case 3 -> this.hero = new Healer(0, 1, 100);
            case 4 -> this.hero = new Wizard(0, 1, 100);
            case 5 -> this.hero = new Druid(0, 1, 100);
            default -> {
                System.out.println("Invalid choice, please try again.");
                pickHero();
            }
        }
    }

    public HeroTemplate getHero() {
        return hero;
    }
}
