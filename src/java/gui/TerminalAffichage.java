package gui;


import config.Cellule;
import config.MapConfig ;
import model.Player ;
import model.Character ;

import java.io.File;
import java.util.Scanner;

public class TerminalAffichage {

    static class Plateau {
        // le nombre de colonnes
        final int width ;

        // le nombre de lignes
        final int height ;
        final int niveau ;

        // permet de dire comment est la map et où les monstres peuvent aller
        Cellule[][] tableau ;

        Player player ;

        Character character ;

        public Plateau(Player player, Character character) throws Exception {
            this.player = player;
            this.character = character;
            int tempNiveau;
            // Boucle pour demander au joueur de saisir un niveau valide
            do {
                System.out.print("Veuillez donner un niveau entre 1 et 4 : ");
                String tempInput = player.getScanAnswer().nextLine().replaceAll("\\s", "");
                try {
                    tempNiveau = Integer.parseInt(tempInput);
                    // Vérifiez si le niveau est dans la plage valide
                    if (tempNiveau >= 1 && tempNiveau <= 4) {
                        break; // Sort de la boucle si le niveau est valide
                    } else {
                        System.out.println("Le niveau doit être entre 1 et 4.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer un nombre valide.");
                }
            } while (true);
            this.niveau = tempNiveau;
            String temp = "Map" + this.niveau + ".txt";
            this.tableau = MapConfig.grid(temp);
            this.width = tableau[0].length;
            this.height = tableau.length;
        }


        public Plateau ( Player player ) throws Exception {
            this(player , null ) ;
        }

        // une fonction qui dit si on a perdu ou pas
        public boolean GameLose(){
            return Character.getLive() <= 0 ;
        }

        // une fonction qui ajoute au tableau l'apparition des monstres
        public void apparitionMonster(){

        }

        // une fonction qui affiche comment le jeu est à cette instance
        public void afficheCourant (){
            System.out.println(" Argent : " +character.getMoney());
            System.out.println(" Vie : " + Character.getLive());
            String colonne = "   ";
            for (int i = 1; i<=this.width;i++){
                if ( i < 10 ){
                    colonne = colonne + " " + i + " " ;
                } else {
                    colonne = colonne  + i + " ";
                }
            }
            System.out.println(colonne);
            String ligne = "";
            for ( int i =0 ; i <=colonne.length() ; i++ ) ligne = ligne + "-";
            System.out.println(ligne);
            for (int i =0; i < this.height ;i++) {
                System.out.print((char) (65 + i) + " |");
                for (int j = 0; j < this.width ; j++) {
                    if ( tableau[i][j].getType() == 0 ) {
                        System.out.print(" # "); // l'herbe
                    } else if (tableau[i][j].getType() == 1 ) {
                        System.out.print(" O "); // la route
                    }  else if (tableau[i][j].getType() == 2 ) {
                        System.out.print(" ~ "); // l'eau
                    }  else if (tableau[i][j].getType() == 3 ) {
                        System.out.print(" A "); // l'arbre
                    } else if (tableau[i][j].getType() == 4 )  {
                        System.out.print(" B "); // la base
                    } else if ( tableau[i][j].getType() == 5){
                        System.out.print(" T "); // une tour
                    } else {
                        System.out.print(" m ");
                    }
                }
                System.out.print("\n");
            }
        }

    }

    static class Jeu {
        Player player;
        Plateau plateau ;

        Character character ;

        public Jeu (Player player , Plateau plateau , Character character ){
            this.player = player;
            this.plateau = plateau ;
            this.character = character ;
        }

        public Jeu (Player player){
            this ( player ,  null , null ) ;
        }

        // une fonction qui nous fait jouer
        public void play () throws Exception {
            if (player.wantPlay()) { // si le player veut jouer
                this.plateau = new Plateau( player ) ; // demande la map que le player veut
                this.character = Character.chooseCharacter(player) ; // initialisation d'un character
                this.plateau.character = this.character ; // l'attribut character du plateau est initiaser
                while (!plateau.GameLose()) { // si le player n'a pas perdu
                    plateau.afficheCourant(); // affiche la map a cette instance
                    Thread.sleep(2000); // fait dormir le terminal 2 sec
                    if (player.requestAction()) { // demande si le player veut-il poser une tour
                        int[] val =player.enterCoordinates(); // demande au player de donner une coordonnée
                        this.plateau.tableau[val[0]][val[1]]=new Cellule(false , 5) ; //place la tour à la position que le player a demandée
                    }
                }
                play(); // quand le player a perdu faire la recursion pour une nouvelle partie
            } else { // si le player ne veut pas jouer
                player.closeScanner(); // ferme le scanner
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("-------- TOWER DEFENSE --------");
        Jeu jeu = new Jeu(new Player());
        jeu.play() ;
    }
}
