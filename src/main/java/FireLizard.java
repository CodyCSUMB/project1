import utilities.Dice;

/*
 * @author Cody Hopkins
 * Date: 07/06/2025
 * Explanation: fire lizard monster
 */
public class FireLizard extends Monster {
    private final int DEFENSE_MIN = 1;
    private final int DEFENSE_MAX = 8;
    private final int ATTACK_MIN = 8;
    private final int ATTACK_MAX = 16;

    FireLizard(String value) {
        super(value, ElementalType.FIRE);
        this.setDefensePoints(DEFENSE_MAX);
        this.setAttackPoints(ATTACK_MAX);
        this.setAttackMax(ATTACK_MAX);
        this.setDefenseMin(DEFENSE_MIN);
        this.setAttackMin(ATTACK_MIN);
    }

    @Override
    public void setAttackPoints() {
        this.setAttackPoints(Dice.roll(ATTACK_MIN, ATTACK_MAX));

    }

    @Override
    public void setDefensePoints(int defensePoints) {
        this.defensePoints = defensePoints;
    }

    @Override
    public void setDefensePoints() {
        this.setDefensePoints(Dice.roll(DEFENSE_MIN, DEFENSE_MAX));
    }
}
