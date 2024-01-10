package com.gui.game.paint;

import com.gui.Coordinate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Caro
{
    Coordinate pos;

    int width, height;

    int type;

    BufferedImage image;


    public Caro(Coordinate p, int width, int height, int t)
    {
        pos = p;
        this.width = width;
        this.height = height;
        type = t;
        setImage();

    }

    private static String findSlash(String p)
    {
        for(int i = 0; i < p.length(); i++)
        {
            switch (p.charAt(i))
            {
                case '/' : return "/";
                case '\\' : return "\\";
            }
        }
        return "/";
    }

    private static String[] getImageNames()
    {
        String path = System.getProperty("user.dir");
        String s = findSlash(path);

        File directory = new File(path + s + "src" + s + "resources" + s + "images" + s + "Map");
        if(directory.list() != null)
        {
            return directory.list();
        }
        return new String[]{};
    }


    /**
     * Set image en fonction de type et scale
     */
    public void setImage()
    {
        String path = System.getProperty("user.dir");
        String s = findSlash(path);
        String[] img_name_list = {"Grass", "Road", "Water", "Tree", "Base"};
        String full_path = path + s + "src" + s + "resources" + s + "images" + s + "Map" + s + img_name_list[type] + ".png";
        try
        {
            image = ImageIO.read(new File(full_path));
        }
        catch (Exception ignored)
        {
            System.out.println("Couldn't read file " + full_path + ". Cannot set the image.");
        }

    }

    public void paint(Graphics2D g2)
    {
        g2.drawImage(image, pos.intj(), pos.inti(), width, height, null);
    }
}
