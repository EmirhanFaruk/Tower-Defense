package gui.mainmenu;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Play extends JPanel
{
    private int width, height;

    public Play(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    @Override
    public void setSize(int width, int height)
    {
        this.width = width;
        this.height = height;
        makePlay();
    }

    private void makePlay()
    {
        this.setBackground(Color.BLUE);
    }
}
