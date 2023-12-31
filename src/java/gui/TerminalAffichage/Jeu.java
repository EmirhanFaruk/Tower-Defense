package gui.TerminalAffichage;

import config.Cellule;
import gui.Coordinate;
import model.Character;
import model.Player;
import model.tour.*;

import java.util.ArrayList;

public class Jeu {

    Player player;
    Plateau plateau ;
    Character character ;
    private final ArrayList<Tour> tours ;

    public Jeu (Player player , Plateau plateau , Character character ){
        this.player = player;
        this.plateau = plateau ;
        this.character = character ;
        this.tours = towerList() ;
    }

    public Jeu (Player player){
        this ( player ,  null , null ) ;
    }

    private ArrayList<Tour> towerList (){
        ArrayList<Tour> towerList = new ArrayList<>() ;
        towerList.add(new Archer(1 )) ;
        towerList.add(new Archer(2 )) ;
        towerList.add(new Archer(3 )) ;
        towerList.add(new Arme(1 )) ;
        towerList.add(new Arme(2 )) ;
        towerList.add(new Arme(3 )) ;
        towerList.add(new Artillerie(1 )) ;
        towerList.add(new Artillerie(2 )) ;
        towerList.add(new Artillerie(3 )) ;
        towerList.add(new Canon(1 )) ;
        towerList.add(new Canon(2 )) ;
        towerList.add(new Canon(3 )) ;
        return towerList ;
    }


    public Tour findTower ( String str ){
        for ( Tour t : tours){
            if (t.getName().equals(str)) {
                return t ;
            }
        }
        return null ;
    }


    // une fonction qui nous fait jouer
    public void play () throws Exception {
        if (player.wantPlay()) { // si le player veut jouer
            this.plateau = new Plateau( player ) ; // demande la map que le player veut
            this.character = this.plateau.getCharacter() ;
            while (!plateau.GameLose()) { // si le player n'a pas perdu
                plateau.afficheCourant(); // affiche la map a cette instance
                //Thread.sleep(5000); // fait dormir le terminal 5 sec
                if ( player.requestAction()) { // demande si le player veut-il poser une tour
                    String towerType = player.chooseTowerType(); // demande quelle tour le player veut-il achetez
                    Tour selectTower =  findTower(towerType) ;
                    if (selectTower != null && character.getMoney() >= selectTower.getPrix()) {
                        character.setMoney(character.getMoney() - selectTower.getPrix()); // Réduire l'argent du character au prix de la tour
                        int[] val =player.enterCoordinates(); // demande au player de donner une coordonnée
                        this.plateau.getTableau().getGrid()[val[0]][val[1]]=new Cellule(false , selectTower.getType()) ; //place la tour à la position que le player a demandée
                    } else {
                        System.out.println("Vous n'avez pas assez d'argent ou le type de tour est invalide.");
                    }
                }
            }
            play(); // quand le player a perdu faire la recursion pour une nouvelle partie
        } else { // si le player ne veut pas jouer
            player.closeScanner(); // ferme le scanner
        }
    }
}
