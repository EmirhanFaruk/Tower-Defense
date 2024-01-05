package gui.game;

import config.MapConfig;
import gui.GameView;
import model.Character;

import javax.swing.*;
import java.awt.*;

public class GameWholeScreen extends JPanel
{
    private GameScreen main_panel;
    private JPanel button_panel;

    private JPanel mid_button_panel;

    private Game game;


    public GameWholeScreen(int width, int height, String map, String difficulty, int wave_count_max, Character character)
    {
        setSize(width, height);
        game = new Game(map, difficulty, wave_count_max, character);

        setLayout(new BorderLayout());
        main_panel = new GameScreen(width, height);
        button_panel = makeButton_panel();

        add(main_panel, BorderLayout.CENTER);
        add(button_panel, BorderLayout.SOUTH);


    }

    public void make()
    {
        main_panel.make(game.getMap_config());
    }


    public void update(long delta_time)
    {
        game.updateEnts(delta_time);

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
