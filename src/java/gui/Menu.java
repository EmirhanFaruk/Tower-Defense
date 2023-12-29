package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu extends JPanel
{
    private String map_choisi;
    private String difficulte;

    private int width, height;
    private boolean fullscreen;


    private JPanel button_panel;
    private JPanel main_panel;

    private String mode = "HOME";

    public Menu(int width, int height) throws IOException
    {
        this.width = width;
        this.height = height;

        // Le reste: affichage
        // En bas: home - play - settings
        this.setLayout(new BorderLayout());

        button_panel = makeButtonPanel();
        main_panel = makeMainPanel();

        this.add(button_panel, BorderLayout.SOUTH);
        this.add(main_panel);

    }

    private JPanel makeButtonPanel()
    {
        JPanel res = new JPanel();
        res.setLayout(new GridLayout(1, 3));

        JButton home = new JButton("HOME");
        JButton play = new JButton("PLAY");
        JButton settings = new JButton("SETTINGS");

        res.add(home);
        res.add(play);
        res.add(settings);

        return res;
    }

    private JPanel makeMainPanel() throws IOException
    {
        JPanel res  = new JPanel();
        switch (mode)
        {
            case "HOME" : res = makeHome();break;
        }

        return res;
    }

    private JPanel makeHome() throws IOException
    {
        JPanel res = new JPanel();
        String path = System.getProperty("user.dir");

        // Getting home image
        BufferedImage home_image;
        try
        {
            home_image = ImageIO.read(new File(path + "/src/resources/images/menu/Menu.png"));
        }
        catch (Exception e)
        {
            home_image = ImageIO.read(new File(path + "\\src\\resources\\images\\menu\\Menu.png"));
        }

        Image scaled_home_image = home_image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        JLabel label = new JLabel(new ImageIcon(scaled_home_image));
        res.add(label);
        return res;
    }
}
