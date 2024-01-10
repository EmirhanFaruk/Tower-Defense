package model.tour;

import model.Character;
import model.monster.Monster;

import java.util.ArrayList;

public class Archer extends Tour {
    public Archer( int level, int x, int y ) {
        super( "archer", 15 , 15 , level, x, y, 3 , 5 , 1000 );
    }

    public Archer ( int level ){
        super ( "archer", 15 , 15 , level, 300 , 5 , 1000 );
    }

    /**
     * Une fonction qui attaque un monstre
     * @param monsters
     * @param difficulty
     * @param character
     */
    @Override
    public void attaquer(ArrayList<Monster> monsters, String difficulty, Character character)
    {
        long currentTime = System.currentTimeMillis();
        long cdt = cooldown ;
        if ( character.getName().equals("commandant")){
            cdt /= 2 ;
        }
        // Vérifier si le cooldown est écoulé
        if (currentTime - lastAttackTime >= cdt)
        {
            target(monsters); //attaque
            double augmenteDegats = 1 + (double) character.getDegats() /10;
            if(cible != null) {
                if (character.getName().equals("archer")){
                    augmenteDegats = 1 + 0.3;
                }
                cible.monsterHurt(degats * augmenteDegats, "ARROW", difficulty);
                if(cible.isDead())
                {
                    cible.winMoneyWhenMonsterDead();
                }
                lastAttackTime = currentTime;  // Mettre à jour le temps de la dernière attaque
            }

        }
    }

    /* getters et setters */
    public int getType() {
        return super.getType();
    }
}
