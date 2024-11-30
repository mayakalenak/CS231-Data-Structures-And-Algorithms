/*
  Originally written by Bruce A. Maxwell a long time ago.
  Updated by Brian Eastwood and Stephanie Taylor more recently
  Updated by Bruce again in Fall 2018

  Creates a window using the JFrame class.

  Creates a drawable area in the window using the JPanel class.

  The JPanel calls the Landscape's draw method to fill in content, so the
  Landscape class needs a draw method.
  
  Students should not *need* to edit anything outside of the main method, 
  but are free to do so if they wish. 

  Name: Maya Kalenak

  Purpose: Make GUI for Game of Life Simulation
*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * Displays a Landscape graphically using Swing. The Landscape
 * contains a grid which can be displayed at any scale factor.
 * 
 * @author bseastwo
 */
public class LandscapeDisplay implements ActionListener// throws InterruptedException
{
    // declare variables
    private Scanner scan;
    private Object obj;
    private JFrame win;
    protected Landscape scape;
    private LandscapePanel canvas;
    private int gridScale; // width (and height) of each square in the grid
    private JButton end;
    private JButton speed;
    private JButton reset;
    private Cell[][] arr1;
    private Cell[][] arr2;
    private int speedInput;
    private static int time;
    private static boolean pauseSim;

    /**
     * Initializes a display window for a Landscape.
     * 
     * @param scape the Landscape to display
     * @param scale controls the relative size of the display
     */
    public LandscapeDisplay(Landscape scape, int scale) {
        this.scape = scape;
        ;

        this.time = time;
        this.pauseSim = false;

        this.arr2 = arr2;
        this.arr1 = arr1;

        this.speedInput = speedInput;

        // make buttons
        this.end = new JButton("End");
        this.speed = new JButton("Speed");
        this.reset = new JButton("Reset");

        // setup the window
        this.win = new JFrame("Game of Life");
        this.win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.win.setSize(603, 630);

        this.scape = scape;
        this.gridScale = scale;

        // make buttons clickable
        end.addActionListener(this);
        speed.addActionListener(this);
        reset.addActionListener(this);

        // create a panel in which to display the Landscape
        // put a buffer of two rows around the display grid
        this.canvas = new LandscapePanel((int) (this.scape.getCols() + 4) * this.gridScale,
                (int) (this.scape.getRows() + 4) * this.gridScale);

        // add the panel and buttons to the window, layout, and display
        this.win.setContentPane(this.canvas);
        this.win.getContentPane().add(this.reset);
        this.win.getContentPane().add(this.speed);
        this.win.getContentPane().add(this.end);

        this.win.setVisible(true);
    }

    // button actions
    public void actionPerformed(ActionEvent e) {
        scan = new Scanner(System.in);
        obj = e.getSource();

        // change simulation speed
        if (obj == speed) {
            System.out.println("Enter the desired speed of the Game of Life simulation (0-infinity = fast-slow): ");
            pauseSim = true;
            speedInput = scan.nextInt();
            time = speedInput;
            pauseSim = false;
        }

        // restart simulation
        if (obj == reset) {
            scape.reset();
            time = 250;
            pauseSim = false;
            repaint();
        }

        // end simulation
        if (obj == end)
            System.exit(0);
    }

    /**
     * Saves an image of the display contents to a file. The supplied
     * filename should have an extension supported by javax.imageio, e.g.
     * "png" or "jpg".
     *
     * @param filename the name of the file to save
     */
    public void saveImage(String filename) {
        // get the file extension from the filename
        String ext = filename.substring(filename.lastIndexOf('.') + 1, filename.length());

        // create an image buffer to save this component
        Component tosave = this.win.getRootPane();
        BufferedImage image = new BufferedImage(tosave.getWidth(), tosave.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        // paint the component to the image buffer
        Graphics g = image.createGraphics();
        tosave.paint(g);
        g.dispose();

        // save the image
        try {
            ImageIO.write(image, ext, new File(filename));
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    /**
     * This inner class provides the panel on which Landscape elements
     * are drawn.
     */
    private class LandscapePanel extends JPanel {
        /**
         * Creates the panel.
         * 
         * @param width  the width of the panel in pixels
         * @param height the height of the panel in pixels
         */
        public LandscapePanel(int width, int height) {
            super();
            this.setPreferredSize(new Dimension(width, height));
            this.setBackground(Color.lightGray);
        }

        /**
         * Method overridden from JComponent that is responsible for
         * drawing components on the screen. The supplied Graphics
         * object is used to draw.
         * 
         * @param g the Graphics object used for drawing
         */
        public void paintComponent(Graphics g) {
            // take care of housekeeping by calling parent paintComponent
            super.paintComponent(g);

            // call the Landscape draw method here
            scape.draw(g, gridScale);
        } // end paintComponent

    } // end LandscapePanel

    public static void speed(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void repaint() {
        this.win.repaint();
    }

    public static void main(String[] args) throws InterruptedException {
        // run simulation
        Landscape land = new Landscape(100, 100, 0.725);

        LandscapeDisplay display = new LandscapeDisplay(land, 60);

        boolean isInfinite = false;

        Cell[][] arr1 = new Cell[land.getRows()][land.getCols()];
        Cell[][] arr2 = new Cell[land.getRows()][land.getCols()];

        int rounds = 0;
        time = 250;

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
            Thread.sleep(time);
            // display.saveImage( "data/life_frame_" + String.format( "%03d", i ) + ".png"
            rounds++;
        }
    }
}