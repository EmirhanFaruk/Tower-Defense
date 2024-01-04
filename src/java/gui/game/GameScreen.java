package gui.game;

import config.Cellule;
import config.MapConfig;
import gui.Coordinate;
import gui.game.paint.Caro;
import gui.game.paint.MonsterPaintable;
import gui.game.paint.TourPaintable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameScreen extends JPanel
{
    Caro[][] caros;
    ArrayList<MonsterPaintable> monster_aff;
    ArrayList<TourPaintable> tour_aff;

    double scale_width, scale_height;

    JPanel map_panel, button_panel;

    public GameScreen(int width, int height, MapConfig map_config)
    {
        monster_aff = new ArrayList<>();
        tour_aff = new ArrayList<>();
        scale_width = (double) width / 16;
        scale_height = (double) height / 8;

        setLayout(new BorderLayout());
        map_panel = new JPanel();
        button_panel = makeButton_panel();

        add(map_panel, BorderLayout.CENTER);
        add(button_panel, BorderLayout.SOUTH);

        makeCaros(map_config);


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


    private void makeCaros(MapConfig map_config)
    {
        Cellule[][] tab = map_config.getGrid();
        caros = new Caro[tab.length][tab[0].length];
        for(int i = 0; i < tab.length; i++)
        {
            for(int j = 0; j < tab[i].length; j++)
            {
                System.out.println(scale_width + "-" + scale_height);
                caros[i][j] = new Caro(new Coordinate(i * scale_width, j * scale_height), 1, 1, scale_width, scale_height, tab[i][j].getType());
            }
        }
    }



    public void update()
    {

    }

    public void updateTours()
    {

    }

    public void updateMonsters()
    {
        for(MonsterPaintable mp : monster_aff)
        {
            mp.update();
            if(mp.getMonster().isDead())
            {
                monster_aff.remove(mp);
            }
        }
    }


    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage()
    }
}