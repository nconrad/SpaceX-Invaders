package _08final.SpaceX_Invaders.model;

import _08final.SpaceX_Invaders.controller.Game;
import _08final.sounds.Sound;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class CommandCenter {

	private  int numOfLives;
	private  int nLevel;
	private  long lScore;
	private Alien alien;
	private  boolean bPlaying;
	private  boolean bPaused;
	
	// These ArrayLists with capacities set
	private List<Movable> movDebris = new ArrayList<Movable>(300);
	private List<Movable> movFriends = new ArrayList<Movable>(100);
	private List<Movable> movFoes = new ArrayList<Movable>(200);
	private List<Movable> movFloaters = new ArrayList<Movable>(50);

	private GameOpsList opsList = new GameOpsList();


	private static CommandCenter instance = null;

	// Constructor made private - static Utility class only
	private CommandCenter() {}


	public static CommandCenter getInstance(){
		if (instance == null){
			instance = new CommandCenter();
		}
		return instance;
	}


	public  void initGame(){
		setLevel(1);
		setScore(0);
		setNumAliens(3);
		spawnAlien(true);

		placeShields();
	}
	
	// The parameter is true if this is for the beginning of the game, otherwise false
	// When you spawn a new falcon, you need to decrement its number
	public void spawnAlien(boolean bFirst) {
		if (getNumAliens() != 0) {
			alien = new Alien();

            if (bFirst) {
                opsList.enqueue(alien, CollisionOp.Operation.ADD);
            }
            if (!bFirst) {
                ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);

                exec.schedule(() -> {
                    opsList.enqueue(alien, CollisionOp.Operation.ADD);
                    setNumAliens(getNumAliens() - 1);
                }, 500, TimeUnit.MILLISECONDS);
            }
		}
		
		Sound.playSound("shipspawn.wav");
	}

	public void placeShields() {

        placeShield(100);
        placeShield(350);
        placeShield(600);
        placeShield(850);
    }

    private void placeShield(int start) {
        int yStart = 200;
        int w = 5;

        for (int x = start + 10;  x < start + 90; x += w) {
            Point center = new Point(x, (int) Game.DIM.getHeight() - yStart);
            CommandCenter.getInstance().getOpsList().enqueue(
                    new ShieldBlock(center), CollisionOp.Operation.ADD);
        }

        for (int x = start;  x < start + 100; x += w) {
            Point center = new Point(x, (int) Game.DIM.getHeight() - yStart + 4);
            CommandCenter.getInstance().getOpsList().enqueue(
                    new ShieldBlock(center), CollisionOp.Operation.ADD);
        }

        for (int x = start;  x < start + 100; x += w) {
            Point center = new Point(x, (int) Game.DIM.getHeight() - yStart + 8);
            CommandCenter.getInstance().getOpsList().enqueue(
                    new ShieldBlock(center), CollisionOp.Operation.ADD);
        }

        for (int x = start;  x < start + 100; x += w) {
            Point center = new Point(x, (int) Game.DIM.getHeight() - yStart + 12);
            CommandCenter.getInstance().getOpsList().enqueue(
                    new ShieldBlock(center), CollisionOp.Operation.ADD);
        }

        for (int x = start;  x < start + 100; x += w) {
            Point center = new Point(x, (int) Game.DIM.getHeight() - yStart + 16);
            CommandCenter.getInstance().getOpsList().enqueue(
                    new ShieldBlock(center), CollisionOp.Operation.ADD);
        }

        for (int x = start;  x < start + 100; x += w) {
            Point center = new Point(x, (int) Game.DIM.getHeight() - yStart + 20);
            CommandCenter.getInstance().getOpsList().enqueue(
                    new ShieldBlock(center), CollisionOp.Operation.ADD);
        }


        // Wing parts of shiels
        for (int x = start;  x < start + 15; x += w) {
            Point center = new Point(x, (int) Game.DIM.getHeight() - yStart + 24);
            CommandCenter.getInstance().getOpsList().enqueue(
                    new ShieldBlock(center), CollisionOp.Operation.ADD);
        }

        for (int x = start + 85;  x < start + 100; x += w) {
            Point center = new Point(x, (int) Game.DIM.getHeight() - yStart + 24);
            CommandCenter.getInstance().getOpsList().enqueue(
                    new ShieldBlock(center), CollisionOp.Operation.ADD);
        }

        for (int x = start;  x < start + 10; x += w) {
            Point center = new Point(x, (int) Game.DIM.getHeight() - yStart + 28);
            CommandCenter.getInstance().getOpsList().enqueue(
                    new ShieldBlock(center), CollisionOp.Operation.ADD);
        }

        for (int x = start + 90;  x < start + 100; x += w) {
            Point center = new Point(x, (int) Game.DIM.getHeight() - yStart + 28);
            CommandCenter.getInstance().getOpsList().enqueue(
                    new ShieldBlock(center), CollisionOp.Operation.ADD);
        }
    }



	public GameOpsList getOpsList() {
		return opsList;
	}

	public void setOpsList(GameOpsList opsList) {
		this.opsList = opsList;
	}

	public  void clearAll(){
		movDebris.clear();
		movFriends.clear();
		movFoes.clear();
		movFloaters.clear();
	}

	public  boolean isPlaying() {
		return bPlaying;
	}

	public  void setPlaying(boolean bPlaying) {
		this.bPlaying = bPlaying;
	}

	public  boolean isPaused() {
		return bPaused;
	}

	public  void setPaused(boolean bPaused) {
		this.bPaused = bPaused;
	}
	
	public  boolean isGameOver() {		//if the number is zero, then game over
		if (getNumAliens() == 0) {
			return true;
		}
		return false;
	}

	public  int getLevel() {
		return nLevel;
	}

	public   long getScore() {
		return lScore;
	}

	public  void setScore(long lParam) {
		lScore = lParam;
	}

	public  void setLevel(int n) {
		nLevel = n;
	}

	public  int getNumAliens() {
		return numOfLives;
	}

	public  void setNumAliens(int nParam) {
        numOfLives = nParam;
	}
	
	public Alien getAlien(){
		return alien;
	}
	
	public void setAlien(Alien alien){
        this.alien = alien;
	}

	public  List<Movable> getMovDebris() {
		return movDebris;
	}



	public  List<Movable> getMovFriends() {
		return movFriends;
	}



	public  List<Movable> getMovFoes() {
		return movFoes;
	}


	public  List<Movable> getMovFloaters() {
		return movFloaters;
	}

}
