package monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class MonsterBase {

    private static final int TIER_1 = 1;
    private static final int TIER_2 = 2;
    private static final int TIER_3 = 3;
    private static final int TIER_4 = 4;
    private static final int TIER_5 = 5;

    public int HP;
    public int GroupLevel;
    public int tier;
    public int MonsterXp;

    public String Name;

    private boolean isEntangled;
    private int entangleDuration;



    public MonsterBase(int tier, int GroupLevel) {
        this.tier = tier;
        InitializeName();
        Name = getRandomName();
        this.GroupLevel = GroupLevel;


        switch (tier) {
            case TIER_1 -> {
                HP = 20 * GroupLevel;
                MonsterXp = 10 + GroupLevel;
            }
            case TIER_2 -> {
                HP = 30 * GroupLevel;
                MonsterXp = 20 + (GroupLevel * 2);
            }
            case TIER_3 -> {
                HP = 40 * GroupLevel;
                MonsterXp = 30 + (GroupLevel * 2);
            }
            case TIER_4 -> {
                HP = 50 * GroupLevel;
                MonsterXp = 50 + (GroupLevel * 2);
            }
            case TIER_5 -> {
                HP = 1000 * GroupLevel;
                MonsterXp = 200 + (GroupLevel * 10);
            }

        }
    }
    public static MonsterBase chooseMonster(List<MonsterBase> monsters) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a monster to attack:");
        int i = 1;
        for (MonsterBase monster : monsters) {
            System.out.printf("%d. %s (level %d)%n", i, monster.getName(), monster.getGroupLevel());
            i++;
        }
        int choice = scanner.nextInt();
        while (choice < 1 || choice > monsters.size()) {
            System.out.println("Invalid choice. Choose a number between 1 and " + monsters.size() + ":");
            choice = scanner.nextInt();
        }
        return monsters.get(choice - 1);
    }

    public int getGroupLevel() {
        return GroupLevel;
    }

    public String getName() {
        return Name;
    }

    public int getTier() {
        return tier;
    }

    protected ArrayList<String> names;

    String getRandomName() {
        int i;
        try {
            i = Dice.getNextNumber(1, names.size() - 1);
            return names.get(i);
        } catch (Exception e) {
            return "";
        }
    }
    public int attackReduction = 0;
    public int Attack() {
        int damage;

        if (tier == 5) {
            // If it's tier 5, set damage between 20 and 60
            damage = Dice.getNextNumber(20, 60)* attackReduction;
        } else {
            // For other tiers, use the default damage calculation
            damage = Dice.getNextNumber(1, tier * 10 + (10 * GroupLevel))* attackReduction;
        }

        return damage;
    }
    public void reduceAttack(int damageReduction) {
        if (damageReduction < 0) {
            throw new IllegalArgumentException("Damage reduction cannot be negative.");
        }
        attackReduction = damageReduction;
    }
    public int getHP() {
        return HP;
    }

    abstract void InitializeName();

    public void takeDamage(int damageDealt) {

        if (damageDealt < 0) {
            throw new IllegalArgumentException("Damage dealt cannot be negative.");
        }


        HP -= damageDealt;


        if (HP <= 0) {
            HP = 0;
        }
    }

    public boolean isEntangled() {
        return isEntangled;
    }
    public void setEntangled(boolean entangled, int duration) {
        isEntangled = entangled;
        if (entangled) {
            entangleDuration = duration; // Set the entanglement duration
        }
    }
    public int getDuration() {
        return entangleDuration;
    }
}