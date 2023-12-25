package gui;


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
        int[][] tableau ;

        public Plateau () throws Exception {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Veuillez donner un niveau entre 1 et 5 : ");
            this.niveau = Integer.parseInt(scanner.nextLine().replaceAll("\\s", ""));
            String temp = "Map" + this.niveau + ".txt";
            String path = System.getProperty("user.dir") ;
            File file;
            try {
                file =new File(path+"/src/resources/"+temp);
            } catch (Exception e ){
                file =new File(path+"\\src\\resources\\"+temp);
            }
            this.width = MapConfig.compteLongeur(temp , new  Scanner( new File(String.valueOf(file))));
            this.height = MapConfig.compteLigne(temp , new Scanner( new File(String.valueOf(file))));
            this.tableau = MapConfig.gridInteger(temp , new Scanner( new File(String.valueOf(file))));
            scanner.close();
        }

        // une fonction qui dit si on a perdu ou pas
        public boolean GameLose(){
            return Character.getLive() <= 0 ;
        }

        // une fonction qui affiche comment le jeu est à cette instance
        public void afficheCourant (){
            System.out.println("Niveau : "+this.niveau);
        }

    }

    static class Jeu {
        Player player;
        Plateau plateau ;

        public Jeu (Player player , Plateau plateau ){
            this.player = player;
            this.plateau = plateau ;
        }

        public void play (){
            do {
                if (player.wantPlay()) {
                    while (!plateau.GameLose()) {
                        if (player.requestAction()) {
                            player.enterCoordinates();
                        }
                        plateau.afficheCourant();
                    }
                }
            } while (player.wantPlay());
            player.closeScanner();
        }


    }

    public static void main(String[] args) throws Exception {
        System.out.println("-------- TOWER DEFENSE --------");
        Jeu jeu = new Jeu(new Player() , new Plateau());
        jeu.play() ;
    }
}
