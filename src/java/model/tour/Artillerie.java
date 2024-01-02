package model.tour;

import model.monster.Monster;

import java.util.ArrayList;

public class Artillerie extends Tour {
    public Artillerie(int level, int x, int y) {
        super("artillerie", 80, 90 , level , x , y , 5 , 7 , 10000 );
    }

    public Artillerie(int level ) {
        super("artillerie", 80, 90 , level , 600 , 7 , 10000 );
    }
    @Override
    public void attaquer(ArrayList<Monster> monsters) {
        super.attaquer(monsters);
    }

    public int getType() {
        return super.getType();
    }
}
