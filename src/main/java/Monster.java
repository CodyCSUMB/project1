import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import utilities.Dice;

/*
 * @author Cody Hopkins
 * Date: 07/06/2025
 * Explanation: base monster class
 */
public abstract class Monster {
    private static final double MAX_HP = 20.0;

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
    protected int defensePoints = 10;
    private boolean fainted = false;
    private int attackMax = 10;
    private Double healthPoints = MAX_HP;
    private int defenseMin = 1;

    Monster(String name, ElementalType elementalType) {
        this.name = name;
        this.elements.add(elementalType);
        this.setPhrase(this);
    }

    /**
     * Calculates and attacks parameter monster from the current object
     * @param monster The monster that is being attacked
     * @return damageTaken
     */
    public double attack(Monster monster) {
        if (this.isFainted()) {
            System.out.printf("%s isn't conscious.. it can't attack.\n", this.name);
            return 0.0;
        }

        System.out.printf("%s is attacking %s\n", this.name, monster.name);
        System.out.println(this.getPhrase());

        double attackPoints = this.calculateAttackPoints(this, monster.getElements());

        System.out.printf("%s is attacking with %f attack points!\n", this.name, attackPoints);

        double damageTaken = monster.takeDamage(attackPoints);

        if (this == monster && damageTaken > 0) {
            System.out.printf("%s hurt itself in the confusion.\n", this.name);
        }

        return damageTaken;
    }

    /**
     * Calculates the damage taken from the current object
     * @param attackValue damage done to current monster
     * @return attackPoints
     */
    public double takeDamage(double attackValue) {
        double defense = calculateDefensePoints(this);
        double attackPoints = attackValue - defense;

        if (attackPoints > 0) {
            System.out.printf("%s is hit for %f damage\n", this.name, attackPoints);
            this.healthPoints -= attackPoints;
        }

        if (attackPoints == 0) {
            System.out.printf("%s is nearly hit!\n", this.name);
        }

        if (attackPoints < (defense / 2)) {
            System.out.printf("%s shrugs off the puny attack\n", this.name);
        }

        if (this.healthPoints <= 0) {
            System.out.printf("%s has faint-- passed out. It's passed out.\n", this.name);
            this.fainted = true;
        } else {
            System.out.printf("%s has %f / %f HP remaining\n", this.name, this.healthPoints, Monster.MAX_HP);
        }

        return attackPoints;
    }

    /**
     * Calculates defense points by rolling a die provided by the utilities package
     * @param monster Monster that is given defense points
     * @return defenseValue
     */
    public double calculateDefensePoints(Monster monster) {
        int defenseValue = Dice.roll(monster.defenseMin, monster.defenseMax);

        if (defenseValue % 2 == 0 && defenseValue < (monster.defenseMax / 2.0)) {
            defenseValue = (defenseValue + 1) * 2;
            System.out.printf("%s finds courage in the desperate situation\n", monster.name);
        }

        if (defenseValue == monster.defenseMin) {
            System.out.printf("%s is clearly not paying attention.\n", monster.name);
        }

        return defenseValue;
    }

    /**
     * Calculates attack points by rolling a die for the monster
     * @param monster The monster that is being given attack points
     * @param enemyType Set a attack modifier based on given enemy type
     * @return modifier
     */
    public double calculateAttackPoints(Monster monster, List<ElementalType> enemyType) {
        int attackValue = Dice.roll(monster.attackMin, monster.attackMax);

        System.out.printf("%s rolls a %d\n", monster.name, attackValue);

        double modifier = 1.0;

        for (ElementalType elementalType : enemyType) {
            modifier *= attackModifier(elementalType);
        }

        if (modifier >= 2.0) {
            System.out.println("It's su-- *cough* very effective!");
        }

        return modifier * attackValue;
    }

    /**
     * Determines a modifier depending on the monster's elemental type
     * @param defending base modifier on given elemental type
     * @return modifier
     */
    public double attackModifier(ElementalType defending) {
        for (ElementalType elementalType : this.elements) {
            return switch (elementalType) {
                case ELECTRIC -> switch (defending) {
                    case WATER -> 2.0;
                    case FIRE -> 1.0;
                    case GRASS -> 0.5;
                    case ELECTRIC -> 0.5;
                };
                case FIRE -> switch (defending) {
                    case ELECTRIC -> 1.0;
                    case FIRE -> 0.5;
                    case GRASS -> 2.0;
                    case WATER -> 0.5;
                };
                case GRASS -> switch (defending) {
                    case ELECTRIC -> 1.0;
                    case FIRE -> 0.5;
                    case GRASS -> 0.5;
                    case WATER -> 2.0;
                };
                case WATER -> switch (defending) {
                    case ELECTRIC -> 1.0;
                    case FIRE -> 2.0;
                    case GRASS -> 0.5;
                    case WATER -> 0.5;
                };
            };
        }
        return 1.0;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Monster monster)) return false;
        return defenseMax == monster.defenseMax && attackMin == monster.attackMin && attackPoints == monster.attackPoints && defensePoints == monster.defensePoints && fainted == monster.fainted && attackMax == monster.attackMax && defenseMin == monster.defenseMin && Objects.equals(name, monster.name) && Objects.equals(elements, monster.elements) && Objects.equals(phrase, monster.phrase) && Objects.equals(healthPoints, monster.healthPoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, defenseMax, elements, phrase, attackMin, attackPoints, defensePoints, fainted, attackMax, healthPoints, defenseMin);
    }

    @Override
    public String toString() {
        if (!isFainted()) {
            StringBuilder info = new StringBuilder(String.format("%s has %f/%f hp\nElemental type:", this.name, this.healthPoints, MAX_HP));
            for (ElementalType elementalType : this.elements) {
                info.append(elementalType.toString()).append(", ");
            }
            return info.toString();
        }
        StringBuilder info = new StringBuilder(String.format("%s has fainted.\nElemental type: ", this.name));
        for (ElementalType elementalType : this.elements) {
            info.append(elementalType.toString()).append(", ");
        }
        return info.toString();
    }

    // getters/setters

    /**
     * Sets the elemental type for the current object
     * @param elementalType Type to determine object's elemental type
     * @return int
     */
    public int setType(ElementalType elementalType) {
        for  (ElementalType element : this.elements) {
            if (element.equals(elementalType)) {
                System.out.printf("%s already set!\n", elementalType);
                return 1;
            }
        }

        if (this.attackModifier(elementalType) > 1.0) {
            System.out.println("Can't have conflicting types!");
            return -1;
        }

        System.out.printf("%s now has %s\n", this.name, elementalType);
        this.elements.add(elementalType);
        return 0;
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
        return phrase + " " + phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    private void setPhrase(Monster monster) {
        switch (monster.name) {
            case "Electric Rat":
                this.setPhrase("'Lectric!");
                break;
            case "Fire Lizard":
                this.setPhrase("Deal with it.");
                break;
            case "Flower Dino":
                this.setPhrase("Flowah!");
                break;
            case "Weird Turtle":
                this.setPhrase("'Urtle!");
                break;
            default:
                this.setPhrase("No phrase for me!");
                break;
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
