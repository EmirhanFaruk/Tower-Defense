package gui.game.paint;

import gui.Coordinate;
import model.monster.Monster;

public class MonsterPaintable extends Paintable
{
    /*
     * 2 listes syncronises. 1 pour monsters 1 pour MonsterPaintable.
     */
    Monster monster;

    public MonsterPaintable(Monster monster)
    {
        super(monster.getPos());
        this.monster = monster;
    }
}
