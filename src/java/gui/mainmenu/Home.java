package gui.mainmenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Home extends JPanel
{
    private int width, height;
    private BufferedImage home_image_file;

    public Home(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    @Override
    public void setSize(int width, int height)
    {
        this.width = width;
        this.height = height;
        try
        {
            makeHome();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private String findSlash(String p)
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

    private void setHome_image_file() throws IOException
    {
        String path = System.getProperty("user.dir");
        String s = findSlash(path);
        home_image_file = ImageIO.read(new File(path + s + "src" + s + "resources" + s + "images" + s + "menu" + s + "Menu.png"));
    }

    /**
     * Produire JPanel pour home: Image de menu.
     * @throws IOException exception pour le path
     */
    public void makeHome() throws IOException
    {
        setSize(width, height);
        // Getting home image
        setHome_image_file();

        Image scaled_home_image = home_image_file.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        for(Component component : getComponents())
        {
            remove(component);
        }
        add(new JLabel(new ImageIcon(scaled_home_image)));
    }
}
