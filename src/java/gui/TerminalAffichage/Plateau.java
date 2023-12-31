package gui.TerminalAffichage;

import config.MapConfig;
import model.Character;
import model.Player;
import model.monster.Monster;
import model.monster.MonsterSpawner;

import java.util.ArrayList;

public class Plateau {
    final int width ; // le nombre de colonnes
    final int height ;  // le nombre de lignes
    final int niveau ; // niveau entre 1 et 3
    private MapConfig tableau ; // permet de dire comment est la map et où les monstres peuvent aller
    private Character character ;
    private MonsterSpawner monsterSpawner ;
    private ArrayList<Monster> monsters ;

    public Plateau(Player player) throws Exception {
        this.character = chooseCharacter(player) ;
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
        this.monsterSpawner = new MonsterSpawner(20 , 20 , 10 , 20 ,this.tableau ,this.character) ;
        this.monsters = new ArrayList<>() ;
    }
    public Character chooseCharacter(Player player) {
        System.out.print("Voulez choisir un personnage (commandant, artilleur, archer, villageois) ? : ");
        String userInput = player.getScanAnswer().nextLine().replaceAll("\\s", "").toLowerCase();
        switch (userInput) {
            case "commandant":
                return new Character("commandant", 300, 10);
            case "artilleur":
                return new Character("artilleur", 250, 7);
            case "archer":
                return new Character("archer", 250, 7);
            case " villageois" :
                return new Character("villageois", 200, 5) ;
            default:
                System.out.println("Le personnage n'existe pas.");
                return chooseCharacter(player);
        }
    }

    // une fonction qui dit si on a perdu ou pas
    public boolean GameLose(){
        return character.getLive() <= 0 ;
    }

    // une fonction qui fait l'apparition des monstres
    public void apparitionMonster(){
        this.monsterSpawner.startWaves();
        if (this.monsterSpawner.getInWave()) this.monsterSpawner.update(20000 , monsters);
    }

    // une fonction qui affiche comment le jeu est à cette instance
    public void afficheCourant (){
        String[][] tab = tableauWithMonster(tableauCellule());
        System.out.println(" Argent : " +character.getMoney());
        System.out.println(" Vie : " + character.getLive());
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
        for ( int i = 0 ; i < this.height ; i++){
            System.out.print((char) (65 + i) + " |");
            for ( int j = 0 ; j < this.width ; j++ ){
                System.out.print(tab[i][j]);
            }
            System.out.print("\n");
        }
    }

    // une fonction qui rajoute a tab ou les monstres sont positionné
    public String[][] tableauWithMonster (String[][] tab){
        apparitionMonster();
        if (this.monsters != null) {
            for (Monster m : this.monsters) {
                if ( m.entrerDansBase()) {
                    m.whenMonsterEnterBase();
                } else {
                    tab[m.getPos().inti()][m.getPos().intj()] = " m ";
                }
            }
        }
        return tab ;
    }

    // une fonction qui revoie un tableau String de la map
    public String[][] tableauCellule() {
        String[][] tab = new String[this.height][this.width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                switch (tableau.getGrid()[i][j].getType()) {
                    case 0: tab[i][j] = " # "; break; // l'herbe
                    case 1: tab[i][j] = " O "; break; // la route
                    case 2: tab[i][j] = " ~ "; break; // l'eau
                    case 3: tab[i][j] = " A "; break; // l'arbre
                    case 4: tab[i][j] = " B "; break; // la base
                    case 5: tab[i][j] = " } "; break; // une tour archer
                    case 6: tab[i][j] = " J "; break; // une tour arme
                    case 7: tab[i][j] = " ꓕ "; break; // une tour artillerie
                    case 8: tab[i][j] = " ⅄ "; break; // une tour canon
                }
            }
        }
        return tab;
    }


    // getteurs et setteurs
    public MapConfig getTableau() {
        return tableau;
    }
    public Character getCharacter() {
        return character;
    }
}
