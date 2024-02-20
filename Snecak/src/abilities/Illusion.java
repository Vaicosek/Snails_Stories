package abilities;

public class Illusion extends AbilityBase implements AllyEntityTemplate {
    public Illusion() {
        setName("Illusion");

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
