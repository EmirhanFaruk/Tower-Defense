package model.tour;

import model.monster.Monster;

import java.util.ArrayList;

public class Canon extends Tour {
    public Canon( int level, int x, int y ) {
        super("canon" , 40 , 75 , level , x , y , 400 ,8 , 5000);
    }

    public Canon( int level ) {
        super("canon" , 40 , 75 , level ,400 , 8 , 5000);
    }
    @Override
    public void attaquer(ArrayList<Monster> monsters) {
        super.attaquer(monsters);
    }

    public int getType() {
        return super.getType();
    }
}
