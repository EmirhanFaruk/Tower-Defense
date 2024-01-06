package gui.game;

import config.MapConfig;
import gui.GameView;
import gui.game.paint.MonsterGraphics;
import model.Character;
import model.monster.Monster;
import model.tour.Catapulte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameWholeScreen extends JPanel {
    private GameScreen main_panel;
    private JPanel button_panel;

    private JLabel message_panel;
    private JPanel mid_button_panel;

    private final CardLayout cardLayout = new CardLayout();

    private final String menu_none = "NONE";
    private final String menu_tour = "TOUR";
    private final String menu_menu = "MENU";

    private boolean playing = true;

    private Game game;

    private GameView frame;


    public GameWholeScreen(int width, int height, String map, String difficulty, int wave_count_max, Character character, GameView frame) {
        this.frame = frame;

        setSize(width, height);
        game = new Game(map, difficulty, wave_count_max, character);

        setLayout(new BorderLayout());
        main_panel = new GameScreen(width, height, this);
        button_panel = makeButton_panel();

        add(main_panel, BorderLayout.CENTER);
        add(button_panel, BorderLayout.SOUTH);


    }

    public void make() {
        main_panel.make(game.getMap_config());
    }


    public void update(double delta_time) {
        // Playing = unpaused
        if (playing) {
            game.updateEnts(delta_time);
            String message;
            if(game.gameOverCondition())
            {
                message = "Game Over!";
                playing = false;
            }
            else
            {
                Character character = game.getCharacter();
                int wc = game.getMonster_spawner().getWave_count();
                message = "Money: " + character.getMoney() + ", Live: " + character.getLive() + ", Wave: " + wc + "/";
                int wcm = game.getMonster_spawner().getWave_count_max();
                if(wcm != -1)
                {
                    message = message + wcm;
                }
                else
                {
                    message = message + "infinite";
                }
            }
            updateMessage(message);
            repaint();
        }

    }

    private JLabel makeMessagePanel() {
        JLabel message_panel ;
        message_panel = new JLabel("Welcome to the game!");
        // Brown background
        message_panel.setBackground(new Color(102, 61, 20));
        message_panel.setForeground(Color.ORANGE);

        return message_panel;
    }

    /**
     * "Placehoder" pour le menu d'achat de tour
     *
     * @return
     */
    private JPanel makeBrownPanel() {
        JPanel res = new JPanel();
        // Brown background
        res.setBackground(new Color(102, 61, 20));

        return res;
    }

    private JPanel makeMenuPanel() {
        JPanel res = new JPanel();
        // Brown background
        res.setBackground(new Color(102, 61, 20));

        res.setLayout(new GridLayout(1, 5));

        JButton pause_button = new JButton("Pause"),
                menu_button = new JButton("Quit to Main Menu");

        JButton[] menu_list = {pause_button, menu_button};
        for (JButton button : menu_list) {
            button.setBackground(new Color(102, 61, 20));
            button.setForeground(Color.ORANGE);
        }

        pause_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playing = !playing;

                String message;
                if(game.gameOverCondition())
                {
                    message = "Game Over!";
                    playing = false;
                }
                else
                {
                    Character character = game.getCharacter();
                    int wc = game.getMonster_spawner().getWave_count();
                    message = "Money: " + character.getMoney() + ", Live: " + character.getLive() + ", Wave: " + wc + "/";
                    int wcm = game.getMonster_spawner().getWave_count_max();
                    if(wcm != -1)
                    {
                        message = message + wcm;
                    }
                    else
                    {
                        message = message + "infinite";
                    }
                }

                message = message + " PAUSED ";
                updateMessage(message);
            }
        });

        menu_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.quitMainMenu();
            }
        });

        res.add(pause_button);
        res.add(menu_button);

        return res;
    }

    private JPanel makeTourPanel() {
        JPanel res = new JPanel();
        // Brown background
        res.setBackground(new Color(102, 61, 20));

        res.setLayout(new GridLayout(1, 5));

        String[] tour_list = {"Archer", "Canon", "Catapulte", "Soldat", "Upgrade"};
        for (String choice : tour_list) {
            JButton button = new JButton(choice);
            button.setBackground(new Color(102, 61, 20));
            button.setForeground(Color.ORANGE);
            if (!choice.equals("Upgrade")) {
                ActionListener listener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Upgrade Tour
                    }
                };
            } else {
                ActionListener listener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Add Tour
                    }
                };
            }

            res.add(button);
        }
        return res;
    }


    private JPanel makeMenuButtonPanel() {
        JPanel res = new JPanel();
        res.setLayout(new GridLayout(1, 2));

        // Tour achat button
        JButton ta = new JButton("Acheter Tour");
        JButton menu = new JButton("Menu");
        JButton[] temp = {ta, menu};
        for (JButton button : temp) {
            button.setBackground(new Color(102, 61, 20));
            button.setForeground(Color.ORANGE);
        }

        ta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mid_button_panel, menu_tour);
            }
        });

        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mid_button_panel, menu_menu);
            }
        });


        res.add(ta);
        res.add(menu);

        return res;
    }


    /**
     * Faire le panel des buttons qui contient acheter, pause et quit
     *
     * @return
     */
    private JPanel makeButton_panel() {
        JPanel res = new JPanel();
        res.setBackground(new Color(66, 40, 14));

        /*
         * 1 - message
         * 2 - choix pour le tour et menu
         * 3 - les buttons
         */
        res.setLayout(new GridLayout(3, 1));

        message_panel = makeMessagePanel();
        mid_button_panel = new JPanel();
        mid_button_panel.setLayout(cardLayout);
        mid_button_panel.add(menu_none, makeBrownPanel());
        mid_button_panel.add(menu_tour, makeTourPanel());
        mid_button_panel.add(menu_menu, makeMenuPanel());

        res.add(message_panel);
        res.add(mid_button_panel);
        res.add(makeMenuButtonPanel());

        return res;
    }

    public void updateMessage(String text)
    {
        if (message_panel != null) {
            SwingUtilities.invokeLater(() -> {
                message_panel.setText(text);
            });
        }
    }

    public Game getGame() {return game;}
}
