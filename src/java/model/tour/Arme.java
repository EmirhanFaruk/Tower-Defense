package model.tour;

import model.monster.Monster;

import java.util.ArrayList;

public class Arme extends Tour {
    public Arme( int level, int x, int y ) {
        super( "arme" , 10 , 10 , level, x , y , 100 , 100 );
    }

    public Arme( int level ) {
        super( "arme" , 10 , 10 , level ,100 , 100 );
    }

    @Override
    public void attaquer(ArrayList<Monster> monsters) {
        super.attaquer(monsters);
    }
}
