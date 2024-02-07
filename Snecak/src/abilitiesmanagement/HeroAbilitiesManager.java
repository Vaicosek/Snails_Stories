package abilitiesmanagement;
import abilities.*;
import java.util.ArrayList;
import java.util.List;

public class HeroAbilitiesManager {
    // Create lists to hold abilities for each hero
    private static final List<AbilityBase> assassinAbilities = new ArrayList<>();
    private static final List<AbilityBase> defenderAbilities = new ArrayList<>();
    private static final List<AbilityBase> healerAbilities = new ArrayList<>();
    private static final List<AbilityBase> wizardAbilities = new ArrayList<>();
    private static final List<AbilityBase> druidAbilities = new ArrayList<>();

    // Add abilities to the respective lists
    static {
        // Add abilities for Assassin
        assassinAbilities.add(new CriticalHit());
        assassinAbilities.add(new Poison());
        assassinAbilities.add(new DeathStrike());
        assassinAbilities.add(new Rage());
        assassinAbilities.add(new SpikeBomb());
        assassinAbilities.add(new ShadowClone());
        assassinAbilities.add(new Misdirection ());
        assassinAbilities.add(new WeaponBlast());
        assassinAbilities.add(new SharpWeapons());
        assassinAbilities.add(new BloodPact());

        // Add abilities for Defender
        defenderAbilities.add(new ExtraHP());
        defenderAbilities.add(new MonsterKiller ());
        defenderAbilities.add(new StrongHands ());
        defenderAbilities.add(new Taunt ());
        defenderAbilities.add(new Turtle ());
        defenderAbilities.add(new Throw());
        defenderAbilities.add(new FuriousStrike());
        defenderAbilities.add(new HammerSlam());
        defenderAbilities.add(new ArmorBlast());
        defenderAbilities.add(new Shout());


        // Add abilities for Healer
        healerAbilities.add(new Healing());
        healerAbilities.add(new HolyFire());
        healerAbilities.add(new Inquisition ());
        healerAbilities.add(new Light());
        healerAbilities.add(new MagicalPower());
        healerAbilities.add(new HEX());
        healerAbilities.add(new Purge());
        healerAbilities.add(new Telekinesis());
        healerAbilities.add(new DivineSword());
        healerAbilities.add(new HolyNova());


        // Add abilities for Wizard
        wizardAbilities.add(new Illusion());
        wizardAbilities.add(new MagicalExplosion());
        wizardAbilities.add(new Conjurer());
        wizardAbilities.add(new Enchantment());
        wizardAbilities.add(new Fire());
        wizardAbilities.add(new MagicalProtection());
        wizardAbilities.add(new MagicalPower());
        wizardAbilities.add(new Sleep());
        wizardAbilities.add(new Ice());
        wizardAbilities.add(new Sparks());


        // Add abilities for Druid
        druidAbilities.add(new PrimalRoar());
        druidAbilities.add(new Storm());
        druidAbilities.add(new Entangle());
        druidAbilities.add(new AnimalCompanion());
        druidAbilities.add(new Bite());
        druidAbilities.add(new MagicalProtection());
        druidAbilities.add(new Swarm());
        druidAbilities.add(new MagicalPower());
        druidAbilities.add(new Thorns());
        druidAbilities.add(new Throw());

    }

    public static List<AbilityBase> getAssassinAbilities() {
        return new ArrayList<>(assassinAbilities);
    }

    public static List<AbilityBase> getDefenderAbilities() {
        return new ArrayList<>(defenderAbilities);
    }

    public static List<AbilityBase> getHealerAbilities() {
        return new ArrayList<>(healerAbilities);
    }

    public static List<AbilityBase> getWizardAbilities() {
        return new ArrayList<>(wizardAbilities);
    }

    public static List<AbilityBase> getDruidAbilities() {
        return new ArrayList<>(druidAbilities);
    }
}
