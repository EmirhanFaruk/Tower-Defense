package gui;

import config.MapConfig;
import model.monster.MonsterSpawner;

import javax.swing.*;

public class GameView extends JPanel
{
    private boolean running;

    private MapConfig map_config;
    private MonsterSpawner monster_spawner;

    public GameView()
    {
        // Produire les attributs etc...
    }

    public void run()
    {
        while(running)
        {
            mainLoop();
        }
    }
    private void mainLoop()
    {
        updateCode();
        updateGraphs();
    }

    private void updateCode()
    {

    }

    private void updateGraphs()
    {

    }

}
