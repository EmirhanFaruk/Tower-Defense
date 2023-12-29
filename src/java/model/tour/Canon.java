package model.tour;

import model.Character;

public class Canon extends Tour {
    public Canon(Character character ,int prix, int degats, int level, int x, int y, int range, int time) {
        super(character ,"Canon" , 40 , 75 , level , x , y , 400 , 5000);
    }
}
