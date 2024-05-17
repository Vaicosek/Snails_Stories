package game.monster;

import lombok.Getter;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

public class MonsterBase {

    @Getter
    public int hp;
    @Getter
    public int groupLevel;
    @Getter
    public int monsterXp;
    @Getter
    public String name;
    @Getter
    private final MonsterTier tier;

    private boolean isEntangled;
    private int entangleDuration;
    @Getter
    private int misdirectedDuration;
    private int tauntDuration;
    private boolean isTaunted;
    private boolean isMisdirected;
    private  Random random;
    private static final Logger logger = Logger.getLogger(MonsterBase.class.getName());

    public MonsterBase(String name, int groupLevel, int hp, int monsterXp, MonsterTier tier) {
        this.name = name;
        this.groupLevel = groupLevel;
        this.hp = hp;
        this.monsterXp = monsterXp;
        this.tier = tier; // Set the tier
    }


    private String getRandomName(List<String> names) {

        return names.get(random.nextInt(names.size()));
    }


    public static MonsterBase chooseMonster(List<MonsterBase> monsters) {
        Scanner scanner = new Scanner(System.in);
        logger.info("Choose a monster to attack:");
        int i = 1;
        for (MonsterBase monster : monsters) {
            logger.info(String.format("%d. %s (level %d)%n", i, monster.getName(), monster.getGroupLevel()));
            i++;
        }
        int choice = scanner.nextInt();
        while (choice < 1 || choice > monsters.size()) {
            logger.info("Invalid choice. Choose a number between 1 and " + monsters.size() + ":");
            choice = scanner.nextInt();
        }
        return monsters.get(choice - 1);
    }


    public int attackReduction = 1;
    public int attack() {
        return this.tier.calculateAttackDamage(this.groupLevel, this.attackReduction);
    }
    public void reduceAttack(int damageReduction) {
        if (damageReduction < 0) {
            throw new IllegalArgumentException("Damage reduction cannot be negative.");
        }
        attackReduction = damageReduction;
    }


    public void takeDamage(int damageDealt) {

        if (damageDealt < 0) {
            throw new IllegalArgumentException("Damage dealt cannot be negative.");
        }


        hp -= damageDealt;


        if (hp <= 0) {
            hp = 0;
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