package model.monster;

import config.Cellule;
import config.MapConfig;

import java.util.ArrayList;
import java.util.List;

public class MonsterPathFinding
{
    private Cellule[][] grid;
    public static List<ArrayList<Integer>> monster_path;

    public MonsterPathFinding(MapConfig map_config)
    {
        grid = map_config.getGrid();

        int[] debut = findStart();

        monster_path = getListeChemin(debut[0], debut[1]);
    }

    public void printChemin()
    {
        System.out.println("{");
        for (ArrayList<Integer> couple : monster_path)
        {
            System.out.println("{" + couple.get(0) + ", " + couple.get(1) + "}");
        }
        System.out.println("}");
    }

    public static void printCellArray(Cellule[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print("(" + i + ", " + j + "): " + array[i][j] + " | ");
            }
            System.out.println();
        }
    }

    public static void printIntCoupleArray(List<ArrayList<Integer>> array)
    {
        System.out.println();
        System.out.print("{");
        for (int i = 0; i < array.size(); i++) {
            System.out.print("(" + array.get(i).get(0) + ", " + array.get(i).get(1) + ") | ");
        }
        System.out.println("}");
    }

    public static void printIntArray(ArrayList<Integer> array) {
        System.out.println("(" + array.get(0) + ", " + array.get(1) + ")");
    }


    /**
     * Trouver le point de debut des monstres. C'est le type de "road" au plus gauche colonne.
     * @return les coordonnées de debut
     */
    private int[] findStart()
    {
        for(int i = 0; i < grid.length; i++)
        {
            if(grid[i][0].isRoad())
            {
                return new int[]{i, 0};
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * Trouver les voisins d'une cellule en coordonnées.
     * @param i coordonnée i
     * @param j coordonnée j
     * @return une liste avec les cellules atour depuis les cordoonnées
     */
    private List<ArrayList<Integer>> getVoisin(int i, int j)
    {
        List<ArrayList<Integer>> res = new ArrayList<>();
        if(i > 0) // HAUT
        {
            if(grid[i - 1][j].isRoad())
            {
                res.add(new ArrayList<>(List.of(i - 1, j)));
            }
        }
        if(j < grid[0].length - 1) // DROITE
        {

            if(grid[i][j + 1].isRoad())
            {
                res.add(new ArrayList<>(List.of(i, j + 1)));
            }
        }
        if(i < grid.length - 1) // BAS
        {
            if(grid[i + 1][j].isRoad())
            {
                res.add(new ArrayList<>(List.of(i + 1, j)));
            }
        }
        if(j > 0) // GAUCHE
        {
            if(grid[i][j - 1].isRoad())
            {
                res.add(new ArrayList<>(List.of(i, j - 1)));
            }
        }

        return res;
    }


    private boolean inArray(List<ArrayList<Integer>> arr, int i, int j)
    {
        for (ArrayList<Integer> couple : arr)
        {
            if(couple.get(0) == i && couple.get(1) == j)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Trouver une chemin depuis le debut vers la base pour les monstres.
     * @param i coordonnée i
     * @param j coordonnée j
     * @return une liste avec les coordonnées des cellules de chemin depuis les coordonnées de début
     */
    private List<ArrayList<Integer>> getListeChemin(int i, int j)
    {
        List<ArrayList<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<Integer>(List.of(i, j)));
        int ic = 0, jc = 0;
        while(true)
        {
            List<ArrayList<Integer>> temp = new ArrayList<>();
            for(ArrayList<Integer> couple: getVoisin(i, j))
            {
                System.out.println("===============================================");
                printIntCoupleArray(getVoisin(i, j));
                System.out.println("Current: (" + i + ", " + j + ")");
                printIntArray(couple);
                System.out.print("Path: ");
                printIntCoupleArray(res);
                ic = couple.get(0);
                jc = couple.get(1);
                if(!(inArray(res, ic, jc)))
                {
                    temp.add(new ArrayList<Integer>(List.of(ic, jc)));
                    res.add(new ArrayList<Integer>(List.of(ic, jc)));
                }
            }
            if(res.size() > 1)
            {
                i = res.get(res.size()-1).get(0);
                j = res.get(res.size()-1).get(1);
            }

            if(temp.isEmpty())
            {
                return res;
            }
        }
    }
}