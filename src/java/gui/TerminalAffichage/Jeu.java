package gui.TerminalAffichage;

import config.Cellule;
import model.Character;
import model.Player;
import model.monster.Monster;
import model.tour.*;

import java.util.ArrayList;

public class Jeu {

    Player player;
    Plateau plateau ;
    Character character ;
    private final ArrayList<Tour> tours ;

    private ArrayList<Tour> toursInGame ;

    public Jeu (Player player , Plateau plateau , Character character ){
        this.player = player;
        this.plateau = plateau ;
        this.character = character ;
        this.tours = towerList() ;
        this.toursInGame = new ArrayList<>() ;
    }

    public Jeu (Player player){
        this ( player ,  null , null ) ;
    }

    // Une fonction qui initialise la liste possible pour poser une tour
    private ArrayList<Tour> towerList (){
        ArrayList<Tour> towerList = new ArrayList<>() ;
        towerList.add(new Archer(1 )) ;
        towerList.add(new Archer(2 )) ;
        towerList.add(new Archer(3 )) ;
        towerList.add(new Soldat(1 )) ;
        towerList.add(new Soldat(2 )) ;
        towerList.add(new Soldat(3 )) ;
        towerList.add(new Catapulte(1 )) ;
        towerList.add(new Catapulte(2 )) ;
        towerList.add(new Catapulte(3 )) ;
        towerList.add(new Canon(1 )) ;
        towerList.add(new Canon(2 )) ;
        towerList.add(new Canon(3 )) ;
        return towerList ;
    }

    // UNe fonction qui cherche si la tour que le player à demande existe
    public Tour findTower ( String str , int i ){
        for ( Tour t : tours){
            if (t.getName().equals(str) && i == t.getLevel()) {
                return t ;
            }
        }
        return null ;
    }

    // Une fonction qui ajoute la tour que le player vient de demander
    public void addTower( String name , int level , int x , int y ) {
        switch (name){
            case "archer"  : toursInGame.add(new Archer( level , x , y )) ; break ;
            case "catapulte" : toursInGame.add(new Catapulte( level , x, y )) ; break ;
            case "canon" : toursInGame.add((new Canon( level , x , y ))) ; break ;
            case "soldat" : toursInGame.add(new Soldat( level , x , y )) ; break ;
        }
    }

    // Une fonction
    public void attaque (ArrayList<Monster> monsters){
        if ( toursInGame != null) {
            for (Tour t : toursInGame) {
                t.attaquer(monsters);
            }
        }
    }


    // une fonction qui nous fait jouer
    public void play () throws Exception {
        if (player.wantPlay()) { // si le player veut jouer
            this.plateau = new Plateau( player ) ; // demande la map que le player veut
            this.character = this.plateau.getCharacter() ;
            while (!plateau.GameLose()) { // si le player n'a pas perdu
                plateau.afficheCourant(); // affiche la map a cette instance
                attaque(plateau.getMonsters()); // attaque les monstres avec la/les tour/tours
                //Thread.sleep(5000); // fait dormir le terminal 5 sec
                if ( player.requestAction()) { // demande si le player veut-il poser une tour
                    String towerType = player.chooseTowerType(); // demande quelle tour le player veut-il achetez
                    int towerLevel = player.chooseTowerLevel();
                    Tour selectTower =  findTower(towerType , towerLevel) ;
                    if (selectTower != null && character.getMoney() >= selectTower.getPrix()) {
                        character.setMoney(character.getMoney() - selectTower.getPrix()); // Réduire l'argent du character au prix de la tour
                        int[] val =player.enterCoordinates(plateau.getTableau()); // demande au player de donner une coordonnée
                        this.plateau.getTableau().getGrid()[val[0]][val[1]]=new Cellule(false , selectTower.getType()) ; //place la tour à la position que le player a demandée
                        addTower(towerType,towerLevel,val[0],val[1]);
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
