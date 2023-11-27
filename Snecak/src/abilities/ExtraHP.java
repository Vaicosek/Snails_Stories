package abilities;

import hero.HeroTemplate;

public class ExtraHP extends HeroAbility {
    private HeroTemplate hero;

    public ExtraHP() {
      setName("ExtraHP");

    }

    public void use () {
        int extraHP = hero.getLevel() * 10;
        int newHP = 100 + extraHP;
        hero.setHP(newHP);
        System.out.println("Gained " + extraHP + " HP. Total HP: " + newHP);
    }
}