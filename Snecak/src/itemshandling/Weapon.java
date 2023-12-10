package itemshandling;

import hero.HeroTemplate;
import players.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class Weapon extends ItemBase {
    private int additionalDamage;

    public Weapon(int damage, int health, int protection) {
        super(ItemType.WEAPON, damage, health, protection);
        this.additionalDamage = 0; // Initialize additional damage
    }

    public void setRandomDamage(Player player) {
        this.Damage = (int) (Math.floor(Math.random() * 15) * (player.getHero().getLevel() / 2));
    }

    public void increaseDamage(int amount) {
        this.additionalDamage += amount;
    }

    public int getTotalDamage() {
        return getDamage() + this.additionalDamage;
    }

    @Override
    protected void InitializeName() {
        names= new ArrayList<String>(Arrays.asList(
                // wizard weapons
                "arcane staff",
                "orb of force",
                "spellbook",
                "rod of negation",
                "rod of the pact keeper",
                "staff of power",
                "ethereal sword",
                "void scythe",
                "chaos axe",
                "demonic trident",

                // druid weapons
                "thorn whip",
                "oaken club",
                "spear of the hunt",
                "moon sickle",
                "thunderhead hammer",
                "entangle rope",
                "totem of the wolf",
                "dragon teeth dagger",
                "blazing sun spear",
                "venomous thorn sword",

                // defender weapons
                "dwarven waraxe",
                "heavy flail",
                "hammer of the gods",
                "giant maul",
                "spiked gauntlets",
                "sword of the righteous",
                "glaive of vengeance",
                "lance of the knight",
                "crimson greatsword",
                "executioner's axe",

                // assassin weapons
                "blades of darkness",
                "poisoned dart",
                "assassin's dagger",
                "hidden blade gauntlet",
                "thieves' tools",
                "snake fang sword",
                "chakram",

                // healer weapons
                "divine mace",
                "holy avenger sword",
                "mace of smiting",
                "scepter of radiance",
                "healing staff",
                "staff of light",
                "sunburst bow",
                "censer of purity",
                "holy lance"
        ));

    }


}


