package model.tour;

import model.monster.Monster;

import java.util.ArrayList;

public class Archer extends Tour {
    public Archer( int level, int x, int y ) {
        super( "archer", 15 , 15 , level, x, y, 300 , 1000 );
    }

    public Archer ( int level ){
        super ( "archer", 15 , 15 , level,300 , 1000 );
    }
    @Override
    public void attaquer(ArrayList<Monster> monsters) {
        super.attaquer(monsters);
    }
}
