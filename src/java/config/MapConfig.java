package config;

import javax.swing.event.CellEditorListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MapConfig {
    private final Cellule[][] grid ;

    public MapConfig (Cellule[][] grid){
        this.grid = grid ;
    }


    public static int compteLigne(String s) throws FileNotFoundException {
        String path = System.getProperty("user.dir") ;
        File file;
        try {
            file =new File(path+"/src/resources/maps/"+s);
        } catch (Exception e ){
            file =new File(path+"\\src\\resources\\maps\\"+s);
        }
        int longueur =0 ;
        Scanner scanner = new Scanner( file ) ;
        int nombreLignes = 0;
        // Utilise le scanner pour compter les lignes
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            nombreLignes++;
        }
        scanner.close();
        return nombreLignes;
    }
    public static int compteLongeur(String s) throws Exception {
        String path = System.getProperty("user.dir") ;
        File file;
        try {
            file =new File(path+"/src/resources/maps/"+s);
        } catch (Exception e ){
            file =new File(path+"\\src\\resources\\maps\\"+s);
        }
        int longueur =0 ;
        Scanner scanner = new Scanner( file ) ;
        longueur =  scanner.nextLine().length() ;
        scanner.close();
        return longueur;
    }
    public static Cellule[][] grid(String s) throws Exception {
        String path = System.getProperty("user.dir");
        File file;
        try {
            file = new File(path + "/src/resources/maps/" + s);
        } catch (Exception e) {
            file = new File(path + "\\src\\resources\\maps\\" + s);
        }

        int lignes = compteLigne(s);
        int longueur = compteLongeur(s);

        Cellule[][] maze = new Cellule[lignes][longueur];

        int j = 0;
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') maze[j][i] = new Cellule(false, 0);
                if (str.charAt(i) == '1') maze[j][i] = new Cellule(true, 1);
                if (str.charAt(i) == '2') maze[j][i] = new Cellule(false, 2);
                if (str.charAt(i) == '3') maze[j][i] = new Cellule(false, 3);
                if (str.charAt(i) == '4') maze[j][i] = new Cellule(true, 4);
            }
            j++;
        }

        scanner.close();
        return maze;
    }

    public static MapConfig make(String s) throws Exception {
        return new MapConfig(grid( s)) ;
    }

    public Cellule[][] getGrid() {
        return grid;
    }
}
