package _08final.SpaceX_Invaders.model;


import java.awt.*;
import java.util.ArrayList;


public class ShieldBlock extends Sprite {

    public ShieldBlock(Point point){
        //call Sprite constructor
        super();
        setTeam(Team.FRIEND);

        ArrayList<Point> points = new ArrayList<Point>();

        points.add(new Point(1, 1));
        points.add(new Point(1, -1));
        points.add(new Point(-1, -1));
        points.add(new Point(-1, 1));

        assignPolarPoints(points);

        setRadius(5);
        setCenter(point);
    }


    @Override
    public void move(){
        // no movement
    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.fillPolygon(getXcoords(), getYcoords(), dDegrees.length);
    }
}
