package gui.game.paint;
import model.monster.Monster;

import javax.swing.*;
import java.awt.*;

public class MonsterGraphics {
    private static final String path = System.getProperty("user.dir");
    private static final String s = findSlash(path);
    private static final String[] monsterBlue = { "MonsterBlue1.gif" , "MonsterBlue2.gif" , "MonsterBlue3.gif" } ;
    private static final String[] monsterRed = {"MonsterRed1.gif" , "MonsterRed2.gif" , "MonsterRed3.gif"} ;
    private static final String[] monsterGreen = {"MonsterGreen1.gif" , "MonsterGreen2.gif" , "MonsterGreen3.gif"} ;
    private static final String [] monsterGray = {"MonsterGray1.gif" , "MonsterGray2.gif" , "MonsterGray3.gif"} ;
    private static final ImageIcon[] monsterBlueImage = {loadImage("MonsterBlue1.gif"), loadImage("MonsterBlue2.gif"), loadImage("MonsterBlue3.gif")};
    private static final ImageIcon[] monsterRedImage = {loadImage("MonsterRed1.gif"), loadImage("MonsterRed2.gif"), loadImage("MonsterRed3.gif")};
    private static final ImageIcon[] monsterGreenImage = {loadImage("MonsterGreen1.gif"), loadImage("MonsterGreen2.gif"), loadImage("MonsterGreen3.gif")};
    private static final ImageIcon[] monsterGrayImage = {loadImage("MonsterGray1.gif"), loadImage("MonsterGray2.gif"), loadImage("MonsterGray3.gif")};
    private static int width , height ;


    public MonsterGraphics( ) {
    }
    public static void setWH(int w, int h)
    {
        width = w;
        height = h;
    }

    public static ImageIcon loadImage(String imagePath) {
        return new ImageIcon(imagePath);
    }

    private static String findSlash(String p) {
        for (int i = 0; i < p.length(); i++) {
            switch (p.charAt(i)) {
                case '/':
                    return "/";
                case '\\':
                    return "\\";
            }
        }
        return "/";
    }

    public static ImageIcon getImage(Monster monster){
        int pos = monster.getNiveau() ;
        switch (monster.getResistance()){
            case "NONE" : return monsterGrayImage[pos] ;
            case "BULLET" : return monsterBlueImage[pos] ;
            case "ARROW" : return monsterGreenImage[pos] ;
            case "FIRE" : return monsterRedImage[pos] ;
        }
        return null ;
    }

    public static void paint(Graphics2D g, Monster monster)
    {
        Image image = getImage(monster).getImage();
        System.err.println(height);
        System.err.println(width);
        int x = monster.getPos().intj() * width;
        int y = monster.getPos().inti() * height;
        g.drawImage(image, x, y, width, height, null);
    }

}
