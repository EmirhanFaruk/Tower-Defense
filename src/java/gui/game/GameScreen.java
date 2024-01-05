package gui.game;

import config.Cellule;
import config.MapConfig;
import gui.Coordinate;
import gui.game.paint.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameScreen extends JPanel
{
    ArrayList<Caro> caros;
    ArrayList<MonsterGraphics> monster_aff;
    ArrayList<TourGraphics> tour_aff;

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

    private void makeMap_panel()
    {
        JPanel res = new JPanel();

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

    public void updateMonsters(Graphics g)
    {
        for(MonsterGraphics mp : monster_aff)
        {
            mp.update(g);
            if(mp.getMonster().isDead())
            {
                monster_aff.remove(mp);
            }
        }
    }

}