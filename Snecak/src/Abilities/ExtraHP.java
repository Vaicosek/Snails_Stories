package abilities;

import hero.HeroTemplate;

public class ExtraHP extends HeroAbility {
    private HeroTemplate hero;

    public ExtraHP(HeroTemplate hero) {
        Name = "ExtraHP";
        this.hero = hero;
    }

    public void Use() {
        int extraHP = hero.getLevel() * 10;
        int currentHP = hero.getHP();
        int newHP = currentHP + extraHP;
        hero.setHP(newHP);
        System.out.println("Gained " + extraHP + " HP. Total HP: " + newHP);
    }
}