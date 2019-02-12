package _08final.SpaceX_Invaders.model;

import _08final.SpaceX_Invaders.controller.Game;

import java.awt.*;
import java.util.ArrayList;

public class SpaceXShip extends Sprite implements Scorable {
    private Point startCenter;
    private int shipKind;

	private final int RAD = 30;

	public SpaceXShip(Point point, int row){
        //call Sprite constructor
        super();
        setTeam(Team.FOE);

        startCenter = point;

        if (row == 1 || row == 2)
            shipKind = 1;
        else if (row == 3 || row == 4)
            shipKind = 2;
        else if (row == 5)
            shipKind = 1;


        // initial movement is to the right
        // speed is determined by the level
		setDeltaX(CommandCenter.getInstance().getLevel() + 1);

		assignShape();
        setRadius(RAD);
        setOrientation(270);
        setCenter(point);
	}



	@Override
	public void move(){
        Point pnt = getCenter();
        double dX = pnt.x + getDeltaX();
        double dY = pnt.y + getDeltaY();

        // reverse direction, and move down when ship has moved over 50 pixels
        if (Math.abs(startCenter.x - dX) > 75) {
            setDeltaX(-1 * getDeltaX());
            setCenter(new Point((int) (pnt.x + getDeltaX()), pnt.y + 25));
        } else {
            setCenter(new Point((int) dX, (int) dY));
        }


        if ((getExpire() % 20) + Game.R.nextInt(1000) == 0) {
            // fire a bullet
            CommandCenter.getInstance().getOpsList().enqueue(new ShipBullet(this), CollisionOp.Operation.ADD);
        }

        if (getCenter().y + getRadius() > Game.DIM.getHeight()) {
            CommandCenter.getInstance().getOpsList().enqueue(this, CollisionOp.Operation.REMOVE);
            CommandCenter.getInstance().setNumAliens(0);
        }
	}


	private void assignShape() {

        ArrayList<Point> points = new ArrayList<Point>();

        if (shipKind == 2) {
            points.add(new Point(0, 5));
            points.add(new Point(3, 5));
            points.add(new Point(2, 6));
            points.add(new Point(6, 6));
            points.add(new Point(5, 5));
            points.add(new Point(8, 5));
            points.add(new Point(5, 0));
            points.add(new Point(5, -6));
            points.add(new Point(4, -8));
            points.add(new Point(3, -9));
            points.add(new Point(0, -10));
            points.add(new Point(-3, -9));
            points.add(new Point(-4, -8));
            points.add(new Point(-5, -6));
            points.add(new Point(-5, 0));
            points.add(new Point(-8, 5));
            points.add(new Point(-5, 5));
            points.add(new Point(-6, 6));
            points.add(new Point(-2, 6));
            points.add(new Point(-3, 5));
            points.add(new Point(0, 5));
        } else if (shipKind == 1) {
            points.add(new Point(0, 6));
            points.add(new Point(2, 6));
            points.add(new Point(1, 5));
            points.add(new Point(4, 5));
            points.add(new Point(4, 6));
            points.add(new Point(5, 6));
            points.add(new Point(5, 5));
            points.add(new Point(8, 5));
            points.add(new Point(8, 6));
            points.add(new Point(9, 6));
            points.add(new Point(9, 2));
            points.add(new Point(8, 2));
            points.add(new Point(8, 3));
            points.add(new Point(7, 3));
            points.add(new Point(5, 2));
            points.add(new Point(5, 0));
            points.add(new Point(4, 0));
            points.add(new Point(4, 1));
            points.add(new Point(3, 1));
            points.add(new Point(3, -2));
            points.add(new Point(2, -3));
            points.add(new Point(2, -7));
            points.add(new Point(0, -10));
            points.add(new Point(-2, -7));
            points.add(new Point(-2, -3));
            points.add(new Point(-3, -2));
            points.add(new Point(-3, 1));
            points.add(new Point(-4, 1));
            points.add(new Point(-4, 0));
            points.add(new Point(-5, 0));
            points.add(new Point(-5, 2));
            points.add(new Point(-7, 3));
            points.add(new Point(-8, 3));
            points.add(new Point(-8, 2));
            points.add(new Point(-9, 2));
            points.add(new Point(-9, 6));
            points.add(new Point(-8, 6));
            points.add(new Point(-8, 5));
            points.add(new Point(-5, 5));
            points.add(new Point(-5, 6));
            points.add(new Point(-4, 6));
            points.add(new Point(-4, 5));
            points.add(new Point(-1, 5));
            points.add(new Point(-2, 6));
            points.add(new Point(0, 6));
        }

        assignPolarPoints(points);

	}


    @Override
    public int getScore() {
        switch (shipKind){
            case 1:
                return 10;
            case 2:
                return 10;
            case 3:
                return 20;
            case 4:
                return 50;
            case 5:
                return 100;
            default:
                return 0;
        }
    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);
        //g.fillPolygon(getXcoords(), getYcoords(), dDegrees.length);

        if (shipKind == 1) {
            g.setColor(Color.cyan);
            g.fillPolygon(getXcoords(), getYcoords(), dDegrees.length);
        } else if (shipKind == 2) {
            g.setColor(Color.white);
            g.fillPolygon(getXcoords(), getYcoords(), dDegrees.length);


            // add SpaceX logo
            g.setColor(Color.black);
            int vSpace = 6,
                startX =  getCenter().x - 15,
                startY =  getCenter().y - 10;

            g.setFont(new Font("SansSerif", Font.PLAIN, 8));

            g.drawString("S", startX, startY + vSpace);
            g.drawString("p", startX + 4, startY + vSpace*2);
            g.drawString("a", startX + 8, startY + vSpace*3);
            g.drawString("c", startX + 12, startY + vSpace*4);
            g.drawString("e", startX + 16, startY + vSpace*5);
            g.drawString("X", startX + 20, startY + vSpace*6);
        }

    }
}
