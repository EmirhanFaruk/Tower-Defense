package gui.game;

import config.MapConfig;
import model.monster.Monster;
import model.monster.MonsterSpawner;
import model.tour.Tour;

import javax.swing.*;
import java.util.ArrayList;

public class Game extends JPanel
{
    private MapConfig map_config;

    private MonsterSpawner monster_spawner;

    private ArrayList<Monster> monsters;

    // Les tours seront encapsulé dans les cellules, cette liste va avoir leurs references
    private ArrayList<Tour> tours;

    /**
     * Constructeur de Game. Produire map_config depuis map(path) et
     * @param map path de map
     * @param diff difficulté pour monster_spawner
     * @throws Exception pour map_config
     */
    public Game(String map, String diff) throws Exception
    {
        MapConfig map_config = MapConfig.make(map);
        monster_spawner = new MonsterSpawner(20, 5, 8, 1, map_config);
        monsters = new ArrayList<>();
        tours = new ArrayList<>();
    }


}
