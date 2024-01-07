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
                // Ball and explosion damage
                cible.monsterHurt((double) degats / 2, "BULLET", difficulty);
                cible.monsterHurt((double) degats / 2, "EXPLOSION", difficulty);
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
