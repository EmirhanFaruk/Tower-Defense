package gui.mainmenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Settings extends JPanel
{
    private int width, height;
    private final Menu main;

    // Resolution
    private JComboBox<String> res_box;
    private JButton choose_res;

    private final int[][] resolutions = {{600, 400}, {800, 600}, {1000, 800}, {1280, 720}, {1920, 1080}};

    public Settings(int width, int height, Menu main)
    {
        this.width = width;
        this.height = height;
        this.main = main;
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
        this.setLayout(new GridLayout(2, 2));

        // Resolution
        this.res_box = makeResBox();
        this.choose_res = makeResChooseButton();

        add(res_box);
        add(choose_res);

        // Fullscreen
        // For later if time left
    }

    private JComboBox<String> makeResBox()
    {
        // Making the ComboBox to choose the resolution
        JComboBox<String> res = new JComboBox<>();
        for(int[] couple : resolutions)
        {
            res.addItem(couple[0] + " x " + couple[1]);
        }

        return res;
    }

    private JButton makeResChooseButton()
    {
        JButton res = new JButton("Choisir cette resolution");
        res.addActionListener(
                new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int[] res = resolutions[res_box.getSelectedIndex()];
                main.setAllSize(res[0], res[1]);
            }
        });
        return res;
    }
}
