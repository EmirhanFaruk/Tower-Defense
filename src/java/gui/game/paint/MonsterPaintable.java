package gui.game.paint;

import gui.Coordinate;
import model.monster.Monster;

import java.awt.*;

public class MonsterPaintable {
    /*
     * 2 listes syncronises. 1 pour monsters 1 pour MonsterPaintable.
     */
    Monster monster;

    public MonsterPaintable(int width, int height, double scale_width, double scale_height, Monster monster) {
        //super(monster.getPos(), width, height, scale_width, scale_height);
        this.monster = monster;
    }

    public Monster getMonster() {
        return monster;
    }
}
