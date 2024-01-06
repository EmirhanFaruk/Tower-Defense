package gui.game;

import config.Cellule;
import config.MapConfig;
import gui.Coordinate;
import gui.GameView;
import gui.game.paint.*;
import model.tour.Tour;
import model.monster.Monster;

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
    private GameWholeScreen gameWholeScreen;

    private GameWholeScreen gameWholeScreen;



    public GameScreen(int width, int height, GameWholeScreen ghs)
    {
        gameWholeScreen = ghs;
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

        TourGraphics.setWH(tile_width, tile_height);
        TourGraphics.setImages();
        makeCaros(map_config);
        MonsterGraphics.setWH(tile_width , tile_height);
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
        //updateMonsters();
    }

    public void updateTours(Graphics2D g2)
    {
        for(Tour tour : gameWholeScreen.getGame().getTours())
        {
            TourGraphics.paint(g2, tour, gameWholeScreen.getGame().getMap_config());
        }
    }

    public void updateMonsters(Graphics2D g)
    {
        if (  ! gameWholeScreen.getGame().getMonsters().isEmpty()) {
            for (Monster monster : gameWholeScreen.getGame().getMonsters()) {
                if (monster.isDead()) {
                    gameWholeScreen.getGame().getMonsters().remove(monster);
                } else {
                    MonsterGraphics.paint(g, monster);
                }
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
        updateMonsters(g2);
        updateTours(g2);
        g2.dispose();
    }

    public static int getTile_height() {
        return tile_height;
    }

    public static int getTile_width() {
        return tile_width;
    }
}