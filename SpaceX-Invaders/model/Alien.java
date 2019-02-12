package _08final.SpaceX_Invaders.model;

import _08final.SpaceX_Invaders.controller.Game;

import java.awt.*;
import java.util.ArrayList;


public class Alien extends Sprite {

	// ==============================================================
	// FIELDS 
	// ==============================================================

	private boolean isMovingLeft = false;
	private boolean isMovingRight = false;

			
	private final double[] FLAME = { 23 * Math.PI / 24 + Math.PI / 2,
			Math.PI + Math.PI / 2, 25 * Math.PI / 24 + Math.PI / 2 };


	
	// ==============================================================
	// CONSTRUCTOR 
	// ==============================================================
	
	public Alien() {
		super();
		setTeam(Team.FRIEND);

		ArrayList<Point> pnts = new ArrayList<Point>();


        pnts.add(new Point(0,4));
        pnts.add(new Point(2,4));
        pnts.add(new Point(2,5));
        pnts.add(new Point(3,5));
        pnts.add(new Point(3,7));
        pnts.add(new Point(5,7));
        pnts.add(new Point(5,5));
        pnts.add(new Point(4,5));
        pnts.add(new Point(4,4));
        pnts.add(new Point(5,4));
        pnts.add(new Point(5,3));
        pnts.add(new Point(6,3));
        pnts.add(new Point(6,1));
        pnts.add(new Point(7,1));
        pnts.add(new Point(7,-3));
        pnts.add(new Point(6,-3));
        pnts.add(new Point(6,0));
        pnts.add(new Point(5,0));
        pnts.add(new Point(5,-3));
        pnts.add(new Point(3,-3));
        pnts.add(new Point(3,-4));
        pnts.add(new Point(1,-4));
        pnts.add(new Point(1,-3));
        pnts.add(new Point(3,-3));
        pnts.add(new Point(3,-2));

        pnts.add(new Point(0,-2));


        pnts.add(new Point(-3,-2));
        pnts.add(new Point(-3,-4));
        pnts.add(new Point(-3,-3));
        pnts.add(new Point(-1,-3));
        pnts.add(new Point(-1,-4));
        pnts.add(new Point(-3,-4));
        pnts.add(new Point(-3,-3));
        pnts.add(new Point(-5,-3));
        pnts.add(new Point(-5,0));
        pnts.add(new Point(-6,0));
        pnts.add(new Point(-6,-3));
        pnts.add(new Point(-7,-3));
        pnts.add(new Point(-7,1));
        pnts.add(new Point(-6,1));
        pnts.add(new Point(-6,3));
        pnts.add(new Point(-5,3));
        pnts.add(new Point(-5,4));
        pnts.add(new Point(-4,4));
        pnts.add(new Point(-4,5));
        pnts.add(new Point(-5,5));
        pnts.add(new Point(-5,7));
        pnts.add(new Point(-3,7));
        pnts.add(new Point(-3,5));
        pnts.add(new Point(-2,5));
        pnts.add(new Point(-2,4));


		assignPolarPoints(pnts);

		setColor(Color.green);
		
		//put alien under shield.
		setCenter(new Point(Game.DIM.width / 2 - 150, Game.DIM.height - 100));
		setOrientation(-90);
		setRadius(25);


		setFadeValue(0);
	}
	
	
	// ==============================================================
	// METHODS 
	// ==============================================================
	@Override
	public void move() {
		//super.move();

		if (isMovingLeft) {
            setCenter(new Point(getCenter().x - 10, getCenter().y));
		}
		else if (isMovingRight) {
            setCenter(new Point(getCenter().x + 10, getCenter().y));
		}

	} //end move

	public void moveLeft() {
        isMovingRight = false;
        isMovingLeft = true;
	}

	public void moveRight() {
        isMovingLeft = false;
        isMovingRight = true;
	}

	public void stopMoving() {
        isMovingLeft = false;
        isMovingRight = false;
	}


	@Override
	public void draw(Graphics g) {
		drawShipWithColor(g, Color.green);
	} //end draw()

	public void drawShipWithColor(Graphics g, Color col) {
		super.draw(g);
		g.setColor(col);

        g.fillPolygon(getXcoords(), getYcoords(), dDegrees.length);
		g.drawPolygon(getXcoords(), getYcoords(), dDegrees.length);
	}


	
} //end class
