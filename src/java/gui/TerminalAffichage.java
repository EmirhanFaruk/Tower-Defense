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

        public Plateau ( Player player , Character character ) throws Exception {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Veuillez donner un niveau entre 1 et 4 : ");
            this.niveau = Integer.parseInt(scanner.nextLine().replaceAll("\\s", ""));
            String temp = "Map" + this.niveau + ".txt";
            this.tableau = MapConfig.grid(temp);
            this.width = tableau[0].length ;
            this.height = tableau.length ;
            this.player = player ;
            this.character = character ;
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
                    } else{
                        System.out.print(" T "); // une tour
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
                this.character = new Character("test" , 200 , 10 ) ; // test pour voir si play() fonctionne
                // il faut écrire une fonction qui permet d'écrire un nom pour créer un character
                this.plateau.character = this.character ; // l'attribut character du plateau est initiaser
                while (!plateau.GameLose()) { // si le player n'a pas perdu
                    plateau.afficheCourant(); // affiche la map a cette instance
                    Thread.sleep(2000); // fait dormir le terminal 2 sec
                    if (player.requestAction()) { // demande si le player veut-il poser une tour
                        player.enterCoordinates(); // demande au player de donner une coordonnée
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
