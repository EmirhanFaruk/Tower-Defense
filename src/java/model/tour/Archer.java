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
                cible.monsterHurt(degats * augmenteDegats, "ARROW", difficulty);
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
