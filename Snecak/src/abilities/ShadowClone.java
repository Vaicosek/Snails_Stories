package abilities;

import hero.HeroTemplate;
import heroalliedEntities.AllyEntityTemplate;

public class ShadowClone extends AbilityBase implements AllyEntityTemplate {
    public ShadowClone() {
        setName("Shadow Clone");
    }

    public boolean isEntitySpell() {
        return true;
    }

    public ShadowClone setEntityName(String entityName) {
        super.setEntityName(entityName);
        return this;
    }

    @Override
    public int performAutoAttack() {
        return 0;
    }
}
