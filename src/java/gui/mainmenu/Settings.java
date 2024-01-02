package gui.mainmenu;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Settings extends JPanel
{
    private int width, height;

    public Settings(int width, int height)
    {
        this.width = width;
        this.height = height;
        makeSettings();
    }

    @Override
    public void setSize(int width, int height)
    {
        this.width = width;
        this.height = height;
        makeSettings();
    }

    private void makeSettings()
    {
        this.setBackground(Color.GRAY);
    }
}
