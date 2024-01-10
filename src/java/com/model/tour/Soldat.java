package com.model.tour;

import com.model.Character;
import com.model.monster.Monster;

import java.util.ArrayList;

public class Soldat extends Tour {
    public Soldat(int level, int x, int y ) {
        super( "arme" , 10 , 10 , level, x , y , 2, 6 , 200 );
    }

    public Soldat(int level ) {
        super( "arme" , 10 , 10 , level ,100 , 6 , 200 );
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
            if(cible != null)
            {
                double augmenteDegats = 1 + (double) character.getDegats() /10;
                if ( character.getName().equals("soldat")) {
                     augmenteDegats = 1 + 0.2; }
                cible.monsterHurt(degats * augmenteDegats, "BULLET", difficulty);
                if(cible.isDead())
                {
                    cible.winMoneyWhenMonsterDead();
                }
                lastAttackTime = currentTime;  // Mets à jour le temps de la dernière attaque
            }

        }
    }

    /* getters et setters */
    public int getType() {
        return super.getType();
    }
}
