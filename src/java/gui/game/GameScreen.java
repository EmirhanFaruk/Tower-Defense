package gui.game;

import config.Cellule;
import config.MapConfig;
import gui.Coordinate;
import gui.game.paint.Caro;
import gui.game.paint.MonsterGraphics;
import gui.game.paint.TourGraphics;
import gui.game.paint.TourPaintable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameScreen extends JPanel
{
    ArrayList<Caro> caros;
    ArrayList<MonsterGraphics> monster_aff;
    ArrayList<TourGraphics> tour_aff;

    private static int tile_width, tile_height;

    private int tile_offset_width, tile_offset_height;



    public GameScreen(int width, int height)
    {
        caros = new ArrayList<>();
        monster_aff = new ArrayList<>();
        tour_aff = new ArrayList<>();

        setSize(width, height);
        setBackground(new Color(66, 40, 14));
    }

    public void make(MapConfig map_config)
    {
        tile_width = getWidth() / 16; // normalement 16, c'est pour etre sur d'avoir toutes les caros
        tile_height = getHeight() / 8; // // normalement 8, c'est pour etre sur d'avoir toutes les caros

        tile_offset_width = (getWidth() - (tile_width * 16));
        tile_offset_height = (getHeight() - (tile_height * 8));


        makeCaros(map_config);
    }

    private void makeCaros(MapConfig map_config)
    {
        Cellule[][] tab = map_config.getGrid();
        for(int i = 0; i < tab.length; i++)
        {
            for(int j = 0; j < tab[i].length; j++)
            {
                // i = height, j = width
                Coordinate temp_coord = new Coordinate(tile_offset_height + i * tile_height, tile_offset_width + j * tile_width);
                caros.add(new Caro(temp_coord, tile_width, tile_height, tab[i][j].getType()));
            }
        }
    }



    public void update()
    {
        updateMonsters();
    }

    public void updateTours()
    {

    }

    public void updateMonsters()
    {
        for(MonsterGraphics mp : monster_aff)
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
        Graphics2D g2 = (Graphics2D) g;

        // Everything to draw goes here using g2
        for (Caro caro : caros)
        {
            caro.paint(g2);
        }
        for (MonsterGraphics monsterGraphics : monster_aff){
            monsterGraphics.paint(g2);
        }
        for ( TourGraphics tourGraphics : tour_aff){
            tourGraphics.paint(g2);
        }

        g2.dispose();
    }

    public static int getTile_height() {
        return tile_height;
    }

    public static int getTile_width() {
        return tile_width;
    }
}