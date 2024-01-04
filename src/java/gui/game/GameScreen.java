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
    ArrayList<Caro> caros;
    ArrayList<MonsterPaintable> monster_aff;
    ArrayList<TourPaintable> tour_aff;

    double scale_width, scale_height;

    JPanel map_panel, button_panel;

    public GameScreen(int width, int height, MapConfig map_config)
    {
        caros = new ArrayList<>();
        monster_aff = new ArrayList<>();
        tour_aff = new ArrayList<>();
        scale_width = (double) width / 16;
        scale_height = (double) height / 8;

        makeCaros(map_config);
    }

    private JPanel makeMap_panel()
    {
        JPanel res = new JPanel();
        return res;
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

    private JPanel makeButtonPanel()
    {
        JPanel res = new JPanel();
        res.setLayout(new GridLayout(1, 3));

        // Button


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
        res.add(makeButtonPanel());

        return res;
    }


    private void makeCaros(MapConfig map_config)
    {
        Cellule[][] tab = map_config.getGrid();
        for(int i = 0; i < tab.length; i++)
        {
            for(int j = 0; j < tab[i].length; i++)
            {
                caros.add(new Caro(new Coordinate(i, j), 1, 1, scale_width, scale_height, tab[i][j].getType()));
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

}