package game.itemshandling;

import game.monster.Dice;
import game.players.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class Weapon extends ItemBase {
    private int additionalDamage;

    public Weapon(int damage, int health, int protection) {
        super(ItemType.WEAPON, damage, health, protection);
        this.additionalDamage = 0; // Initialize additional damage
    }

    public void setRandomDamage(Player player) {
        this.damage = Dice.getNextNumber(0, (player.getHero().getLevel() * 11));
    }

    public void increaseDamage(int amount) {
        this.additionalDamage += amount;
    }

}