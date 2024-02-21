package abilities;

public class AbilityFactory {

    public static AbilityTemplate createAbility(String abilityType) {
        return switch (abilityType) {
            case "Bite" -> new Bite();
            case "BloodPact" -> new BloodPact();
            case "CriticalHit" -> new CriticalHit();
            case "Poison" -> new Poison();
            case "DeathStrike" -> new DeathStrike();
            case "Rage" -> new Rage();
            case "SpikeBomb" -> new SpikeBomb();
            case "ShadowClone" -> new ShadowClone();
            case "Misdirection" -> new Misdirection();
            case "WeaponBlast" -> new WeaponBlast();
            case "SharpWeapons" -> new SharpWeapons();
            case "ExtraHP" -> new ExtraHP();
            case "MonsterKiller" -> new MonsterKiller();
            case "StrongHands" -> new StrongHands();
            case "Taunt" -> new Taunt();
            case "Turtle" -> new Turtle();
            case "Throw" -> new Throw();
            case "FuriousStrike" -> new FuriousStrike();
            case "HammerSlam" -> new HammerSlam();
            case "ArmorBlast" -> new ArmorBlast();
            case "Shout" -> new Shout();
            case "Healing" -> new Healing();
            case "HolyFire" -> new HolyFire();
            case "Inquisition" -> new Inquisition();
            case "Light" -> new Light();
            case "MagicalPower" -> new MagicalPower();
            case "HEX" -> new HEX();
            case "Purge" -> new Purge();
            case "Telekinesis" -> new Telekinesis();
            case "DivineSword" -> new DivineSword();
            case "HolyNova" -> new HolyNova();
            case "Illusion" -> new Illusion();
            case "MagicalExplosion" -> new MagicalExplosion();
            case "Conjurer" -> new Conjurer();
            case "Enchantment" -> new Enchantment();
            case "Fire" -> new Fire();
            case "MagicalProtection" -> new MagicalProtection();
            case "Sleep" -> new Sleep();
            case "Ice" -> new Ice();
            case "Sparks" -> new Sparks();
            case "PrimalRoar" -> new PrimalRoar();
            case "Storm" -> new Storm();
            case "Entangle" -> new Entangle();
            case "AnimalCompanion" -> new AnimalCompanion();
            case "Swarm" -> new Swarm();
            case "Thorns" -> new Thorns();
            default -> throw new IllegalArgumentException("Unknown ability type: " + abilityType);
        };
    }
}
