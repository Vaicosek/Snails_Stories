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
        this.protection = Dice.getNextNumber(0, 6 + player.getHero().getLevel());
    }

    public void setRandomDamage(Player player) {
        this.damage = Dice.getNextNumber(0, 4 + player.getHero().getLevel());
    }
}