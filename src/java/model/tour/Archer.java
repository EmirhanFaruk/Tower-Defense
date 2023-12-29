package model.tour;

import model.Character;

public class Archer extends Tour {
    public Archer( int level, int x, int y ) {
        super( "archer", 15 , 10 , level, x, y, 300 , 1000 );
    }
}
