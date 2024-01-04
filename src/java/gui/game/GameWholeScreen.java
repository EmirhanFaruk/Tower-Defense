package gui.game;

import config.MapConfig;
import gui.GameView;

import javax.swing.*;
import java.awt.*;

public class GameWholeScreen extends JPanel
{
    int width, height;

    GameScreen main_panel;
    JPanel button_panel;

    private GameView frame;

    public GameWholeScreen(int width, int height, GameView frame)
    {
        this.width = width;
        this.height = height;

        setLayout(new BorderLayout());


        main_panel = new GameScreen(width, height, frame);
        button_panel = makeButton_panel();

        add(main_panel, BorderLayout.CENTER);
        add(button_panel, BorderLayout.SOUTH);
    }

    public void make(MapConfig mapConfig)
    {
        main_panel.make(mapConfig);
    }

    private JLabel makeMessagePanel()
    {
        JLabel message_panel = new JLabel("Welcome to the game!");
        // Brown background
        message_panel.setBackground(new Color(102, 61, 20));
        message_panel.setForeground(Color.ORANGE);

        return message_panel;
    }

    /**
     * "Placehoder" pour le menu d'achat de tour
     * @return
     */
    private JPanel makeBrownPanel()
    {
        JPanel res = new JPanel();
        // Brown background
        res.setBackground(new Color(102, 61, 20));

        return res;
    }

    private JPanel makeMenuPanel()
    {
        JPanel res = new JPanel();
        // Brown background
        res.setBackground(new Color(102, 61, 20));

        return res;
    }

    private JPanel makeMenuButtonPanel()
    {
        JPanel res = new JPanel();
        res.setLayout(new GridLayout(1, 2));

        // Tour achat button
        JButton ta = new JButton("Acheter Tour");
        JButton menu = new JButton("Menu");
        JButton[] temp = {ta, menu};
        for(JButton button : temp)
        {
            button.setBackground(new Color(102, 61, 20));
            button.setForeground(Color.ORANGE);
        }


        res.add(ta);
        res.add(menu);

        return res;
    }


    /**
     * Faire le panel des buttons qui contient acheter, pause et quit
     * @return
     */
    private JPanel makeButton_panel()
    {
        JPanel res = new JPanel();
        res.setBackground(new Color(66, 40, 14));

        /*
         * 1 - message
         * 2 - choix pour le tour et menu
         * 3 - les buttons
         */
        res.setLayout(new GridLayout(3, 1));

        res.add(makeMessagePanel());
        res.add(makeBrownPanel());
        res.add(makeMenuButtonPanel());

        return res;
    }
}
