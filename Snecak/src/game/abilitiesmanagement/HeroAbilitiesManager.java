package game.abilitiesmanagement;
import java.util.List;
import java.util.Map;

public class HeroAbilitiesManager {

    private static final String MAGIC = "MagicalPower";

    private HeroAbilitiesManager() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    private static final Map<String, List<String>> heroAbilitiesMapping = Map.of(
            "Assassin", List.of(
                    "CriticalHit", "Poison", "DeathStrike", "Rage", "SpikeBomb",
                    "ShadowClone", "Misdirection", "WeaponBlast", "SharpWeapons", "BloodPact"
            ),
            "Defender", List.of(
                    "ExtraHP", "MonsterKiller", "StrongHands", "Taunt", "Turtle",
                    "Throw", "FuriousStrike", "HammerSlam", "ArmorBlast", "Shout"
            ),
            "Healer", List.of(
                    "Healing", "HolyFire", "Inquisition", "Light", MAGIC,
                    "HEX", "Purge", "Telekinesis", "DivineSword", "HolyNova"
            ),
            "Wizard", List.of(
                    "Illusion", "MagicalExplosion", "Conjurer", "Enchantment", "Fire",
                    "MagicalProtection", MAGIC, "Sleep", "Ice", "Sparks"
            ),
            "Druid", List.of(
                    "PrimalRoar", "Storm", "Entangle", "AnimalCompanion", "Bite",
                    "MagicalProtection", "Swarm", MAGIC, "Thorns", "Throw"
            )
    );

    public static List<String> getAbilitiesForHero(String heroType) {
        return heroAbilitiesMapping.getOrDefault(heroType, List.of());
    }
}
