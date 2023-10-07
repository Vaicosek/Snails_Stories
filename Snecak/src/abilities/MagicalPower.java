package abilities;


import hero.HeroTemplate;

public class MagicalPower extends HeroAbility {
    private HeroTemplate hero;

    public MagicalPower() {
         setName ("MagicalPower");
    }

    public void Use() {
        this.hero.SetMana(100);
    }

}

