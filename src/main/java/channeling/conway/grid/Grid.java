package channeling.conway.grid;


import com.google.common.base.Preconditions;

public class Grid<T> {
    private final T[][] grid;

    public Grid(int width, int height) {
        Preconditions.checkArgument(width > 0, "Error...");
        Preconditions.checkArgument(height > 0, "Error...");
        grid = (T[][])new Object[width][height];
    }

    public void put(Coordinate coordinate, T elem) {
        grid[coordinate.getX()][coordinate.getY()] = elem;
    }

    public T get(Coordinate coordinate) {
        return grid[coordinate.getX()][coordinate.getY()];
    }

    public int getMaxWidth() {
        return grid.length;
    }

    public int getMaxHeight() {
        return grid[0].length;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = -1; y < getMaxHeight(); ++y) {
            sb.append(String.format("%1$3s", (y == -1) ? "  " : y));
            for (int x = 0; x < getMaxWidth(); ++x) {
                String toPrint;
                if (y == -1)  {
                    toPrint  = x + "";
                } else {
                    T cur = grid[x][y];
                    toPrint = cur.toString();
                }
                sb.append(String.format(" |%1$3s", toPrint));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
