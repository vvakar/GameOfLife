package channeling.conway;

import channeling.conway.cell.LivingCell;
import channeling.conway.grid.Grid;
import channeling.conway.grid.Coordinate;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        Grid<LivingCell> grid = new Grid<>(25, 25);
        Ticker ticker = new Ticker(grid, Arrays.asList(
                new Coordinate(12, 11),
                new Coordinate(13, 12),
                new Coordinate(11, 13),
                new Coordinate(12, 13),
                new Coordinate(13, 13)
        ));


        for (int i = 0; i < 50; ++i) {
            System.out.println("GENERATION: " + i);
            System.out.println("\n\n\n" + grid + "\n\n\n");
            System.out.println("---------------------------------------------");
            Thread.sleep(700L);
            ticker.tick();
        }
    }

}
