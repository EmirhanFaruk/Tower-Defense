package gui.TerminalAffichage;

import config.Cellule;
import model.Character;
import model.Player;

import java.util.ArrayList;

public class Jeu {

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

    public void startMonsterWave (){
        this.plateau.monsterSpawner.startWaves();
        this.plateau.monsterSpawner.update(20 , new ArrayList<>());
    }

    // une fonction qui nous fait jouer
    public void play () throws Exception {
        if (player.wantPlay()) { // si le player veut jouer
            this.plateau = new Plateau( player ) ; // demande la map que le player veut
            this.character = Character.chooseCharacter(player) ; // initialisation d'un character
            this.plateau.character = this.character ; // l'attribut character du plateau est initiaser
            startMonsterWave();
            while (!plateau.GameLose()) { // si le player n'a pas perdu
                plateau.afficheCourant(); // affiche la map a cette instance
                Thread.sleep(2000); // fait dormir le terminal 2 sec
                if (player.requestAction()) { // demande si le player veut-il poser une tour
                    int[] val =player.enterCoordinates(); // demande au player de donner une coordonnée
                    this.plateau.tableau.getGrid()[val[0]][val[1]]=new Cellule(false , 5) ; //place la tour à la position que le player a demandée
                }
            }
            play(); // quand le player a perdu faire la recursion pour une nouvelle partie
        } else { // si le player ne veut pas jouer
            player.closeScanner(); // ferme le scanner
        }
    }
}
