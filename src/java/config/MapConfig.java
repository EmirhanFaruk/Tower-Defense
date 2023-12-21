package config;

import javax.swing.event.CellEditorListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MapConfig {
    private final Cellule[][] grid ;

    public MapConfig (Cellule[][] grid){
        this.grid = grid ;
    }


    public List<ArrayList<Integer>> getVoisin(int i, int j)
    {
        /*
        Retourne une liste avec les cellules atour depuis les cordoonnées
         */
        List<ArrayList<Integer>> res = new ArrayList<>();
        if(i > 0) // HAUT
        {
            res.add(new ArrayList<>(List.of(i - 1, j)));
        }
        if(j < grid.length - 1) // DROITE
        {
            res.add(new ArrayList<>(List.of(i, j + 1)));
        }
        if(i < grid.length - 1) // BAS
        {
            res.add(new ArrayList<>(List.of(i + 1, j)));
        }
        if(j > 0) // GAUCHE
        {
            res.add(new ArrayList<>(List.of(i, j - 1)));
        }

        return res;
    }

    public List<ArrayList<Integer>> getListeChemin(int i, int j)
    {
        /*
        Returne une liste avec les coordonnées des cellules de chemin depuis les coordonnées de début
         */
        List<ArrayList<Integer>> res = new ArrayList<>();
        while(true)
        {
            List<ArrayList<Integer>> temp = new ArrayList<>();
            for(ArrayList<Integer> couple: getVoisin(i, j))
            {
                int ic = couple.get(0);
                int jc = couple.get(1);
                if(grid[ic][jc].isRoad())
                {
                    temp.add(new ArrayList<Integer>(List.of(ic, jc)));
                    res.add(new ArrayList<Integer>(List.of(ic, jc)));
                }
            }

            if(temp.isEmpty())
            {
                return res;
            }
        }
    }

    public static int compteligne(String s) throws Exception{
        String path = System.getProperty("user.dir") ;
        File file;
        try {
            file =new File(path+"/src/resources/"+s);
        } catch (Exception e ){
            e.printStackTrace();
            file =new File(path+"\\src\\resources\\"+s);
        }
        int nbligne =0 ;
        Scanner scanner = new Scanner( file ) ;
        while (scanner.nextLine()!=null){
            nbligne++;
        }
        scanner.close();
        return nbligne;
    }

    public static int comptelongeur(String s) throws Exception {
        String path = System.getProperty("user.dir") ;
        File file;
        try {
            file =new File(path+"/src/resources/"+s);
        } catch (Exception e ){
            e.printStackTrace();
            file =new File(path+"\\src\\resources\\"+s);
        }
        int longueur =0 ;
        Scanner scanner = new Scanner( file ) ;
        String str  = scanner.nextLine();
        longueur = str.length() ;
        scanner.close();
        return longueur;
    }

    public static Cellule[][] grid(String s) throws Exception {
        String path = System.getProperty("user.dir") ;
        File file;
        try {
            file =new File(path+"/src/resources/"+s);
        } catch (Exception e ){
            e.printStackTrace();
            file =new File(path+"\\src\\resources\\"+s);
        }
        Scanner scanner = new Scanner( file ) ;
        Cellule[][] maze = new Cellule[compteligne(s)][comptelongeur(s)] ;
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

    public Cellule[][] getGrid() {
        return grid;
    }
}
