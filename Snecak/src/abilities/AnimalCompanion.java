package abilities;

import hero.HeroTemplate;
import heroalliedEntities.AllyEntityTemplate;

public class AnimalCompanion extends AbilityBase implements AllyEntityTemplate {
    private String entityName;
    private int randomAttack;

    public AnimalCompanion() {
        setName("Animal Companion");
    }

    public void summonPlayerAlliedEntity(HeroTemplate hero) {
        // Implement summoning logic if needed
    }

    public AnimalCompanion setEntityName(String entityName) {
        this.entityName = entityName;
        return this;
    }

    public AnimalCompanion setRandomAttack(int randomAttack) {
        this.randomAttack = randomAttack;
        return this;
    }

    public boolean isEntitySpell() {
        return true;
    }

    @Override
    public int performAutoAttack() {
        // Implement the logic for AnimalCompanion's auto attack
        return randomAttack; // Replace with the actual logic
    }

    @Override
    public String getEntityName() {
        return entityName;
    }

    // Other methods and logic if needed
}
