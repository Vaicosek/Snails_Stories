import item.Item;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private HeroTemplate hero;
    private String name;
    private int x;
    private int y;


    private List<Item> inventory;

    public Player() {
        Map map = new Map();
        this.x = map.Width / 2;
        this.y = map.Height / 2;
        this.inventory = new ArrayList<Item>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public boolean hasItem(Item item) {
        return inventory.contains(item);
    }

    public boolean hasItemByName(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }


    public void increaseXP(int amount) {
        var actualXp = hero.getXP() + amount;
        if (actualXp >= 100) {
            hero.setLevel(hero.getLevel() + 1); ;
            hero.setXP(0) ;
        }
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
