package abilities;

import hero.HeroTemplate;

public class Illusion extends AbilityBase {
    public Illusion() {
        setName("Illusion");

    }

    public void summonPlayerAlliedEntity (HeroTemplate hero) {

    }

    public boolean isEntitySpell() {
        return true;
    }

    public Illusion setEntityName(String entityName) {
        super.setEntityName(entityName);
        return this;
    }
}
