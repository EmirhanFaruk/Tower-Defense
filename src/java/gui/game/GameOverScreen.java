package gui.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverScreen extends JPanel {

    private JLabel gameOverLabel;
    private JButton restartButton;
    private JButton exitButton;

    public GameOverScreen() {

        // Message de fin de jeu
        gameOverLabel = new JLabel("Game Over!");
        gameOverLabel.setHorizontalAlignment(JLabel.CENTER);
        add(gameOverLabel);

        // Bouton pour redémarrer le jeu
        restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ajoutez ici le code pour redémarrer le jeu
            }
        });
        add(restartButton);

        // Bouton pour quitter le jeu
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ajoutez ici le code pour quitter le jeu

            }
        });
        add(exitButton);
    }
}