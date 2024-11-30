/*
Name: Maya Kalenak
Purpose: make 2d array of Cells with alive/deads status
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Landscape {

    /**
     * The underlying grid of Cells for Conway's Game
     */
    private Cell[][] landscape;
    private Cell[][] newGrid;
    private Cell[][] arr1;
    private Cell[][] arr2;

    /**
     * The original probability each individual Cell is alive
     */
    private double initialChance;
    private int rows;
    private int columns;
    private ArrayList<Cell> neighbors;
    private String text;
    private int speed;

    /**
     * Constructs a Landscape of the specified number of rows and columns.
     * All Cells are initially dead.
     * 
     * @param rows    the number of rows in the Landscape
     * @param columns the number of columns in the Landscape
     */
    public Landscape(int rows, int columns) {
        this(rows, columns, 0);
    }

    /**
     * Constructs a Landscape of the specified number of rows and columns.
     * Each Cell is initially alive with probability specified by chance.
     * 
     * @param rows    the number of rows in the Landscape
     * @param columns the number of columns in the Landscape
     * @param chance  the probability each individual Cell is initially alive
     */
    public Landscape(int rows, int columns, double chance) {
        initialChance = chance;
        this.rows = rows;
        this.columns = columns;
        reset();
    }

    /**
     * Recreates the Landscape according to the specifications given
     * in its initial construction.
     */
    public void reset() {
        Random rand = new Random();

        landscape = new Cell[rows][columns];

        System.out.print(landscape.length);

        for (int i = 0; i < landscape.length; i++) {
            for (int j = 0; j < landscape[i].length; j++) {
                if (rand.nextDouble() < initialChance)
                    landscape[i][j] = new Cell(true);
                else
                    landscape[i][j] = new Cell(false);
            }
        }
    }

    /**
     * Returns the number of rows in the Landscape.
     * 
     * @return the number of rows in the Landscape
     */
    public int getRows() {
        return landscape.length;
    }

    /**
     * Returns the number of columns in the Landscape.
     * 
     * @return the number of columns in the Landscape
     */
    public int getCols() {
        int length = 0;

        for (int i = 0; i < landscape.length; i++) {
            for (int j = 0; j < landscape[i].length; j++) {
                length = landscape[i].length;
            }
        }

        return length;
    }

    /**
     * Returns the Cell specified the given row and column.
     * 
     * @param row the row of the desired Cell
     * @param col the column of the desired Cell
     * @return the Cell specified the given row and column
     */
    public Cell getCell(int row, int col) {
        return landscape[row][col];
    }

    /**
     * Returns a String representation of the Landscape.
     */
    public String toString() {
        text = "";

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                text += " " + landscape[getRows()][getCols()].toString();
            }
            text = text + "\n";
        }

        return text;
    }

    /**
     * Returns an ArrayList of the neighboring Cells to the specified location.
     * 
     * @param row the row of the specified Cell
     * @param col the column of the specified Cell
     * @return an ArrayList of the neighboring Cells to the specified location
     */
    public ArrayList<Cell> getNeighbors(int row, int col) {
        neighbors = new ArrayList<>();

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && j >= 0 && i < landscape.length && j < landscape[i].length && !(i == row && j == col))
                    neighbors.add(landscape[i][j]);
            }
        }

        return neighbors;
    }

    /**
     * Advances the current Landscape by one step.
     */
    public void advance() {
        newGrid = landscape;

        for (int i = 0; i < newGrid.length; i++) {
            for (int j = 0; j < newGrid[i].length; j++) {
                newGrid[i][j] = new Cell(landscape[i][j].getAlive());

            }
        }

        for (int i = 0; i < landscape.length; i++) {
            for (int j = 0; j < landscape[i].length; j++) {
                newGrid[i][j].updateState(this.getNeighbors(i, j));
            }
        }

        landscape = newGrid;
    }

    /**
     * Draws the Cell to the given Graphics object at the specified scale.
     * An alive Cell is drawn with a black color; a dead Cell is drawn gray.
     * 
     * @param g     the Graphics object on which to draw
     * @param scale the scale of the representation of this Cell
     */
    public void draw(Graphics g, int scale) {
        for (int x = 0; x < getRows(); x++) {
            for (int y = 0; y < getCols(); y++) {
                g.setColor(getCell(x, y).getAlive() ? Color.BLACK : Color.gray);
                g.fillOval(x * scale, y * scale, scale, scale);
            }
        }
    }

    // tester
    public static void main(String[] args) {
        Landscape land = new Landscape(3, 7, 0.5);

        System.out.print(land.landscape);
        System.out.println("\n");
        System.out.println("Rows: " + land.getRows());
        System.out.println("Columns: " + land.getCols());

        land.reset();

        System.out.println("arr[0][0]: " + land.getCell(0, 0));
        System.out.println("initial neighbors: " + land.getNeighbors(0, 0));
        System.out.println("initial arr[0][0]: " + land.landscape[0][0]);
        System.out.println("initial arr[0[1]]: " + land.landscape[0][1]);
        System.out.println("initial arr[0[2]]: " + land.landscape[0][2]);

        land.advance();

        System.out.println("\n");
        System.out.println("arr[0][0]: " + land.getCell(0, 0));
        System.out.println("new neighbors: " + land.getNeighbors(0, 0));
        System.out.println("advance arr[0[0]]: " + land.landscape[0][0]);
        System.out.println("advance arr[0[1]]: " + land.landscape[0][1]);
        System.out.println("advance arr[0[2]]: " + land.landscape[0][2]);

    }
}
