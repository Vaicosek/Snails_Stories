package abilities;

import hero.HeroTemplate;

public interface EntityAbilityTemplate extends AbilityTemplate {

    int performAutoAttack();
    void summonEntity(HeroTemplate hero);
    String getEntityName();
    void setEntityName(String name);
}
