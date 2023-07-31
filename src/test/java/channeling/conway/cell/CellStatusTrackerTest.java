package channeling.conway.cell;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static channeling.conway.cell.LivingCell.CellStatus.*;

class CellStatusTrackerTest {
    private CellStatusCalculator calc;

    @BeforeEach
    public void before() {
        calc = new CellStatusCalculator();
    }

    @Test
    public void testWithFewNeighbors_allDead() {
        assertEquals(calc.calculateNextStatus(LIVE, 1), DEAD);
        assertEquals(calc.calculateNextStatus(DEAD, 1), DEAD);
    }

    @Test
    public void testWithTwoNeighbors() {
        assertEquals(calc.calculateNextStatus(LIVE, 2), LIVE);
        assertEquals(calc.calculateNextStatus(DEAD, 2), DEAD);
    }

    @Test
    public void testWithThreeNeighbors() {
        assertEquals(calc.calculateNextStatus(LIVE, 3), LIVE);
        assertEquals(calc.calculateNextStatus(DEAD, 3), LIVE);
    }

    @Test
    public void testWithManyNeighbors_allDead() {
        assertEquals(calc.calculateNextStatus(LIVE, 4), DEAD);
        assertEquals(calc.calculateNextStatus(DEAD, 4), DEAD);
    }
}