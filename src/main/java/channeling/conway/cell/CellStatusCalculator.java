package channeling.conway.cell;

import static channeling.conway.cell.LivingCell.CellStatus.*;

/**
 * Calculate status of a cell (dead or live).
 *
 */
public class CellStatusCalculator {

    public LivingCell.CellStatus calculateNextStatus(LivingCell.CellStatus currentStatus, int numLiveNeighbors) {
        // 1. Any live cell with fewer than two live neighbors dies as if caused by
        if (numLiveNeighbors < 2) return DEAD;
        // 2. Any live cell with two or three live neighbors lives on to the next generation.
        if (currentStatus == LIVE && (numLiveNeighbors == 2 || numLiveNeighbors == 3)) return LIVE;
        // 3. Any live cell with more than three live neighbors dies, as if by overcrowding.
        if (numLiveNeighbors > 3) return DEAD;
        // 4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
        if (currentStatus == DEAD && numLiveNeighbors == 3) return LIVE;
        return currentStatus;
    }
}
