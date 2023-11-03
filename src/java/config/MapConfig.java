package config;

import javax.swing.event.CellEditorListener;
import java.io.File;
import java.util.Scanner;

public class MapConfig {
    private final Cellule[][] grid ;

    public MapConfig (Cellule[][] grid){
        this.grid = grid ;
    }
    public static int compteligne() throws Exception{
        String path = System.getProperty("user.dir") ;
        File file;
        try {
            file =new File(path+"/src/main/resources/Maze.txt");
        } catch (Exception e ){
            e.printStackTrace();
            file =new File(path+"\\src\\main\\resources\\Maze.txt");
        }
        int nbligne =0 ;
        Scanner scanner = new Scanner( file ) ;
        while (scanner.nextLine()!=null){
            nbligne++;
        }
        scanner.close();
        return nbligne;
    }

    public static int comptelongeur() throws Exception {
        String path = System.getProperty("user.dir") ;
        File file;
        try {
            file =new File(path+"/src/main/resources/Maze.txt");
        } catch (Exception e ){
            e.printStackTrace();
            file =new File(path+"\\src\\main\\resources\\Maze.txt");
        }
        int longueur =0 ;
        Scanner scanner = new Scanner( file ) ;
        String str  = scanner.nextLine();
        longueur = str.length() ;
        scanner.close();
        return longueur;
    }

    public static Cellule[][] grid () throws Exception {
        String path = System.getProperty("user.dir") ;
        File file;
        try {
            file =new File(path+"/src/main/resources/Maze.txt");
        } catch (Exception e ){
            e.printStackTrace();
            file =new File(path+"\\src\\main\\resources\\Maze.txt");
        }
        Scanner scanner = new Scanner( file ) ;
        Cellule[][] maze = new Cellule[compteligne()][comptelongeur()] ;
        int j = 0 ;
        String str  = scanner.nextLine();
        while (str != null){
            for ( int i = 0 ; i< str.length();i++){
                if (str.charAt(i)=='0') maze[j][i]=  new Cellule(false,0) ;
                if (str.charAt(i)=='1') maze[j][i]=  new Cellule(true,1) ;
                if (str.charAt(i)=='2') maze[j][i]=  new Cellule(false,2) ;
                if (str.charAt(i)=='3') maze[j][i]=  new Cellule(false,3) ;
            }
            j++ ;
        }
        return maze;
    }

}
