package game.itemshandling;

import game.monster.Dice;
import game.players.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class Consumable extends ItemBase {
    public Consumable(int damage, int health, int protection) {
        super(ItemType.CONSUMABLE, damage, health,
                protection);
    }

    public void setRandomHealth(Player player) {
        this.health = Dice.getNextNumber(0, 10 + (player.getHero().getLevel() * 10));
    }

    public void setRandomDamage(Player player) {
        this.damage = Dice.getNextNumber(0, 4 + player.getHero().getLevel());
    }
}
