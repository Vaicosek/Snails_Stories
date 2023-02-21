import java.util.EnumMap;
import java.util.Scanner;

class Assassin implements HeroTemplate {
    private EnumMap<Ability, Object> abilities;
    private int XP;
    private int level;
    private int HP;

    public enum Ability {Quiet, Shadow, LightArmor, Rage, PoisonI, PoisonII, ShadowClone, Marksman, Smoke, NightOwl}


    public Assassin (int XP, int level, int HP) {
        this.XP = XP;
        this.level = level;
        this.HP = HP;
        abilities = new EnumMap<>(Assassin.Ability.class);
        for (Assassin.Ability ability : Assassin.Ability.values()) {
            abilities.put(ability, false);
        }
    }
    public void gainAbility() {
        Scanner scanner = new Scanner(System.in);
        Ability[] possibleAbilities = Ability.values();
        for (Ability ability : possibleAbilities) {
            if (!hasAbility(ability)) {
                System.out.println("You can gain the " + ability + " ability.");
            }
        }
        System.out.println("Enter the name of the ability you want to gain, or press 'q' to quit:");
        String abilityName = scanner.nextLine();
        if (abilityName.equalsIgnoreCase("q")) {
            return;
        }
        try {
            Ability chosenAbility = Ability.valueOf(abilityName);
            if (hasAbility(chosenAbility)) {
                System.out.println("You already have the " + chosenAbility + " ability.");
            } else {
                abilities.put(chosenAbility, true);
                System.out.println("You have gained the " + chosenAbility + " ability.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid ability name.");
        }
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public boolean hasAbility(Enum ability) {
        return (boolean) abilities.get(ability);
    }
}
