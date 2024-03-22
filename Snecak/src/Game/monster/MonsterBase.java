package Game.monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class MonsterBase {

    public int HP;
    public int GroupLevel;
    public int MonsterXp;
    public String Name;
    private MonsterTier tier;

    private boolean isEntangled;
    private int entangleDuration;
    private int misdirectedDuration;
    private int tauntDuration;
    private boolean isTaunted;
    private boolean isMisdirected;

    public MonsterBase(MonsterTier tier, int groupLevel) {
        this.tier = tier;
        this.GroupLevel = groupLevel;
        this.Name = getRandomName(tier.getNames());
        this.HP = tier.calculateHP(groupLevel);
        this.MonsterXp = tier.calculateXP(groupLevel);
    }


    private String getRandomName(List<String> names) {
        Random random = new Random();
        return names.get(random.nextInt(names.size()));
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

    public MonsterTier getTier() {
        return tier;
    }

    protected ArrayList<String> names;


    public int attackReduction = 1;
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

    public abstract void InitializeName();

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
    public int getEntangledDuration() {
        return entangleDuration;
    }
    public int getTauntedDuration() {
        return tauntDuration;
    }
    public int getMisdirectedDuration() {
        return misdirectedDuration;
    }

    public boolean isTaunted() {
        return isTaunted;
    }

    public void setTaunted(boolean taunted, int tauntRounds) {
        isTaunted = taunted;
    }
    public boolean isMisdirected() {

        return isMisdirected;
    }
    public void setMisdirected(boolean misdirected, int misdirectedRounds) {
        isMisdirected = misdirected;
    }

}