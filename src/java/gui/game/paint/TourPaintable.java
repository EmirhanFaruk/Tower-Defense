package gui.game.paint;

import gui.Coordinate;
import model.tour.Tour;

public class TourPaintable extends Paintable
{
    Tour tour;


    public TourPaintable(int width, int height, double scale_width, double scale_height, Tour tour)
    {
        super(tour.getCoordinates(), width, height, scale_width, scale_height);
        this.tour = tour;
    }
}
