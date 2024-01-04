package gui.game.paint;

import gui.Coordinate;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.Printable;

public class Paintable extends JPanel
{
    protected Coordinate pos;
    protected int width, height;
    protected BufferedImage image;
    protected final double scale;

    public Paintable(Coordinate p, int width, int height , double scale)
    {
        pos = p;
        this.scale = scale;
        this.width = width;
        this.height = height;
    }

    public void setPanelImage()
    {
        Image scaled_home_image = image.getScaledInstance((int) (width * scale), (int) (height * scale), Image.SCALE_SMOOTH);

        removeAll();
        add(new JLabel(new ImageIcon(scaled_home_image)));
    }

    public void updatePos(Coordinate p)
    {
        pos.set(p);
    }
}
