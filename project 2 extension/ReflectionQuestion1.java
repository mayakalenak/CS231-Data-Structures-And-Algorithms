/*
Name: Maya Kalenak
Purpose: Reflection Question 1: see if simulation is stuck in infinite loop
 */

import java.util.Arrays;

public class ReflectionQuestion1 {
    private Cell[][] arr1;
    private Cell[][] arr2;
    private Landscape land;

    public ReflectionQuestion1(int rows, int cols, double chance) {
        arr1 = new Cell[rows][cols];
        arr2 = new Cell[rows][cols];
        land = new Landscape(rows, cols, chance);
    }

    public boolean checkInfiniteLoop() {
        for (int i = 0; i < land.getRows(); i++) {
            for (int j = 0; j < land.getCols(); j++) {
                // set new 2d array to first dead//alive state
                arr1[i][j] = land.getCell(i, j);
                // test and print first grid
                System.out.print(arr1[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        // advance to next state
        land.advance();

        for (int i = 0; i < land.getRows(); i++) {
            for (int j = 0; j < land.getCols(); j++) {
                // set new 2d array to updated dead/alive state
                arr2[i][j] = land.getCell(i, j);
                // test and print next grid
                System.out.print(arr2[i][j]);
            }
            System.out.println();
        }

        // return true if previous and next grids are same
        if (Arrays.deepEquals(arr1, arr2) == true) {
            return true;
        }
        // return false if no infinite loop
        else
            return false;
    }

    // extension
    // return 2d arrays
    public Cell[][] back() {
        // previous landscape if no buttons pressed
        // current landsape if back button pressed
        // next landscape if back button pressed again
        // previous landscape if next button pressed
        for (int i = 0; i < land.getRows(); i++) {
            for (int j = 0; j < land.getCols(); j++) {
                // set new 2d array to first dead//alive state
                arr1[i][j] = land.getCell(i, j);
                // test and print first grid
                System.out.print(arr1[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        // previous landscape
        return arr1;
    }

    public Cell[][] next() {
        // current landscape if no buttons pressed
        // next landscape if back button pressed
        // previous landscape if next button pressed
        for (int i = 0; i < land.getRows(); i++) {
            for (int j = 0; j < land.getCols(); j++) {
                // set new 2d array to first dead//alive state
                arr1[i][j] = land.getCell(i, j);
            }
        }

        land.advance();

        for (int i = 0; i < land.getRows(); i++) {
            for (int j = 0; j < land.getCols(); j++) {
                // set new 2d array to updated dead/alive state
                arr2[i][j] = land.getCell(i, j);
                // test and print next grid
                System.out.print(arr2[i][j]);
            }
            System.out.println();
        }

        // next landscape
        return arr2;
    }
}