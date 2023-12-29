package model;

import model.tour.Archer;
import model.tour.Tour;

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

    // Demande si le joueur veut-il jouer ou non
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

    // Demande au joueur de donner une coordonnée
    public int[] enterCoordinates() {
        System.out.print("Veuillez saisir une coordonnée (exemple : A6) : ");
        // Utilisez le scanner de la classe
        String str = scanAnswer.nextLine().replaceAll("\\s", "");
        // Vérifier que la chaîne a la bonne longueur
        if (str.length() == 2 || str.length() == 3) {
            char firstChar = str.charAt(0);
            int secondChar = 0  ;
            if ( str.length()==2 ) {
               secondChar = Integer.parseInt(String.valueOf(str.charAt(1)));
            } else {
                secondChar = Integer.parseInt(String.valueOf(str.charAt(1)+str.charAt(2)));
            }
            // Vérifier que le premier caractère est une lettre majuscule entre A et H inclus
            if (firstChar >= 'A' && firstChar <= 'H') {
                // Vérifier que le deuxième caractère est un nombre entre 1 et 16 inclus
                if (secondChar >= 1 && secondChar<= 16) {
                    int[] tab = new int[2];
                    tab[0] = firstChar - 'A'; // Convertir la lettre en indice (A=1, B=2, ..., H=8)
                    tab[1] = secondChar - 1 ;
                    return tab;
                }
            }
        }
        // Si la coordonnée n'est pas valide, afficher un message d'erreur et demander une nouvelle saisie
        System.out.println("Coordonnée invalide. Veuillez saisir une coordonnée valide.");
        return enterCoordinates(); // Appel récursif pour demander une nouvelle saisie
    }


    // Demande si le joueur veut-il effectuer une action
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

    // Demande quel type de tour veut-il acheter
    public String chooseTowerType(){
        System.out.print("Quelles types de tour voulez-vous acheter ( canon/archer/arme/artillerie ) ? : ");
        String userInput = scanAnswer.nextLine().replaceAll("\\s", "").toLowerCase();
        switch (userInput){
            case "archer"  : return "archer" ;
            case "artillerie" : return "artillerie" ;
            case "canon" : return "canon" ;
            case "arme" : return "arme" ;
            default : System.out.println("Cette tour n'existe pas.") ; return chooseTowerType() ;
        }
    }

    // Ferme le scanner lorsqu'il n'est plus nécessaire
    public void closeScanner() {
        scanAnswer.close();
    }

    public String getName() {
        return name;
    }
    public Scanner getScanAnswer() {
        return scanAnswer;
    }
}
