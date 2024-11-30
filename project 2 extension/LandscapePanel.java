/*
Name: Maya Kalenak
Purpose: Create landscape display
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LandscapePanel extends JPanel {
    private Landscape scape;
    private int gridScale;

    public LandscapePanel(int width, int height) {
        super();
        this.scape = scape;
        this.gridScale = gridScale;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.lightGray);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        scape.draw(g, gridScale);
    }
}