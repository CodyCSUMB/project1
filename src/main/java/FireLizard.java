import utilities.Dice;

/*
 * @author Cody Hopkins
 * Date: 07/06/2025
 * Explanation:
 */
public class FireLizard extends Monster {
    private final int DEFENSE_MIN = 1;
    private final int DEFENSE_MAX = 8;
    private final int ATTACK_MIN = 8;
    private final int ATTACK_MAX = 16;

    FireLizard(String value) {
        super(value, ElementalType.FIRE);
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
        this.setAttackPoints(Dice.roll(DEFENSE_MIN, DEFENSE_MAX));
    }
}
