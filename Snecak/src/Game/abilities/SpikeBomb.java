package Game.abilities;

import Game.hero.HeroTemplate;
import Game.monster.Dice;
import Game.monster.MonsterBase;
import Game.players.Player;

import java.util.List;

public class SpikeBomb implements AreaAbilityTemplate {

    private String name = "SpikeBomb";
    private int totalDamage;
    private int manaCost;
    private boolean unlocked = false;
    public SpikeBomb() {
        setName("SpikeBomb");
    }
    public boolean isSpellAreaEffect() {
        return true;
    }

    @Override
    public void castAreaEffect(HeroTemplate hero, List<MonsterBase> monsters, Player player) {
        int damage = Dice.getNextNumber(0, (10 + (hero.getLevel() * 5)));
        setDamage(damage);
        hero.setHP(hero.getHP() - (totalDamage / 3));
        for (MonsterBase monster : monsters) {
            monster.takeDamage(totalDamage);
        }
    }

    @Override
    public int getDamage() {
        return totalDamage;
    }

    @Override
    public void setDamage(int totalDamage) {

    }

    @Override
    public int getManaCost() {
        return manaCost;
    }

    @Override
    public void setManaCost(int manaCost) {

    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isUnlocked() {
        return unlocked;
    }

    @Override
    public void setUnlocked() {
        unlocked = true;
    }
}
