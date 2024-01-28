package heroalliedEntities;

import abilities.AnimalCompanion;
import abilities.Conjurer;
import abilities.Illusion;
import abilities.ShadowClone;
import hero.Hero;
import hero.HeroTemplate;
import monster.Dice;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AllyEntityFactory {
    private static Random random = new Random();

    public static AnimalCompanion createAnimalCompanion(HeroTemplate  summoner) {
        return new AnimalCompanion()
                .setEntityName(summoner.getName() + getRandomEntityName(animalNames))
                .setRandomAttack(getRandomAttack());
    }
    public static Conjurer createConjuredEntity(HeroTemplate  summoner) {
        return new Conjurer()
                .setEntityName(summoner.getName() + getRandomEntityName(conjuredEntityNames))
                .setRandomAttack(getRandomAttack());
    }

    public static ShadowClone createShadowClone(HeroTemplate  summoner) {
        String entityName = summoner.getName() + "'s Shadow Clone";
        ShadowClone shadowClone = new ShadowClone();
        shadowClone.setEntityName(entityName);
        return shadowClone;
    }

    public static Illusion createIllusion(HeroTemplate summoner) {
        String entityName = summoner.getName() + "'s Illusion";
        Illusion illusion = new Illusion();
        illusion.setEntityName(entityName);
        // Set any other required properties for Illusion
        return illusion;
    }


    private static int getRandomAttack() {
        return Dice.getNextNumber(1, 10);
    }

    private static List<String> animalNames = Arrays.asList(
            "Fluffy", "Whiskers", "Shadow", "Paws", "Fang", "Luna", "Mittens", "Thunder", "Blaze", "Spike",
            "Cinder", "Midnight", "Rusty", "Sapphire", "Stormy", "Coco", "Echo", "Winston", "Lola", "Bella",
            "Rex", "Misty", "Oreo", "Buddy", "Nala", "Simba", "Mocha", "Tiger", "Ziggy", "Cinnamon",
            "Zorro", "Pepper", "Daisy", "Max", "Ruby", "Rocky", "Leo", "Jasmine", "Oliver", "Lucy",
            "Ginger", "Charlie"
    );

    private static List<String> conjuredEntityNames = Arrays.asList(
            "Arcane Guardian", "Mystic Serpent", "Eldritch Phantom", "Spectral Arcanist", "Aetherial Gazer",
            "Celestial Enchanter", "Wraith Conjurer", "Sorcerer's Familiar", "Mana Wyrm", "Enigmatic Spirit",
            "Shadowfiend", "Nebula Shaper", "Elemental Weaver", "Harbinger of Power", "Apparition Sage",
            "Abyssal Summoner", "Radiant Illusionist", "Phoenix Acolyte", "Nyx, the Voidstalker", "Terra Elemental",
            "Ignis Pyromancer", "Zephyr Stormcaller", "Aquilo Frostweaver", "Aetherial Artisan", "Obsidian Gargoyle",
            "Solstice Sorceress", "Serenity Spellbinder", "Onyx Archmage", "Luminara Lifestealer", "Chronos Timebender",
            "Eclipsa Eclipse Mage", "Havoc Hexer", "Oblivion Conjurer", "Ragnarok Ravager", "Zodiac Astrologer",
            "Eclipse Evoker", "Cerulean Soothsayer", "Spellbound Specter", "Wizardry Wight", "Mana Mirage",
            "Ethereal Evocation", "Wizened Warlock"
    );

    private static String getRandomEntityName(List<String> names) {
        int randomIndex = random.nextInt(names.size());
        return names.get(randomIndex);
    }
}
