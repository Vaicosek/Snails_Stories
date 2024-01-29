package abilities;

import hero.HeroTemplate;
import heroalliedEntities.AllyEntityTemplate;

public class Conjurer extends AbilityBase implements AllyEntityTemplate {
    private String entityName;
    private int randomAttack;

    public Conjurer() {
        setName("Conjurer");
    }

    public Conjurer setEntityName(String entityName) {
        this.entityName = entityName;
        return this;
    }

    public void summonPlayerAlliedEntity(HeroTemplate hero) {
        // Implement summoning logic if needed
    }

    public boolean isEntitySpell() {
        return true;
    }

    @Override
    public int performAutoAttack() {
        return randomAttack;
    }

    public String getEntityName() {
        return entityName;
    }

    public Conjurer setRandomAttack(int randomAttack) {
        this.randomAttack = randomAttack;
        return this;
    }
}
