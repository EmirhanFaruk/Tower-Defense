package gui.mainmenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu extends JPanel
{
    /**
     * Home button.
     */
    public class HomeButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setMode("HOME");
            cardLayout.show(main_panel, home_mode);
        }
    }

    /**
     * Play button.
     */
    public class PlayButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setMode("PLAY");
            cardLayout.show(main_panel, play_mode);
        }
    }

    /**
     * Settings button.
     */
    public class SettingsButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setMode("SETTINGS");
            cardLayout.show(main_panel, settings_mode);
        }
    }

    private String map_choisi;
    private String difficulte;

    private int width, height;
    private boolean fullscreen = false;


    private JPanel button_panel;
    private JPanel main_panel;


    private final HomeButton hbl = new HomeButton();
    private final PlayButton pbl = new PlayButton();
    private final SettingsButton sbl = new SettingsButton();

    private String mode = "HOME";

    private final CardLayout cardLayout = new CardLayout();

    private final String home_mode = "HOME";
    private final String play_mode = "PLAY";
    private final String settings_mode = "SETTINGS";

    /**
     * Constructeur de Menu.
     * @param width largueur
     * @param height hauteur
     * @throws IOException exception pour le path dans home image
     */
    public Menu(int width, int height) throws IOException
    {
        this.width = width;
        this.height = height;

        make();
    }

    /**
     * Produire le panel.
     * @throws IOException exception pour le path
     */
    private void make() throws IOException
    {
        for(int i = 0; i < this.getComponentCount(); i++)
        {
            System.out.println(this.getComponent(i));
        }
        // Le reste: affichage
        // En bas: home - play - settings
        this.setLayout(new BorderLayout());

        button_panel = makeButtonPanel();
        main_panel = makeMainPanel();



        this.add(button_panel, BorderLayout.SOUTH);
        this.add(main_panel);
    }

    private void setMode(String mode) {this.mode = mode;}

    /**
     * Produire le panel avec les buttons.
     * @return JPanel des buttons
     */
    private JPanel makeButtonPanel()
    {
        JPanel res = new JPanel();
        res.setLayout(new GridLayout(1, 3));

        JButton home = new JButton("HOME");
        home.addActionListener(hbl);
        JButton play = new JButton("PLAY");
        play.addActionListener(pbl);
        JButton settings = new JButton("SETTINGS");
        settings.addActionListener(sbl);

        JButton[] bl = {home, play, settings};
        for (JButton button : bl)
        {
            button.setBorderPainted(false);
            button.setBackground(Color.BLACK);
            button.setForeground(Color.GRAY);
        }

        res.add(home);
        res.add(play);
        res.add(settings);

        return res;
    }

    /**
     * Produire le main panel qui sera dans le JFrame quand le mode est "menu"
     * @return main panel
     * @throws IOException exception pour le path
     */
    private JPanel makeMainPanel() throws IOException
    {
        JPanel res  = new JPanel();
        // Pour pouvoir changer le mode
        res.setLayout(cardLayout);

        // Ajout des modes
        res.add(home_mode, makeHome());
        res.add(play_mode, makePlay());
        //res.add(settings_mode, makeSettings());

        cardLayout.show(res, home_mode);

        return res;
    }

    /**
     * Produire JPanel pour home: Image de menu.
     * @return JPanel de home
     * @throws IOException exception pour le path
     */
    private JPanel makeHome() throws IOException
    {
        JPanel res = new JPanel();
        String path = System.getProperty("user.dir");

        // Getting home image
        BufferedImage home_image;
        try
        {
            home_image = ImageIO.read(new File(path + "/src/resources/images/menu/Menu.png"));
        }
        catch (Exception e)
        {
            home_image = ImageIO.read(new File(path + "\\src\\resources\\images\\menu\\Menu.png"));
        }

        Image scaled_home_image = home_image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        JLabel label = new JLabel(new ImageIcon(scaled_home_image));
        res.add(label);
        return res;
    }

    /**
     * Produire le menu de jeu ou on peut choisir un niveau et sa difficulté, et ou on peut lancer le jeu.
     * @return JPanel de play
     */
    private JPanel makePlay()
    {
        JPanel res = new JPanel();
        res.setBackground(Color.BLUE);
        return res;
    }

    /**
     * Produire le menu des parametres ou il y a le reglage de largeur et hauteur, et aussi fullscreen
     * @return Jpanel de settings
     */
    private JPanel makeSettings()
    {
        return null;
    }
}
