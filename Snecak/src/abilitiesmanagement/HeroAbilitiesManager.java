package abilitiesmanagement;
import abilities.*;
import java.util.ArrayList;
import java.util.List;

public class HeroAbilitiesManager {
    // Create lists to hold abilities for each hero
    private static final List<HeroAbility> assassinAbilities = new ArrayList<>();
    private static final List<HeroAbility> defenderAbilities = new ArrayList<>();
    private static final List<HeroAbility> healerAbilities = new ArrayList<>();
    private static final List<HeroAbility> wizardAbilities = new ArrayList<>();
    private static final List<HeroAbility> druidAbilities = new ArrayList<>();

    // Add abilities to the respective lists
    static {
        // Add abilities for Assassin
        assassinAbilities.add(new CriticalHit());
        assassinAbilities.add(new Poison());
        assassinAbilities.add(new DeathStrike());
        assassinAbilities.add(new Rage());
        assassinAbilities.add(new SmokeBomb());
        assassinAbilities.add(new ShadowClone());
        assassinAbilities.add(new Misdirection ());
        assassinAbilities.add(new Evasion ());
        assassinAbilities.add(new SharpWeapons());
        assassinAbilities.add(new Bloodthirst());

        // Add abilities for Defender
        defenderAbilities.add(new ExtraHP());
        defenderAbilities.add(new MonsterKiller ());
        defenderAbilities.add(new StrongHands ());
        defenderAbilities.add(new Taunt ());
        defenderAbilities.add(new Turtle ());
        defenderAbilities.add(new Throw());
        defenderAbilities.add(new DragonBorn ());
        defenderAbilities.add(new Warrior ());
        defenderAbilities.add(new Bodyguard ());
        defenderAbilities.add(new Sacrifice());


        // Add abilities for Healer
        healerAbilities.add(new Healing());
        healerAbilities.add(new HolyFire());
        healerAbilities.add(new Inquisition ());
        healerAbilities.add(new Light());
        healerAbilities.add(new MagicalPower());
        healerAbilities.add(new Resurrection());
        healerAbilities.add(new Purge());
        healerAbilities.add(new Telekinesis());
        healerAbilities.add(new DivineSword());
        healerAbilities.add(new HolyNova());


        // Add abilities for Wizard
        wizardAbilities.add(new Illusion());
        wizardAbilities.add(new Focus());
        wizardAbilities.add(new Conjurer());
        wizardAbilities.add(new Enchantment());
        wizardAbilities.add(new Fire());
        wizardAbilities.add(new StoneSkin());
        wizardAbilities.add(new MagicalPower());
        wizardAbilities.add(new Sleep());
        wizardAbilities.add(new Ice());
        wizardAbilities.add(new Sparks());


        // Add abilities for Druid
        druidAbilities.add(new PrimalRoar());
        druidAbilities.add(new Storm());
        druidAbilities.add(new Entangle());
        druidAbilities.add(new AnimalCompanion());
        druidAbilities.add(new Herbalist ());
        druidAbilities.add(new Barkskin());
        druidAbilities.add(new Swarm());
        druidAbilities.add(new MagicalPower());
        druidAbilities.add(new Thorns());
        druidAbilities.add(new Alchemy());

    }

    public static List<HeroAbility> getAssassinAbilities() {
        return new ArrayList<>(assassinAbilities);
    }

    public static List<HeroAbility> getDefenderAbilities() {
        return new ArrayList<>(defenderAbilities);
    }

    public static List<HeroAbility> getHealerAbilities() {
        return new ArrayList<>(healerAbilities);
    }

    public static List<HeroAbility> getWizardAbilities() {
        return new ArrayList<>(wizardAbilities);
    }

    public static List<HeroAbility> getDruidAbilities() {
        return new ArrayList<>(druidAbilities);
    }
}
