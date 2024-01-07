package model.tour;

import model.monster.Monster;

import java.util.ArrayList;

public class Archer extends Tour {
    public Archer( int level, int x, int y ) {
        super( "archer", 15 , 15 , level, x, y, 3 , 5 , 1000 );
    }

    public Archer ( int level ){
        super ( "archer", 15 , 15 , level, 300 , 5 , 1000 );
    }
    @Override
    public void attaquer(ArrayList<Monster> monsters, String difficulty) {
        long currentTime = System.currentTimeMillis();
        // Vérifier si le cooldown est écoulé
        if (currentTime - lastAttackTime >= cooldown) {
            target(monsters); //attaque
            if(cible != null)
            {
                cible.monsterHurt(degats, "ARROW", difficulty);
            }
            lastAttackTime = currentTime;  // Mettre à jour le temps de la dernière attaque
        }
    }
    public int getType() {
        return super.getType();
    }
}
