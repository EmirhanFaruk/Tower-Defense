package gui.TerminalAffichage;

import config.MapConfig;
import model.Character;
import model.Player;
import model.monster.MonsterSpawner;

public class Plateau {
    // le nombre de colonnes
    final int width ;

    // le nombre de lignes
    final int height ;
    final int niveau ;

    // permet de dire comment est la map et où les monstres peuvent aller
    MapConfig tableau ;
    Player player ;
    Character character ;
    MonsterSpawner monsterSpawner ;

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
        this.tableau = new MapConfig(MapConfig.grid(temp));
        this.width = tableau.getGrid()[0].length;
        this.height = tableau.getGrid().length;
        this.monsterSpawner = new MonsterSpawner(20 , 20 , 10 , 20 ,this.tableau ) ;
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
                if ( tableau.getGrid()[i][j].getType() == 0 ) {
                    System.out.print(" # "); // l'herbe
                } else if (tableau.getGrid()[i][j].getType() == 1 ) {
                    System.out.print(" O "); // la route
                }  else if (tableau.getGrid()[i][j].getType() == 2 ) {
                    System.out.print(" ~ "); // l'eau
                }  else if (tableau.getGrid()[i][j].getType() == 3 ) {
                    System.out.print(" A "); // l'arbre
                } else if (tableau.getGrid()[i][j].getType() == 4 )  {
                    System.out.print(" B "); // la base
                } else if ( tableau.getGrid()[i][j].getType() == 5){
                    System.out.print(" T "); // une tour
                } else {
                    System.out.print(" m ");
                }
            }
            System.out.print("\n");
        }
    }

}
