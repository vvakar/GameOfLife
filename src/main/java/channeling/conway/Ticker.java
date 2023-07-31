package channeling.conway;

import channeling.conway.cell.CellStatusCalculator;
import channeling.conway.grid.Grid;
import channeling.conway.grid.Coordinate;
import channeling.conway.cell.LivingCell;
import channeling.conway.cell.LivingCell.CellStatus;
import channeling.conway.cell.LivingGridCell;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static channeling.conway.cell.LivingCell.CellStatus.*;

public class Ticker {
    private final Grid<LivingCell> grid;
    private Collection<Coordinate> lastChanged;
    private CellStatusCalculator cellStatusCalculator;

    public Ticker(Grid<LivingCell> grid, Collection<Coordinate> livingCells) {
        this.grid = grid;
        cellStatusCalculator = new CellStatusCalculator();
        lastChanged = livingCells;

        // intialize all cells to DEAD
        for (int x = 0; x < grid.getMaxWidth(); ++x) {
            for (int y = 0; y < grid.getMaxHeight(); ++y) {
                LivingGridCell cell = new LivingGridCell(x, y, DEAD);
                grid.put(cell.getCoordinate(), cell);
            }
        }

        // apply starting position
        for (Coordinate c : livingCells) {
            grid.get(c).setStatus(LIVE);
        }
    }

    public void tick() {
        Map<Coordinate, CellStatus> pending = new HashMap<>();

        // process board in its current generation
        lastChanged.forEach(cell -> {
            getNeighbors(cell).forEach(neighbor -> {
                CellStatus newStatus = computeNewState(neighbor);
                CellStatus status = grid.get(neighbor.getCoordinate()).getStatus();
                if (status != newStatus) {
                    pending.put(neighbor.getCoordinate(), newStatus);
                }
            });
        });

        // apply new statuses to board
        pending.forEach((coordinate, status) -> {
            grid.get(coordinate).setStatus(status);
        });

        lastChanged = pending.keySet();
    }

    private CellStatus computeNewState(LivingCell cell) {
        int liveNeighbors = (int) getNeighbors(cell.getCoordinate()).stream()
                .filter(c -> c.getStatus() == LIVE)
                .count();
        return cellStatusCalculator.calculateNextStatus(cell.getStatus(), liveNeighbors);
    }

    private Collection<LivingCell> getNeighbors(Coordinate cell) {
        List<LivingCell> ret = new LinkedList<>();
        for (int x = Math.max(0, cell.getX() - 1); x < Math.min(grid.getMaxWidth(), cell.getX() + 2); ++x) {
            for (int y = Math.max(0, cell.getY() - 1); y < Math.min(grid.getMaxHeight(), cell.getY() + 2); ++y) {
                if (x != cell.getX() || y != cell.getY()) { // skip self
                    ret.add(grid.get(new Coordinate(x, y)));
                }
            }
        }

        return ret;
    }


    private static final class StateDiffs {
        private Collection<Coordinate> changed;

        public Collection<Coordinate> getChanged() {
            return changed;
        }

        public void reset(Collection<Coordinate> changed) {
            this.changed = changed;
        }
    }
}
