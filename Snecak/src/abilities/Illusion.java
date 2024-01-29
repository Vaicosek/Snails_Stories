package abilities;

import hero.HeroTemplate;
import heroalliedEntities.AllyEntityTemplate;

public class Illusion extends AbilityBase implements AllyEntityTemplate {
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

    @Override
    public int performAutoAttack() {
        return 0;
    }
}
