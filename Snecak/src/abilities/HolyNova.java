package abilities;

import hero.HeroTemplate;
import monster.Dice;
import monster.MonsterBase;
import players.Player;

import java.util.List;

public class HolyNova implements AreaAbilityTemplate {
    private static final double[] TIER_MULTIPLIERS = {0.05, 0.08, 0.12, 0.18, 0.25};

    private String name = "HolyNova";
    private int totalDamage;
    private int manaCost = 100;
    private boolean unlocked;

    public HolyNova() {

    }

    @Override
    public void castAreaEffect(HeroTemplate hero, List<MonsterBase> monsters, Player player) {
        int currentMana = hero.getMana();
        int manaCost = getManaCost();

        if (currentMana >= manaCost) {
            hero.setMana(currentMana - manaCost);
            System.out.println("Used " + getName() + "!");

            // Calculate damage based on hero's level and monster's tier
            int heroDamage = Dice.getNextNumber(5, (10 + (hero.getLevel() * 5)));
            int tier = monster.getTier();
            double multiplier = TIER_MULTIPLIERS[tier - 1];
            int monsterDamage = (int) (monster.getHP() * multiplier) + (5 * tier);

            int totalDamage = heroDamage + monsterDamage;
            setDamage(totalDamage);

        } else {
            System.out.println("Not enough mana to use " + getName() + " or it's not your turn.");
        }
    }

    public boolean isSpellAreaEffect() {
        return true;
    }
}
