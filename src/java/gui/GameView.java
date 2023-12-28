package gui;

import config.MapConfig;
import model.monster.MonsterSpawner;

import javax.swing.*;

public class GameView extends JPanel
{
    private boolean running;

    private MapConfig map_config;
    private MonsterSpawner monster_spawner;

    /**
     * Constructeur de GameView, assigner les attributs
     */
    public GameView()
    {
        // Produire les attributs etc...
    }

    /**
     * Une func qui fait rouler le mainLoop
     */
    public void run()
    {
        while(running)
        {
            mainLoop();
        }
    }

    /**
     * Une func qui contient les mise a jours de code et de graphics de jeu
     */
    private void mainLoop()
    {
        updateCode();
        updateGraphs();
    }

    /**
     * Une function qui ensemble les mise a jours de monstres, les tours...
     */
    private void updateCode()
    {

    }

    /**
     * Une func qui met a jour le panel pour afficher les elements de jeu
     */
    private void updateGraphs()
    {

    }

}
