package _08final.SpaceX_Invaders.model;

import _08final.SpaceX_Invaders.controller.Game;

import java.awt.*;
import java.util.ArrayList;


public class ShipBullet extends Sprite {

	private final double FIRE_POWER = 35.0;



	public ShipBullet(SpaceXShip ship){

		super();
		setTeam(Team.FOE);

		//defined the points on a cartesean grid
		ArrayList<Point> pnts = new ArrayList<Point>();

		pnts.add(new Point(-1,2));
		pnts.add(new Point(1,2));
		pnts.add(new Point(1,-2));
		pnts.add(new Point(-1,-2));
		pnts.add(new Point(-1,2));

		assignPolarPoints(pnts);


		setExpire(100);
		setRadius(3);


		//everything is relative to the falcon ship that fired the bullet
		setDeltaX( 0 );
		setDeltaY( 10 );
		setCenter( ship.getCenter() );

		//set the bullet orientation to the falcon (ship) orientation
		setOrientation(ship.getOrientation());


	}

	@Override
	public void move(){
		super.move();

		if (getExpire() == 0)
			CommandCenter.getInstance().getOpsList().enqueue(this, CollisionOp.Operation.REMOVE);
		else if (getCenter().y > Game.DIM.getHeight())
			CommandCenter.getInstance().getOpsList().enqueue(this, CollisionOp.Operation.REMOVE);
		else
			setExpire(getExpire() - 1);

	}

}
