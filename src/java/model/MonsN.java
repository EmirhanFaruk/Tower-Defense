package model;

public class MonsN extends Monster
{
    private static double[][] mulp =
                    {
                            {1, 1},
                            {1.5, 1.1},
                            {1.8, 1.3},
                            {2, 1.5}
                    }; // live, speed. Exemples a chane
    public MonsN(String name , double live , double speed , int money , double x, double y, int niveau)
    {
        super(name, live * mulp[niveau][0], speed * mulp[niveau][1], money, x, y);
    }
}
