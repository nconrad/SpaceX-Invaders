package _08final.SpaceX_Invaders.model;

import java.awt.*;
import java.util.ArrayList;


public class AlienBullet extends Sprite {

	private final double FIRE_POWER = 35.0;

    public AlienBullet(Alien alien) {

		super();
	    setTeam(Team.FRIEND);

		//defined the points on a cartesean grid
		ArrayList<Point> points = new ArrayList<Point>();

        //points.add(new Point(-1,2));
        //points.add(new Point(1,2));
        //points.add(new Point(1,-2));
        //points.add(new Point(-1,-2));

        points.add(new Point(0,3));
        points.add(new Point(1,2));
        points.add(new Point(2,1));
        points.add(new Point(0,0));
        points.add(new Point(0,-1));
        points.add(new Point(1,-2));
        points.add(new Point(0,-2));
        points.add(new Point(-1,-1));
        points.add(new Point(-1,0));
        points.add(new Point(0,1));
        points.add(new Point(0,2));
        points.add(new Point(-1,3));
        //points.add(new Point(0,3));

		assignPolarPoints(points);

		//a bullet expires after 20 frames
	    setExpire( 40 );
	    setRadius(6);

		setColor(Color.green);

	    //everything is relative to the falcon ship that fired the bullet
	    setDeltaX( 0 );
	    setDeltaY( -20 );
	    setCenter( new Point(alien.getCenter().x, alien.getCenter().y - 15)  );


	    setOrientation(alien.getOrientation());
	}

	@Override
	public void move(){
		super.move();

		if (getExpire() == 0)
			CommandCenter.getInstance().getOpsList().enqueue(this, CollisionOp.Operation.REMOVE);
		else
			setExpire(getExpire() - 1);
	}

    public void draw(Graphics g) {
        super.draw(g);

        g.fillPolygon(getXcoords(), getYcoords(), dDegrees.length);
    }
}
