package com.model;

import com.config.MapConfig;

import java.util.Scanner;

public class Player {
    String name;
    Scanner scanAnswer ;

    public Player() {
        // Utilisez le même scanner pour lire le nom
        this.scanAnswer = new Scanner(System.in);
        System.out.print("Veuillez-vous donner un nom : ");
        this.name = scanAnswer.nextLine();
    }

    /**
     * Demande si le joueur veut-il jouer ou non
     * @return
     */
    public boolean wantPlay() {
        System.out.print("Voulez-vous jouer (oui/non) ? : ");
        // Utilisez le scanner de la classe
        String userInput = scanAnswer.nextLine().replaceAll("\\s", "").toLowerCase();
        if (userInput.equals("oui")) {
            return true;
        } else if (userInput.equals("non")) {
            return false;
        } else {
            System.out.println("Ce n'est pas la réponse attendue.");
            return wantPlay();
        }
    }

    /**
     *  Demande au joueur de donner une coordonnée
     * @param mapConfig
     * @return tab
     */
    public int[] enterCoordinates(MapConfig mapConfig) {
        System.out.print("Veuillez saisir une coordonnée (exemple : A6) : ");
        // Utilisez le scanner de la classe
        String str = scanAnswer.nextLine().replaceAll("\\s", "");
        // Vérifier que la chaîne a la bonne longueur
        if (str.length() == 2 || str.length() == 3) {
            char firstChar = str.charAt(0);
            int secondChar;
            if ( str.length()==2 ) {
               secondChar = Integer.parseInt(String.valueOf(str.charAt(1)));
            } else {
                secondChar =Integer.parseInt(str.substring(1));
            }
            // Vérifier que le premier caractère est une lettre majuscule entre A et H inclus et le deuxième caractère est un nombre entre 1 et 16 inclus
            if (firstChar >= 'A' && firstChar <= 'H' && secondChar >= 1 && secondChar<= 16) {
                int x = firstChar - 'A'; // Convertir la lettre en indice (A=1, B=2, ..., H=8)
                int y = secondChar - 1;
                // Vérifier que
                if (mapConfig.getGrid()[x][y].getType() == 0 ) {
                    int[] tab = new int[2];
                    tab[0] = x ;
                    tab[1] = y ;
                    return tab;
                } else {
                    System.out.println("Impossible de poser la tour à cette endroit. Veuillez saisir une coordonnée valide.");
                    return enterCoordinates(mapConfig); // Appel récursif pour demander une nouvelle saisie
                }
            }
        }
        // Si la coordonnée n'est pas valide, afficher un message d'erreur et demander une nouvelle saisie
        System.out.println("Coordonnée invalide. Veuillez saisir une coordonnée valide.");
        return enterCoordinates(mapConfig); // Appel récursif pour demander une nouvelle saisie
    }

    /**
     * Demande si le joueur veut-il effectuer une action
     * @return bool
     */
    public boolean requestAction() {
        System.out.print("Voulez-vous poser une tour(oui/non) ? : ");
        // Utilisez le scanner de la classe
        String userInput = scanAnswer.nextLine().replaceAll("\\s", "").toLowerCase();
        if (userInput.equals("oui")) {
            return true;
        } else if (userInput.equals("non")) {
            return false;
        } else {
            System.out.println("Ce n'est pas la réponse attendue.");
            return requestAction();
        }
    }

    /**
     * Demande quel type de tour veut-il acheter
     * @return string
     */
    public String chooseTowerType(){
        System.out.print("Quelles types de tour voulez-vous acheter ( canon/archer/soldat/catapulte ) ? : ");
        String userInput = scanAnswer.nextLine().replaceAll("\\s", "").toLowerCase();
        switch (userInput){
            case "archer"  : return "archer" ;
            case "catapulte" : return "catapulte" ;
            case "canon" : return "canon" ;
            case "soldat" : return "soldat" ;
            default : System.out.println("Cette tour n'existe pas.") ; return chooseTowerType() ;
        }
    }

    /**
     * Demande quel niveau de tour veut-il acheter
     * @return
     */
    public int chooseTowerLevel(){
        System.out.print(" Choisissez un niveau entre 1 et 3 pour votre tour : ");
        String userInput = scanAnswer.nextLine().replaceAll("\\s", "").toLowerCase();
        switch (Integer.parseInt(userInput)){
            case 1  : return 1 ;
            case 2 : return 2 ;
            case 3 : return 3 ;
            default : System.out.println("Ce niveau n'existe pas.") ; return chooseTowerLevel() ;
        }
    }

    // Ferme le scanner lorsqu'il n'est plus nécessaire
    public void closeScanner() {
        scanAnswer.close();
    }

    /* getters et setters */
    public Scanner getScanAnswer() {
        return scanAnswer;
    }
}
