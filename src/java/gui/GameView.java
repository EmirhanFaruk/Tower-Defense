package gui;

import config.MapConfig;
import gui.game.Game;
import gui.game.GameWholeScreen;
import gui.mainmenu.Menu;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameView extends JFrame
{
    public static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];

    private boolean running = false;

    private boolean in_main_menu = true;

    private boolean in_game = false;

    private double scale;

    private JPanel main_panel;

    private gui.mainmenu.Menu menu;
    private Game game;


    /**
     * Constructeur de GameView, assigner les attributs
     */
    public GameView(int width, int height) throws Exception {
        // Les attributs de JPanel
        this.setTitle("Tower Defense");
        this.setSize(width, height);
        this.setPreferredSize(new Dimension(width, height));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // On commence par menu
        /*
        menu = new Menu(width, height, this);
        this.add(menu);
         */

        MapConfig mapConfig = new MapConfig(MapConfig.grid("Map4.txt"));
        GameWholeScreen temp = new GameWholeScreen(width, height);
        this.add(temp);
        pack();
        temp.make(mapConfig);

        this.setVisible(true);
        // On ne peut pas produire game encore car on n'a pas encore choisit le map.
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
        if(in_main_menu)
        {
            logicMenu();
        }
        else if(in_game)
        {
            logicGame();
        }
    }

    /**
     * Mettre a jour le panel pour afficher les elements de jeu
     */
    private void updateGraphs()
    {
        if(in_main_menu)
        {
            drawMenu();
        }
        else if(in_game)
        {
            drawGame();
        }
    }

    /**
     * Afficher le menu
     */
    private void drawMenu()
    {

    }

    /**
     * Partie logique de Menu
     */
    private void logicMenu()
    {

    }

    /**
     * Afficher le jeu
     */
    private void drawGame()
    {

    }

    /**
     * Partie logique du jeu
     */
    private void logicGame()
    {

    }


    public GraphicsDevice getDevice()
    {
        return device;
    }
}
