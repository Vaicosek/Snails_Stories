package abilities;

import hero.HeroTemplate;

public class ExtraHP extends AbilityBase {
    private HeroTemplate hero;

    public ExtraHP() {
      setName("ExtraHP");

    }

    public void useOnSelf () {
        int extraHP = hero.getLevel() * 10;
        int newHP = 100 + extraHP;
        hero.setHP(newHP);
        System.out.println("Gained " + extraHP + " HP. Total HP: " + newHP);
    }
}