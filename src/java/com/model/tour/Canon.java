package com.model.tour;

import com.model.Character;
import com.model.monster.Monster;

import java.util.ArrayList;

public class Canon extends Tour {
    public Canon( int level, int x, int y ) {
        super("canon" , 40 , 250 , level , x , y , 6 ,8 , 7000);
    }

    public Canon( int level ) {
        super("canon" , 40 , 250 , level ,400 , 8 , 3000);
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
                double augmenteDegats= 1 + (double) character.getDegats() / 100 ;
                cible.monsterHurt(degats * augmenteDegats, "BULLET", difficulty);
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
