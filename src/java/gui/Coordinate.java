package gui;

import java.nio.charset.CoderResult;

public class Coordinate {
    private double i ;
    private double j ;
    public Coordinate (double i , double j){
        this.i = i ;
        this.j = j ;
    }

    public void mult(double a, double b) {i *= a; j *= b;}
    public void add(double a, double b) {i += a; j += b;}
    public void set(double a, double b) {i = a; j = b;}
    public void set(Coordinate c)
    {
        Coordinate cord = c.copy();
        i = cord.i(); j = cord.j();
    }
    public double i() {return i;}
    public double j() {return j;}
    public int inti() {return (int)i;}
    public int intj() {return (int)j;}
    public double[] ij() {return new double[]{i, j};}
    public Coordinate copy() {return new Coordinate(i, j);}
    public Coordinate intCopy() {return new Coordinate(inti(), intj());}
}
