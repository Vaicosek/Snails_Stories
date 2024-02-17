package abilities;

import abilitiesmanagement.HeroAbilitiesManager;
import hero.Hero;
import hero.HeroTemplate;
import monster.MonsterBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class AbilityBase implements Cloneable { // Implement Cloneable interface

    private String name;
    private int damage;
    private int manaCost;
    private boolean unlocked;
    private String entityName; // Add entityName field


    private int heroLevel;
    private int monsterHp;

    public void setHeroLevel(int heroLevel) {
        this.heroLevel = heroLevel;
    }

    public void setMonsterHp(int monsterHp) {
        this.monsterHp = monsterHp;
    }

    public void calculateDamage(HeroTemplate hero, int monsterHp) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    public String getEntityName() {
        return entityName;
    }

    public AbilityBase setEntityName(String entityName) {
        this.entityName = entityName;
        return this;
    }

    public void use(HeroTemplate hero, MonsterBase currentMonster) {
    }

    public void testUse() {
    }

    public void useAreaSpell(HeroTemplate hero, List<MonsterBase> monsters) {
    }

    public void applyPassiveEffect(Hero hero) {
    }

    public void applyActiveEffect(Hero hero) {
    }

    public boolean isSpellAreaEffect() {
        return false; // By default, assume the spell doesn't target all monsters
    }

    public boolean isSpellTaunt() {
        return false; // By default
    }

    public boolean isEntangleAbility() {
        return false; // By default
    }

    public int getTauntRounds() {
        return 0;
    }

    public void useOnSelf(Hero hero) {
    }

    public void summonPlayerAlliedEntity(HeroTemplate hero) {
    }

    public boolean isEntitySpell() {
        return false;
    }

    public int getEntangleDuration() {
        return 0;
    }

    public boolean isMisdirectionAbility() {
        return false;
    }
}
