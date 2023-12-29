package model.tour;

import model.Character;

public class Archer extends Tour {
    public Archer(Character character ,int prix, int degats, int level, int x, int y, int range, int time) {
        super( character ,"Archer", prix, 10 , level, x, y, range, 1000 );
    }
}
