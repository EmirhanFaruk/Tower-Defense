package model.tour;

import model.monster.Monster;

import java.util.ArrayList;

public class Soldat extends Tour {
    public Soldat(int level, int x, int y ) {
        super( "arme" , 10 , 10 , level, x , y , 2, 6 , 100 );
    }

    public Soldat(int level ) {
        super( "arme" , 10 , 10 , level ,100 , 6 , 100 );
    }

    @Override
    public void attaquer(ArrayList<Monster> monsters) {
        super.attaquer(monsters);
    }

    public int getType() {
        return super.getType();
    }
}
