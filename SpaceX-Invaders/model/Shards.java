package _08final.SpaceX_Invaders.model;


import java.awt.*;
import java.util.ArrayList;


public class Shards extends Sprite {

    private final double FIRE_POWER = 15.0; // set this down
    private static final int FADE = 5; // number of frames that this will exist
    private int fadeRed, fadeGreen, fadeBlue;

    private String type;

    public Shards(Sprite sprite, int nOr, String type) {

        super();
        setTeam(Team.DEBRIS);

        this.type = type; // explosion or implosion

        //defined the points on a cartesian grid
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1, 0));
        points.add(new Point(-1, 0));

        setColor(Color.ORANGE);
        assignPolarPoints(points);

        setExpire(FADE); // a hairball has limited range
        setRadius(sprite.getRadius() / 4); // the radius of explosion is a function of the size of the sprite
        setOrientation(nOr);

        setDeltaX(sprite.getDeltaX() +
                Math.cos(Math.toRadians(getOrientation())) * FIRE_POWER); // orientation of the shard
        setDeltaY(sprite.getDeltaY() +
                Math.sin(Math.toRadians(getOrientation())) * FIRE_POWER);
        setCenter(sprite.getCenter());

        fadeGreen = sprite.getColor().getGreen() / FADE;
        fadeBlue = sprite.getColor().getBlue() / FADE;
        fadeRed = sprite.getColor().getRed() / FADE;
    }

    @Override
    public void move() {
        if (type.equals("explosion")) super.move();

        if (getExpire() == 0)
            CommandCenter.getInstance().getOpsList().enqueue(this, CollisionOp.Operation.REMOVE);
        else {
            setExpire(getExpire() - 1);
            Color currentColor = getColor();
            setColor(new Color(
                    // use Math.abs to ensure we have positive numbers
                    Math.abs(currentColor.getGreen() - fadeGreen),
                    Math.abs(currentColor.getBlue() - fadeBlue),
                    Math.abs(currentColor.getRed() - fadeRed)));
        }

    }
}