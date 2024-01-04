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

    private int tile_width, tile_height;

    private int tile_offset_width;

    public GameScreen(int width, int height, MapConfig map_config)
    {
        caros = new ArrayList<>();
        monster_aff = new ArrayList<>();
        tour_aff = new ArrayList<>();

        setSize(width, height);
        setBackground(new Color(66, 40, 14));

        tile_width = getWidth() / 16;
        tile_height = getHeight() / 9;

        tile_offset_width = (getWidth() - tile_width * 16);


        makeCaros(map_config);

    }

    private void makeCaros(MapConfig map_config)
    {
        Cellule[][] tab = map_config.getGrid();
        for(int i = 0; i < tab.length; i++)
        {
            for(int j = 0; j < tab[i].length; j++)
            {
                Coordinate temp_coord = new Coordinate(tile_offset_width + i * tile_width, j * tile_height);
                caros.add(new Caro(temp_coord, tile_width, tile_height, tab[i][j].getType()));
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
        Graphics2D g2 = (Graphics2D) g;

        // Everything to draw goes here using g2
        for (Caro caro : caros)
        {
            caro.paint(g2);
        }

        g2.dispose();
    }
}