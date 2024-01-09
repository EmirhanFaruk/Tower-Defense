package com.model.tour;

import com.model.Character;
import com.model.monster.Monster;

import java.util.ArrayList;

public class Soldat extends Tour {
    public Soldat(int level, int x, int y ) {
        super( "arme" , 10 , 10 , level, x , y , 2, 6 , 100 );
    }

    public Soldat(int level ) {
        super( "arme" , 10 , 10 , level ,100 , 6 , 100 );
    }


    @Override
    public void attaquer(ArrayList<Monster> monsters, String difficulty, Character character)
    {
        long currentTime = System.currentTimeMillis();
        // Vérifier si le cooldown est écoulé
        if (currentTime - lastAttackTime >= cooldown)
        {
            target(monsters); //attaque
            if(cible != null)
            {
                double augmenteDegats= 1 + (double) character.getDegats() / 20 ;
                cible.monsterHurt(degats * augmenteDegats, "BULLET", difficulty);
                if(cible.isDead())
                {
                    cible.winMoneyWhenMonsterDead();
                }
                lastAttackTime = currentTime;  // Mettre à jour le temps de la dernière attaque
            }

        }
    }

    public int getType() {
        return super.getType();
    }
}
