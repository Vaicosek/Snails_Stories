package game.monster;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MonsterBase {

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

    public MonsterBase(String name, int groupLevel, int hp, int monsterXp, MonsterTier tier) {
        this.Name = name;
        this.GroupLevel = groupLevel;
        this.HP = hp;
        this.MonsterXp = monsterXp;
        this.tier = tier; // Set the tier
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


    public int attackReduction = 1;
    public int Attack() {
        int damage = this.tier.calculateAttackDamage(this.GroupLevel, this.attackReduction);
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