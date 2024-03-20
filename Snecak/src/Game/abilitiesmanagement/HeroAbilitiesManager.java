package Game.abilitiesmanagement;
import Game.abilities.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HeroAbilitiesManager {
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
                    "Healing", "HolyFire", "Inquisition", "Light", "MagicalPower",
                    "HEX", "Purge", "Telekinesis", "DivineSword", "HolyNova"
            ),
            "Wizard", List.of(
                    "Illusion", "MagicalExplosion", "Conjurer", "Enchantment", "Fire",
                    "MagicalProtection", "MagicalPower", "Sleep", "Ice", "Sparks"
            ),
            "Druid", List.of(
                    "PrimalRoar", "Storm", "Entangle", "AnimalCompanion", "Bite",
                    "MagicalProtection", "Swarm", "MagicalPower", "Thorns", "Throw"
            )
    );

    public static List<String> getAbilitiesForHero(String heroType) {
        return heroAbilitiesMapping.getOrDefault(heroType, List.of());
    }
}
