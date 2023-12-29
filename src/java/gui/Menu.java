package gui;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel
{
    private String map_choisi;
    private String difficulte;

    private int width, height;
    private boolean fullscreen;


    private JPanel button_panel;
    private JPanel main_panel;

    public Menu(int width, int height)
    {
        this.width = width;
        this.height = height;

        // Le reste: affichage
        // En bas: home - play - settings
        this.setLayout(new BorderLayout());

        button_panel = makeButtonPanel();

        this.add(button_panel, BorderLayout.SOUTH);

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
}
