/*
  Originally written by Bruce A. Maxwell a long time ago.
  Updated by Brian Eastwood and Stephanie Taylor more recently
  Updated by Bruce again in Fall 2018
  Updated by Bender in Spring 2023

  Creates a window using the JFrame class.

  Creates a drawable area in the window using the JPanel class.
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MazeSearchDisplay implements ActionListener {
    JFrame win;
    protected AbstractMazeSearch searcher;
    private Panel canvas;
    private int gridScale; // width (and height) of each square in the grid

    // button fields
    private JPanel buttonPanel;
    private JButton finishSim;
    private JButton density;
    private JButton end;
    private Object obj;

    // text field
    private JPanel labelPanel;
    private JLabel askDensity;
    private JLabel askCol;
    private JLabel askRow;

    // input text fields
    private JTextField enterDensity;
    private JTextField enterCol;
    private JTextField enterRow;

    // get or reset variables from maze constructor 
    private double setDensity;
    private int setRow;
    private int setCol;
    private String inputDensity;
    private String inputCol;
    private String inputRow;

    /**
     * Initializes a display window for a MazeSearcher.
     * 
     * @param scape the MazeSearcher to display
     * @param scale controls the relative size of the display
     */
    public MazeSearchDisplay(AbstractMazeSearch searcher, int scale) {
        // setup the window
        this.win = new JFrame("Maze-Search");
        this.win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.searcher = searcher;
        this.gridScale = scale;

        // create a panel in which to display the MazeSearcher
        // put a buffer of two rows around the display grid
        this.canvas = new Panel((int) (this.searcher.getMaze().getCols() + 2) * this.gridScale,
                (int) (this.searcher.getMaze().getRows() + 2) * this.gridScale);

        this.buttonPanel = new JPanel();
        this.labelPanel = new JPanel();

        // initialize buttons
        this.finishSim = new JButton("Fast Forward to end of simulation");
        this.density = new JButton("Click to change density, columns, and rows");
        this.end = new JButton("End simulation");

        this.enterDensity = new JTextField(3);
        this.enterCol = new JTextField(3);
        this.enterRow = new JTextField(3);

        inputDensity = enterDensity.getText();
        inputCol = enterCol.getText();
        inputRow = enterRow.getText();

        this.askDensity = new JLabel("Enter your desired density of obstacles of the grid: ");
        this.askCol = new JLabel("Enter your desired columns of the grid: ");
        this.askRow = new JLabel("Enter your desired rows of the grid: ");

        // make buttons clickable
        this.finishSim.addActionListener(this);
        this.end.addActionListener(this);
        this.density.addActionListener(this);

        this.buttonPanel.add(this.finishSim);
        this.buttonPanel.add(this.density);
        this.buttonPanel.add(this.end);

        this.labelPanel.add(this.askDensity);
        this.labelPanel.add(this.enterDensity);
        this.labelPanel.add(this.askCol);
        this.labelPanel.add(this.enterCol);
        this.labelPanel.add(this.askRow);
        this.labelPanel.add(this.enterRow);

        // add the panel to the window, layout, and display
        this.win.add(this.labelPanel, BorderLayout.NORTH);
        this.win.add(this.buttonPanel, BorderLayout.SOUTH);
        this.win.add(this.canvas, BorderLayout.CENTER);

        this.win.pack();
        this.win.setVisible(true);
    }

    public void setMazeSearch(AbstractMazeSearch searcher) {
        this.searcher = searcher;
    }

    public void closeWindow() {
        this.win.dispose();
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
     * This inner class provides the panel on which MazeSearcher elements
     * are drawn.
     */
    private class Panel extends JPanel {
        /**
         * Creates the panel.
         * 
         * @param width  the width of the panel in pixels
         * @param height the height of the panel in pixels
         */
        public Panel(int width, int height) {
            super();
            this.setPreferredSize(new Dimension(width, height));
            this.setBackground(Color.BLACK);
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
            g.translate(gridScale, gridScale);

            // call the MazeSearcher draw method here
            searcher.draw(g, gridScale);
        } // end paintComponent
    } // end Panel

    public void repaint() {
        this.win.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        obj = e.getSource();

        //button actions
        if (obj == finishSim) {
            this.searcher.reset();
            this.searcher.setStart(this.searcher.getMaze().get(0, 0));
            this.searcher.setTarget(this.searcher.getMaze().get(14, 14));
            this.searcher.setCur(this.searcher.getStart());
            this.searcher.search(this.searcher.getCur(), this.searcher.getTarget(), true, 0);
        }
        if (obj == density) {
            // use inputted columns, rows, and density
            inputDensity = this.enterDensity.getText();
            inputRow = this.enterRow.getText();
            inputCol = this.enterCol.getText();

            // read in columns, rows, and density
            setDensity = Double.parseDouble(this.inputDensity);
            setRow = Integer.parseInt(this.inputRow);
            setCol = Integer.parseInt(this.inputCol);

            // set new columns, rows, and density
            this.searcher.getMaze().setDensity(setDensity);
            this.searcher.getMaze().setCols(setCol);
            this.searcher.getMaze().setRows(setRow);

            // restart search using inputted columns, rows, and density 
            this.searcher.setStart(this.searcher.getMaze().get(0, 0));
            this.searcher.setTarget(this.searcher.getMaze().get(setRow-1, setCol-1));
            this.searcher.setCur(this.searcher.getStart());
            this.searcher.search(this.searcher.getStart(), this.searcher.getTarget(), true, 0);
        }
        if (obj == end) {
            // end simulation
            closeWindow();
            System.exit(0);
        }
    }
}