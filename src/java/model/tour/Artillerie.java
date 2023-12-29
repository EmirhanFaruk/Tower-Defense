package model.tour;

import model.Character;

public class Artillerie extends Tour {
    public Artillerie(Character character ,int prix, int degats, int level, int x, int y, int range, int time) {
        super(character , "Artillerie", 80, 90 , level , x , y , 600 , 10000 );
    }
}
