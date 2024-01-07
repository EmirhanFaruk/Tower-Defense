package gui.game.paint;
import model.monster.Monster;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MonsterGraphics {
    private static final String path = System.getProperty("user.dir");
    private static final String s = findSlash(path);
    private static final BufferedImage[] monsterBlueImage = {loadImage("MonsterBlue1.png"), loadImage("MonsterBlue2.png"), loadImage("MonsterBlue3.png")};
    private static final BufferedImage[] monsterRedImage = {loadImage("MonsterRed1.png"), loadImage("MonsterRed2.png"), loadImage("MonsterRed3.png")};
    private static final BufferedImage[] monsterGreenImage = {loadImage("MonsterGreen1.png"), loadImage("MonsterGreen2.png"), loadImage("MonsterGreen3.png")};
    private static final BufferedImage[] monsterGrayImage = {loadImage("MonsterGray1.png"), loadImage("MonsterGray2.png"), loadImage("MonsterGray3.png")};
    private static int width , height ;
    public MonsterGraphics( ) {
    }
    public static void setWH(int w, int h)
    {
        width = w;
        height = h;
    }

    private static BufferedImage loadImage(String fileName) {
        try {
            String imagePath = path + s + "src" + s + "resources" + s + "images" + s + "Monster" + s + fileName;
            return ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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

    public static BufferedImage getImage(Monster monster) {
        int pos = monster.getNiveau();
        switch (monster.getResistance()) {
            case "NONE":
                return monsterGrayImage[pos];
            case "BULLET":
                return monsterBlueImage[pos];
            case "ARROW":
                return monsterGreenImage[pos];
            case "FIRE":
                return monsterRedImage[pos];
            default:
                return null;
        }
    }

    public static void paint(Graphics2D g, Monster monster)
    {
        BufferedImage image = getImage(monster);
        int x = (int)( monster.getPos().j() * width );
        int y = ( int ) (monster.getPos().i() * height );
        g.drawImage(image, x, y, width, height, null);

        Rectangle health_bar_cover = new Rectangle(x, y , width, height/5);
        g.setColor(Color.RED);
        g.fill(health_bar_cover);
        g.draw(health_bar_cover);
        
        double live_perc = monster.getLive() / monster.getMax_live();
        Rectangle health_bar = new Rectangle(x, y , (int) (width * live_perc), height/5);
        g.setColor(Color.GREEN);
        g.fill(health_bar);
        g.draw(health_bar);

    }
}
