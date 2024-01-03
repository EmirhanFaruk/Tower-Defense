package model.tour;

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
    public void attaquer(ArrayList<Monster> monsters) {
        super.attaquer(monsters);
    }

    public int getType() {
        return super.getType();
    }
}
