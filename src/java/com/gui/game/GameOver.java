package com.gui.game;
public class GameOver {

    private boolean gameEnded;

    public GameOver() {
        this.gameEnded = false;
    }

    public void checkGameOverCondition(Game game) {
        if (game.gameOverCondition()) {
            gameEnded = true;
            endGame();
        }
    }

    public void endGame() {
        // Ajoutez ici toute autre logique de fin de jeu nécessaire
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public void resetGame() {
        gameEnded = false;
        // Ajoutez ici toute autre logique de réinitialisation du jeu nécessaire
    }
}