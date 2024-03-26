package game.itemshandling;

import game.monster.Dice;
import game.players.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class Armor extends ItemBase {
    public Armor(int damage, int health, int protection) {
        super(ItemType.ARMOR, damage, health, protection);
    }

    public void setRandomProtection(Player player) {
        this.Protection =  Dice.getNextNumber(0,6 + player.getHero().getLevel());
    }
    public void setRandomDamage(Player player) {
        this.Damage = Dice.getNextNumber(0,4 + player.getHero().getLevel());
    }

    @Override
    protected void InitializeName() {
        names= new ArrayList<String>(Arrays.asList(
                // wizard armor

                "robe of the archmagi",
                "cloak of displacement",
                "ring of turning",
                "amulet of protection",
                "bracers of defense",
                "staff of defense",
                "shield",
                "crystal ball",
                "mantle",
                "boots",
                // druid armor

                "leather armor",
                "hide armor",
                "scale mail",
                "spider silk vest",
                "mantle",
                "embrace",
                "circlet",
                "cloak",
                "mail",
                "mantle",
                // defender armor

                "chain mail",
                "splint mail",
                "plate mail",
                "adamantine plate",
                "shield",
                "mithral armor",
                "dragonscale mail",
                "titanium shield",
                "diamond plate",
                "crimson mail",
                // assassin armor

                "leather armor",
                "studded leather",
                "dark cloak",
                "poisoned hood",
                "boots",
                "goggles",
                "chain",
                "vest",
                "boots",
                "cloak",
                // healer armor

                "plate mail",
                "faith shield",
                "breastplate",
                "amulet of life",
                "golden gauntlets",
                "armor of divine favor",
                "helm",
                "holy ring",
                "vambraces",
                "sacred girdle"
        ));
    }


}
 