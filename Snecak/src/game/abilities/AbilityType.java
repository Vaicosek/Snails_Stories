package game.abilities;

import java.util.function.Supplier;

public enum AbilityType {
    BITE(Bite::new),
    BLOOD_PACT(BloodPact::new),
    CRITICAL_HIT(CriticalHit::new),
    POISON(Poison::new),
    DEATH_STRIKE(DeathStrike::new),
    RAGE(Rage::new),
    SPIKE_BOMB(SpikeBomb::new),
    SHADOW_CLONE(ShadowClone::new),
    MISDIRECTION(Misdirection::new),
    WEAPON_BLAST(WeaponBlast::new),
    SHARP_WEAPONS(SharpWeapons::new),
    EXTRA_HP(ExtraHP::new),
    MONSTER_KILLER(MonsterKiller::new),
    STRONG_HANDS(StrongHands::new),
    TAUNT(Taunt::new),
    TURTLE(Turtle::new),
    THROW(Throw::new),
    FURIOUS_STRIKE(FuriousStrike::new),
    HAMMER_SLAM(HammerSlam::new),
    ARMOR_BLAST(ArmorBlast::new),
    SHOUT(Shout::new),
    HEALING(Healing::new),
    HOLY_FIRE(HolyFire::new),
    INQUISITION(Inquisition::new),
    LIGHT(Light::new),
    MAGICAL_POWER(MagicalPower::new),
    HEX(HEX::new),
    PURGE(Purge::new),
    TELEKINESIS(Telekinesis::new),
    DIVINE_SWORD(DivineSword::new),
    HOLY_NOVA(HolyNova::new),
    ILLUSION(Illusion::new),
    MAGICAL_EXPLOSION(MagicalExplosion::new),
    CONJURER(Conjurer::new),
    ENCHANTMENT(Enchantment::new),
    FIRE(Fire::new),
    MAGICAL_PROTECTION(MagicalProtection::new),
    SLEEP(Sleep::new),
    ICE(Ice::new),
    SPARKS(Sparks::new),
    PRIMAL_ROAR(PrimalRoar::new),
    STORM(Storm::new),
    ENTANGLE(Entangle::new),
    ANIMAL_COMPANION(AnimalCompanion::new),
    SWARM(Swarm::new),
    THORNS(Thorns::new);

    private final Supplier<AbilityTemplate> constructor;

    AbilityType(Supplier<AbilityTemplate> constructor) {
        this.constructor = constructor;
    }

    public AbilityTemplate createInstance() {
        return constructor.get();
    }

    public static AbilityType fromString(String abilityName) {
        try {
            return AbilityType.valueOf(abilityName.toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            return null; // or handle exception as needed
        }
    }
}
