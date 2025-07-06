import java.util.ArrayList;
import java.util.List;

public abstract class Monster {
    private static final double MAX_HP = 20.0;
    /**
     * @author Drew A. Clinkenbeard
     * @since 2.0.1
     * The beginning of the Monster class
     */

    /**
     * THis is an inner class that represents the types that may be used to
     * create pocket monsters.
     */
    protected enum ElementalType {
        ELECTRIC,
        FIRE,
        GRASS,
        WATER,
    }

    private String name = "";
    private int defenseMax = 10;
    private List<ElementalType> elements = new ArrayList<ElementalType>();
    private String phrase = "";
    private int attackMin = 1;
    private int attackPoints = 10;
    private int defensePoints = 10;
    private boolean fainted = false;
    private int attackMax = 10;
    private Double healthPoints = MAX_HP;
    private int defenseMin = 1;

    Monster(String name, ElementalType elementalType) {
        this.name = name;
        this.elements.add(elementalType);
        // TODO: implement setPhrase
        this.setPhrase(name);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDefenseMax() {
        return defenseMax;
    }

    public void setDefenseMax(int defenseMax) {
        this.defenseMax = defenseMax;
    }

    public List<ElementalType> getElements() {
        return elements;
    }

    public void setElements(List<ElementalType> elements) {
        this.elements = elements;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    private void setPhrase(Monster monster) {
        switch (monster.name) {

        }
    }

    public int getAttackMin() {
        return attackMin;
    }

    public void setAttackMin(int attackMin) {
        this.attackMin = attackMin;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public abstract void setAttackPoints();

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public int getDefensePoints() {
        return defensePoints;
    }

    public abstract void setDefensePoints(int defensePoints);
    public abstract void setDefensePoints();


    public boolean isFainted() {
        return fainted;
    }

    public void setFainted(boolean fainted) {
        this.fainted = fainted;
    }

    public int getAttackMax() {
        return attackMax;
    }

    public void setAttackMax(int attackMax) {
        this.attackMax = attackMax;
    }

    public Double getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(Double healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getDefenseMin() {
        return defenseMin;
    }

    public void setDefenseMin(int defenseMin) {
        this.defenseMin = defenseMin;
    }
}
