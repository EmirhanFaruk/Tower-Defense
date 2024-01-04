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

    public Paintable(Coordinate p, int width, int height, double scale_width, double scale_height)
    {
        pos = p;
        this.width = (int) (width * scale_width);
        this.height = (int) (height * scale_height);
    }

    public void setPanelImage()
    {
        setSize(width, height);
        Image scaled_image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        removeAll();
        add(new JLabel(new ImageIcon(scaled_image)));
    }

    public void updatePos(Coordinate p)
    {
        pos.set(p);
    }
}
