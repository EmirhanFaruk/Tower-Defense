package model.tour;

import model.Character;
import model.monster.Monster;

import java.util.ArrayList;

public class Catapulte extends Tour {
    public Catapulte(int level, int x, int y) {
        super("catapulte", 80, 90 , level , x , y , 5 , 7 , 10000 );
    }

    public Catapulte(int level ) {
        super("catapulte", 80, 90 , level , 600 , 7 , 10000 );
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
                // Ball and explosion damage
                cible.monsterHurt((double) degats / 2 * augmenteDegats, "BULLET", difficulty);
                cible.monsterHurt((double) degats / 2 * augmenteDegats, "EXPLOSION", difficulty);
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
