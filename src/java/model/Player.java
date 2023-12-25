package model;

import java.util.Scanner;

public class Player {
    String name;
    Scanner scanAnswer ;

    public Player() {
        // Utilisez le même scanner pour lire le nom
        this.scanAnswer = new Scanner(System.in);
        System.out.print("Veuillez donner un nom : ");
        this.name = scanAnswer.nextLine();
    }

    // Demande si le joueur veut-il jouer ou non
    public boolean wantPlay() {
        System.out.print("Voulez-vous jouer (oui/non) : ");
        // Utilisez le scanner de la classe
        return scanAnswer.nextLine().replaceAll("\\s", "").equalsIgnoreCase("oui");
    }

    // Demande au joueur de donner une coordonnée
    public int[] enterCoordinates() {
        System.out.print("Veuillez saisir une coordonnée (exemple : A6) : ");
        // Utilisez le scanner de la classe
        String str = scanAnswer.nextLine().replaceAll("\\s", "");
        int[] tab = new int[2];
        tab[0] = str.charAt(0) - 64;
        tab[1] = str.charAt(1) - 48;
        return tab;
    }

    // Demande si le joueur veut-il effectuer une action
    public boolean requestAction() {
        System.out.print("Voulez-vous poser une tour (oui/non) ?");
        // Utilisez le scanner de la classe
        String str = scanAnswer.nextLine().replaceAll("\\s", "");
        return str.equalsIgnoreCase("oui");
    }

    // Ferme le scanner lorsqu'il n'est plus nécessaire
    public void closeScanner() {
        scanAnswer.close();
    }
}
