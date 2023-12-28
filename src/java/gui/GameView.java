package gui;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame
{
    private boolean running = false;

    private boolean in_main_menu = true;

    private boolean in_game = false;

    private int width, height, scale;

    private JPanel main_panel;

    private Menu menu;
    private Game game;


    /**
     * Constructeur de GameView, assigner les attributs
     */
    public GameView(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.setPreferredSize(new Dimension(width, height));
        menu = new Menu(width, height);
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
}
