package channeling.conway.cell;

import channeling.conway.grid.Coordinate;

public class LivingGridCell implements LivingCell {
    private CellStatus status;
    private Coordinate coordinate;

    public LivingGridCell(int x, int y, CellStatus initialStatus) {
        this.coordinate = new Coordinate(x, y);
        this.status = initialStatus;
    }

    @Override
    public CellStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(CellStatus status) {
        this.status = status;
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public String toString() {
        return status == CellStatus.LIVE ? "X" : " ";
    }
}
