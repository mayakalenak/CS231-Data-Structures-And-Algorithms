/*
Name: Maya Kalenak
Purpose: Call Game of Life Simulation
*/

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class LifeSimulation {
    public static void main(String[] args) throws InterruptedException {
        Landscape land = new Landscape(100, 100, 0.5);

        LandscapeDisplay display = new LandscapeDisplay(land, 6);

        boolean isInfinite = false;

        Cell[][] arr1 = new Cell[land.getRows()][land.getCols()];
        Cell[][] arr2 = new Cell[land.getRows()][land.getCols()];

        int rounds = 0;
        int aliveCells = 0;

        try {
            int AliveCells = 0;
            FileWriter csvWriter = new FileWriter("alive_cells_.csv");

            csvWriter.append("No., AliveCells\n");

            for (int i = 0; i < 1000; i++) {
                csvWriter.append(i + "," + aliveCells + "\n");
            }

            while (rounds < 1000 || isInfinite == false) {
                aliveCells = 0;

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

                for (int i = 0; i < land.getRows(); i++) {
                    for (int j = 0; j < land.getCols(); j++) {
                        if (arr1[i][j].getAlive() == true) {
                            aliveCells++;
                        }
                    }
                }

                display.repaint();
                Thread.sleep(500);
                // display.saveImage( "data/life_frame_" + String.format( "%03d", i ) + ".png"
                // );
                rounds++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
