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
        System.out.print("}");
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

    /**
     * Trouver une chemin depuis le debut vers la base pour les monstres.
     * @param i coordonnée i
     * @param j coordonnée j
     * @return une liste avec les coordonnées des cellules de chemin depuis les coordonnées de début
     */
    private List<ArrayList<Integer>> getListeChemin(int i, int j)
    {
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
}