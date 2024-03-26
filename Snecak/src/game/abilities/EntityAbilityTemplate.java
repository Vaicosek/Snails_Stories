package game.abilities;

import game.hero.HeroTemplate;

public interface EntityAbilityTemplate extends AbilityTemplate {

    int performAutoAttack();

    void setRandomAttack (int entityAttack);
    void summonEntity(HeroTemplate hero);
    String getEntityName();
    void setEntityName(String name);
}
