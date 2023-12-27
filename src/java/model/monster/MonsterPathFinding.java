package model.monster;

import config.Cellule;
import config.MapConfig;
import gui.Coordinate;

import java.nio.charset.CoderResult;
import java.util.ArrayList;
import java.util.List;

public class MonsterPathFinding
{
    private static Cellule[][] grid;
    public static ArrayList<Coordinate> monster_path;


    /**
     * Returne monster_path, produit une s'il est null.
     * @param map_config map_config
     * @return monster_path
     */
    public static ArrayList<Coordinate> makeMonster_path(MapConfig map_config)
    {
        if(monster_path == null)
        {
            grid = map_config.getGrid();

            Coordinate debut = findStart();

            monster_path = getListeChemin(debut.intCopy());
        }
        return monster_path;
    }


    /**
     * Determiner la direction du monstre en utilisant son chemin
     * @param mons le manstre
     */
    private static void setMonsterDirection(Monster mons)
    {
        if(!mons.getPath().isEmpty())
        {
            Coordinate couple = mons.getPath().get(0);
            Coordinate pos = mons.getPos();
            // Si au meme position que path target, enleve le et reessaie
            if(pos.i() == couple.i() && pos.j() == couple.j())
            {
                mons.popPath();
                setMonsterDirection(mons);
            }
            else if(pos.i() == couple.i())
            {
                // Gerer j, EAST ou WEST
                if(pos.j() < couple.j())
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
                if(pos.i() < couple.i())
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

    /**
     * Vérifie si la vitesse de monstre est plus grand de son prochain place à aller
     * @param mons le monstre
     * @return si assez proche ou pas
     */
    private static boolean closeToTarget(Monster mons)
    {
        Coordinate pos = mons.getPos();
        Coordinate target = mons.getPath().get(0);
        return Math.abs(pos.i() - target.i()) < mons.getSpeed() && Math.abs(pos.j() - target.j()) < mons.getSpeed();
    }

    /**
     * Faire bouger le monstre en fonction de son direction
     * @param mons le monstre
     */
    public static void moveMonster(Monster mons)
    {
        setMonsterDirection(mons);
        if(!mons.getDirection().equals("NONE"))
        {
            if(closeToTarget(mons))
            {
                Coordinate target = mons.getPath().get(0).copy();
                mons.setPos(target.i(), target.j());
            }
            else
            {
                switch (mons.getDirection())
                {
                    case "NORTH": mons.addPos(mons.getSpeed(), 0);
                    case "SOUTH": mons.addPos(-mons.getSpeed(), 0);
                    case "EAST": mons.addPos(0, mons.getSpeed());
                    case "WEST": mons.addPos(0, -mons.getSpeed());
                }
            }
        }
    }



    /**
     * Trouver le point de debut des monstres. C'est le type de "road" au plus gauche colonne.
     * @return les coordonnées de debut
     */
    private static Coordinate findStart()
    {
        for(int i = 0; i < grid.length; i++)
        {
            if(grid[i][0].isRoad())
            {
                return new Coordinate(i, 0);
            }
        }
        return new Coordinate(-1, -1);
    }

    /**
     * Trouver les voisins d'une cellule en coordonnées.
     * @param cord coordonnées de la cellule
     * @return une liste avec les cellules atour depuis les cordoonnées
     */
    private static ArrayList<Coordinate> getVoisin(Coordinate cord)
    {
        ArrayList<Coordinate> res = new ArrayList<>();
        int i = cord.inti();
        int j = cord.intj();
        if(i > 0) // HAUT
        {
            if(grid[i - 1][j].isRoad())
            {
                res.add(new Coordinate(i - 1, j));
            }
        }
        if(j < grid[0].length - 1) // DROITE
        {

            if(grid[i][j + 1].isRoad())
            {
                res.add(new Coordinate(i, j + 1));
            }
        }
        if(i < grid.length - 1) // BAS
        {
            if(grid[i + 1][j].isRoad())
            {
                res.add(new Coordinate(i + 1, j));
            }
        }
        if(j > 0) // GAUCHE
        {
            if(grid[i][j - 1].isRoad())
            {
                res.add(new Coordinate(i, j - 1));
            }
        }

        return res;
    }


    /**
     * Determine si le coordonné existe dans arr.
     * @param arr le array
     * @param cord le coordonné à comparer avec
     * @return existe ou pas
     */
    private static boolean inArray(ArrayList<Coordinate> arr, Coordinate cord)
    {
        for (Coordinate element : arr)
        {
            if(element.i() == cord.i() && element.j() == cord.j())
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Trouver un chemin depuis le debut vers la base pour les monstres.
     * @param cord coordonné de debut
     * @return une liste avec les coordonnées des cellules de chemin depuis les coordonnées de début
     */
    private static ArrayList<Coordinate> getListeChemin(Coordinate cord)
    {
        ArrayList<Coordinate> res = new ArrayList<>();
        res.add(cord.copy());
        Coordinate current = cord.copy();
        while(true)
        {
            ArrayList<Coordinate> temp = new ArrayList<>();
            for(Coordinate couple: getVoisin(current))
            {
                Coordinate cord_temp = couple.intCopy();
                if(!(inArray(res, cord_temp)))
                {
                    temp.add(cord_temp);
                    res.add(cord_temp);
                }
            }
            if(res.size() > 1)
            {
                current.set(res.get(res.size()-1));
            }

            if(temp.isEmpty())
            {
                return res;
            }
        }
    }
}