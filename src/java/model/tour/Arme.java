package model.tour;

import model.Character;

public class Arme extends Tour {
    public Arme(Character character ,int prix, int degats, int level, int x, int y, int range, int time) {
        super( character ,"Arme" , 20 , 10 , level, x , y , 100 , 100 );
    }
}
