package abilities;


import hero.HeroTemplate;

public class MagicalPower extends HeroAbility {
    private HeroTemplate hero;

    public MagicalPower(HeroTemplate hero) {
        Name = "MagicalPower";
        this.hero = hero;

    }

    public void Use() {
        this.hero.SetMana(100);
    }

}

