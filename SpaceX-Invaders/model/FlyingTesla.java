package _08final.SpaceX_Invaders.model;


import _08final.SpaceX_Invaders.controller.Game;
import java.awt.*;
import java.util.ArrayList;

public class FlyingTesla extends Sprite implements Scorable {

    // number of times flying tesla will turn around
    private int uTurnCount = 0;
    ArrayList<Point> points = new ArrayList<>();

    // flame taken was taken from astroids
    private final double[] FLAME = { 23 * Math.PI / 24 + Math.PI / 2,
            Math.PI + Math.PI / 2, 25 * Math.PI / 24 + Math.PI / 2 };

    private Point[] pntFlames = new Point[FLAME.length];

    private int[] nXFlames = new int[FLAME.length];
    private int[] nYFlames = new int[FLAME.length];

    private Graphics g;

    public FlyingTesla() {
		super();
		setTeam(Team.FOE);


		points.add(new Point(0,3));
		points.add(new Point(4,3));
		points.add(new Point(6,0));
		points.add(new Point(10,0));
        points.add(new Point(10,-2));
		points.add(new Point(11,-2));
		points.add(new Point(11,-3));
		points.add(new Point(10,-3));
		points.add(new Point(10,-4));
		points.add(new Point(7,-4));
		points.add(new Point(6,-3));
		points.add(new Point(5,-3));
		points.add(new Point(4,-4));
		points.add(new Point(-3,-4));
		points.add(new Point(-4,-3));
		points.add(new Point(-5,-3));
		points.add(new Point(-6,-4));
		points.add(new Point(-10,-4));
		points.add(new Point(-10,-2));
		points.add(new Point(-9,-1));
		points.add(new Point(-8,0));
		points.add(new Point(-4,0));
		points.add(new Point(-2,3));
		points.add(new Point(0,3));

		assignPolarPoints(points);
        uTurn();

		setExpire(340);
		setRadius(50);
		setColor(Color.WHITE);

		setDeltaX(-7);
		setCenter(new Point(Game.DIM.width, 50));

		//random orientation 
		setOrientation(-90);
	}


	public void uTurn() {
        for (Point point : points) {
            point.setLocation(-1*point.x, point.y);
        }
        assignPolarPoints(points);
    }


	@Override
	public void move() {
		super.move();

        // change direction a few times
        if (getExpire() % 200 == 0 && uTurnCount < 3) {
            uTurn();
            setDeltaX(-1 * getDeltaX());
            uTurnCount++;
        }


        if (getExpire() == 0)
            CommandCenter.getInstance().getOpsList().enqueue(this, CollisionOp.Operation.REMOVE);
        else
            setExpire(getExpire() - 1);

	}

    /**
     * add frame
     * @param reverse determines which side of the flying tesla the flame is on.
     *                defaults to right side
     */
	public void flame(boolean reverse) {
        for (int nC = 0; nC < FLAME.length; nC++) {
            if (nC % 2 != 0) {
                pntFlames[nC] = new Point((int) (getCenter().x + 2
                        * getRadius()
                        * Math.sin(Math.toRadians(reverse ? 360 : 180)
                        + FLAME[nC])), (int) (getCenter().y - 2
                        * getRadius()
                        * Math.cos(Math.toRadians(reverse ? 360 : 180)
                        + FLAME[nC])));

            } else {
                pntFlames[nC] = new Point((int) (getCenter().x + getRadius()
                        * 1.1
                        * Math.sin(Math.toRadians(reverse ? 360 : 180)
                        + FLAME[nC])),
                        (int) (getCenter().y - getRadius()
                                * 1.1
                                * Math.cos(Math.toRadians(reverse ? 360 : 180)
                                + FLAME[nC])));

            } //end even/odd else

        } //end for loop

        for (int nC = 0; nC < FLAME.length; nC++) {
            nXFlames[nC] = pntFlames[nC].x;
            nYFlames[nC] = pntFlames[nC].y;

        } //end assign flame points


        // change flame color over frames
        if (getExpire() % 5 == 0)
            g.setColor(Color.red);
        else
            g.setColor(Color.orange);

        //g.setColor( Color.white );
        g.fillPolygon(nXFlames, nYFlames, 3);
    }

    @Override
    public int getScore() {
        return 500;
    }


	@Override
	public void draw(Graphics g) {
		super.draw(g);

        this.g = g;
		//fill this polygon (with whatever color it has)
		g.fillPolygon(getXcoords(), getYcoords(), dDegrees.length);
		//now draw a white border
		g.setColor(Color.BLUE);
		g.drawPolygon(getXcoords(), getYcoords(), dDegrees.length);

        flame(uTurnCount % 2 != 0);

        g.setFont(new Font("CourierNew", Font.BOLD, 12));
        g.setColor(Color.BLACK);
        g.drawString("TESLA", getCenter().x - 23, getCenter().y + 12);
	}

}
