package abilities;

import hero.HeroTemplate;

public class ShadowClone extends AbilityBase {
    public ShadowClone() {
        setName("Shadow Clone");
    }

    public void summonPlayerAlliedEntity (HeroTemplate hero) {


    }

    public boolean isEntitySpell() {
        return true;
    }

    public ShadowClone setEntityName(String entityName) {
        super.setEntityName(entityName);
        return this;
    }
}
