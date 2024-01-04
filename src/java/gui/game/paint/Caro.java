package gui.game.paint;

import gui.Coordinate;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Caro extends Paintable
{
    int type;

    public Caro(Coordinate p, int width, int height , double scale_width, double scale_height, int t)
    {
        super(p, width, height, scale_width, scale_height);
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
        String[] img_name_list = getImageNames();
        String full_path = path + s + "src" + s + "resources" + s + "images" + s + "Map" + s + img_name_list[type];
        try
        {
            image = ImageIO.read(new File(full_path));
            width = image.getWidth();
            height = image.getHeight();
        }
        catch (Exception ignored)
        {
            System.out.println("Couldn't read file " + full_path + ". Cannot set the image.");
        }

        setPanelImage();
    }
}
