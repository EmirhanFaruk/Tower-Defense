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

    public static int compteLigne(String s) throws FileNotFoundException {
        String path = System.getProperty("user.dir") ;
        File file;
        try {
            file =new File(path+"/src/resources/"+s);
        } catch (Exception e ){
            file =new File(path+"\\src\\resources\\"+s);
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
            file =new File(path+"/src/resources/"+s);
        } catch (Exception e ){
            file =new File(path+"\\src\\resources\\"+s);
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
            file = new File(path + "/src/resources/" + s);
        } catch (Exception e) {
            file = new File(path + "\\src\\resources\\" + s);
        }

        int lignes = compteLigne(s);
        int longueur = compteLongeur(s);

        Cellule[][] maze = new Cellule[lignes][longueur];
        System.out.println("Lignes : " + lignes);
        System.out.println("Longueur : " + longueur);

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
