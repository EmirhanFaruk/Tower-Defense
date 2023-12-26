package model.monster;

import config.Cellule;
import config.MapConfig;

import java.util.ArrayList;
import java.util.List;

public class MonsterPathFinding
{
    private static Cellule[][] grid;
    public static List<ArrayList<Integer>> monster_path;


    /**
     * Returne monster_path, produit une s'il est null.
     * @param map_config map_config
     * @return monster_path
     */
    public static List<ArrayList<Integer>> makeMonster_path(MapConfig map_config)
    {
        if(monster_path == null)
        {
            grid = map_config.getGrid();

            int[] debut = findStart();

            monster_path = getListeChemin(debut[0], debut[1]);
        }
        return monster_path;
    }


    public static void setMonsterDirection(Monster mons)
    {
        if(!mons.getPath().isEmpty())
        {
            ArrayList<Integer> couple = mons.getPath().get(0);
            double[] pos = mons.getPos();
            // Si au meme position que path target, enleve le et reessaie
            if(pos[0] == couple.get(0) && pos[1] == couple.get(1))
            {
                mons.popPath();
                setMonsterDirection(mons);
            }
            else if(pos[0] == couple.get(0))
            {
                // Gerer j, EAST ou WEST
                if(pos[1] < couple.get(1))
                {
                    mons.setDirection("EAST");
                }
                else
                {
                    mons.setDirection("WEST");
                }
            }
            else
            {
                // Gerer i, NORTH ou SOUTH
                if(pos[0] < couple.get(0))
                {
                    mons.setDirection("NORTH");
                }
                else
                {
                    mons.setDirection("SOUTH");
                }
            }
        }
        else
        {
            mons.setDirection("NONE");
        }
    }

    public static void moveMonster(Monster mons)
    {
        setMonsterDirection(mons);
        if(!mons.getDirection().equals("NONE"))
        {
            
        }
    }



    /**
     * Trouver le point de debut des monstres. C'est le type de "road" au plus gauche colonne.
     * @return les coordonnées de debut
     */
    private static int[] findStart()
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
    private static List<ArrayList<Integer>> getVoisin(int i, int j)
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


    /**
     * Determine si le couple (i, j) existe dans arr.
     * @param arr le array
     * @param i coordonnée i
     * @param j coordonnée j
     * @return existe ou pas
     */
    private static boolean inArray(List<ArrayList<Integer>> arr, int i, int j)
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
     * Trouver un chemin depuis le debut vers la base pour les monstres.
     * @param i coordonnée i
     * @param j coordonnée j
     * @return une liste avec les coordonnées des cellules de chemin depuis les coordonnées de début
     */
    private static List<ArrayList<Integer>> getListeChemin(int i, int j)
    {
        List<ArrayList<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<Integer>(List.of(i, j)));
        int ic = 0, jc = 0;
        while(true)
        {
            List<ArrayList<Integer>> temp = new ArrayList<>();
            for(ArrayList<Integer> couple: getVoisin(i, j))
            {
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