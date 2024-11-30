/*
Name: Maya Kalenak
Use: Call Game of Life Simulation
 */

import java.util.Arrays;

public class Extension { // tester
    public static void main(String[] args) throws InterruptedException {
        Landscape land = new Landscape(10, 10, 0.725);

        LandscapeDisplay display = new LandscapeDisplay(land, 60);

        boolean isInfinite = false;

        Cell[][] arr1 = new Cell[land.getRows()][land.getCols()];
        Cell[][] arr2 = new Cell[land.getRows()][land.getCols()];

        int rounds = 0;

        while (rounds < 1000 || isInfinite == false) {
            for (int i = 0; i < land.getRows(); i++) {
                for (int j = 0; j < land.getCols(); j++) {
                    // set new 2d array to first dead//alive state
                    arr1[i][j] = land.getCell(i, j);
                }
            }

            // advance to next state
            land.advance();

            for (int i = 0; i < land.getRows(); i++) {
                for (int j = 0; j < land.getCols(); j++) {
                    // set new 2d array to updated dead/alive state
                    arr2[i][j] = land.getCell(i, j);
                }
            }

            // return true if previous and next grids are same
            if (Arrays.deepEquals(arr1, arr2) == true) {
                isInfinite = true;
            } else {
                isInfinite = false;
            }

            display.repaint();
            Thread.sleep(500);
            // display.saveImage( "data/life_frame_" + String.format( "%03d", i ) + ".png"
            // );
            rounds++;
        }
    }
}
