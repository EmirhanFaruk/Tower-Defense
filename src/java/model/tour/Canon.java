package model.tour;

import model.Character;
import model.monster.Monster;

import java.util.ArrayList;

public class Canon extends Tour {
    public Canon( int level, int x, int y ) {
        super("canon" , 40 , 75 , level , x , y , 6 ,8 , 5000);
    }

    public Canon( int level ) {
        super("canon" , 40 , 75 , level ,400 , 8 , 5000);
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

    /* getters et setters */
    public int getType() {
        return super.getType();
    }
}
