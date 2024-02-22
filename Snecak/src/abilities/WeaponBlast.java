package abilities;

import hero.HeroTemplate;
import monster.MonsterBase;
import players.Player;

import java.util.List;

public class WeaponBlast implements TickAbilityTemplate {

    private String name = "Turtle";
    private int manaCost;
    private int totalDamage;
    private boolean unlocked;
    private int remainingTurns;

    public WeaponBlast() {
        setName("WeaponBlast");
    }

    @Override
    public void cast(HeroTemplate hero, MonsterBase monster, List<MonsterBase> monsters) {
      monster.takeDamage(1);
    }

    @Override
    public void onTick(Player player, MonsterBase monster, List<MonsterBase> monsters, int turnCounter) {

        for (MonsterBase currentMonster : monsters) {
            int damage = player.getHero().getEquippedWeaponDamage() * 2 + monster.getHP() / 10;
            setDamage(damage);
            player.getHero().destroyWeapon(player.getHero().getEquippedWeapon(), player);
            currentMonster.takeDamage(totalDamage);
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
        return false;
    }

    @Override
    public void setUnlocked(boolean unlocked) {

    }

    @Override
    public int getRemainingTurns() {
        return remainingTurns;
    }

    @Override
    public void setRemainingTurns(int turns) {

    }

    @Override
    public boolean isEffectActive() {
        return false;
    }
}
