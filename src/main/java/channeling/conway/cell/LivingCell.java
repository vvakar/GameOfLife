package channeling.conway.cell;

import channeling.conway.grid.Coordinate;

public interface LivingCell {
    enum CellStatus {DEAD, LIVE}

    CellStatus getStatus();
    void setStatus(CellStatus status);

    Coordinate getCoordinate();

}
