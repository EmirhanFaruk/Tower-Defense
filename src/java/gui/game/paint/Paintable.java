package gui.game.paint;

import gui.Coordinate;

import javax.swing.*;
import java.awt.*;
import java.awt.print.Printable;

public class Paintable extends JPanel
{
    Coordinate pos;
    Image image;

    public Paintable(Coordinate p)
    {
        pos = p;
    }

    public void updatePos(Coordinate p)
    {
        pos.set(p);
    }
}
