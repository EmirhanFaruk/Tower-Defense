package gui;

import config.MapConfig;
import gui.game.Game;
import gui.game.GameScreen;
import gui.game.GameWholeScreen;
import gui.mainmenu.Menu;
import model.Character;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;

public class GameView extends JFrame
{
    public static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];

    private boolean running = false;

    private boolean in_main_menu = true;

    private boolean in_game = false;

    private double scale;

    // Pour changer le mode
    private JPanel main_panel;
    private final CardLayout cardLayout = new CardLayout();

    private final String main_menu_screen_s = "MAIN MENU", ingame_screen_s = "INGAME";

    private gui.mainmenu.Menu menu;
    private GameWholeScreen game;


    /**
     * Constructeur de GameView, assigner les attributs
     */
    public GameView(int width, int height)
    {
        // Les attributs de JPanel
        this.setTitle("Tower Defense");
        this.setSize(width, height);
        this.setPreferredSize(new Dimension(width, height));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // On commence par menu
        // On ne peut pas produire game encore car on n'a pas encore choisit le map.
        menu = new Menu(width, height, this);

        main_panel = new JPanel();
        main_panel.setLayout(cardLayout);
        main_panel.add(main_menu_screen_s, menu);
        cardLayout.show(main_panel, main_menu_screen_s);

        this.add(main_panel);

        this.setVisible(true);

    }


    public void startGame(int width, int height, String map, String difficulty, String mode, String character)
    {
        int wave_count_max = 4;
        switch (difficulty)
        {
            case "EASY": wave_count_max = 4; break;
            case "NORMAL": wave_count_max = 6; break;
            case "HARD": wave_count_max = 8; break;
        }
        if(mode.equals("MARATHON")){ wave_count_max = -1;}

        // Defaulf character
        Character game_character = new Character("villageois", 200, 5);
        switch (character)
        {
            case "COMMANDANT": game_character = new Character("commandant", 300, 10); break;
            case "SOLDAT": game_character = new Character("artilleur", 250, 7); break;
            case "ARCHER": game_character = new Character("archer", 250, 7); break;
            case "VILLAGEOIS" : game_character = new Character("villageois", 200, 5); break;
        }

        pack();
        getDevice().setFullScreenWindow(this);
        setSize(width, height);


        game = new GameWholeScreen(main_panel.getWidth(), main_panel.getHeight(), map, difficulty, wave_count_max, game_character);


        main_panel.add(ingame_screen_s, game);
        game.make();
        cardLayout.show(main_panel, ingame_screen_s);

    }

    @Override
    public void setSize(int width, int height)
    {
        super.setSize(width, height);
        if(main_panel != null)
        {
            main_panel.setSize(width, height);
        }
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
