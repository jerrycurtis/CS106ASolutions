/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board
 *  Should not be used directly (use getWidth()/getHeight() instead).
 *  * */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 2;

/** Width of a brick */
	private static final int BRICK_WIDTH = 
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
/** Private instance variable ball */
	private GOval ball;
	
/** Private instance variable brick */
	private GRect brick;
	
/** Private instance variable velocity */
	private double vx, vy;
	
/** Private instance variable for generating vx randomly */
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
/** Animation delay or paust time between ball moves */
    private static final int DELAY = 15;

    
    public static void main(String[] args) {
    	new Breakout().start(args);
    }
    
/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		setSize(APPLICATION_WIDTH,APPLICATION_HEIGHT);
		buildGame();
		for ( int i = NTURNS; i > 0; i--){
			setLives(i);
			playGame();
		}
		gameOver();
	}
	
	private void buildGame() {
		buildBricks();
		setPaddle();
		welcomeMessage();
	}
	
	private void playGame() {
		setBall();
		waitForClick();
		remove(welcome);
		velBall();
		while (true) {
			moveBall();
			if ((ball.getY() + vy >= (APPLICATION_HEIGHT - BALL_RADIUS*2)) && vy > 0) {
				remove(ball);
				remove(numberOfLives);
				break;
				}
			if ( brickCounter == 0 ) {
				remove(ball);
				victoryMessage();
			}
		}
	}
	
	private void setBall() {
		int start_x = ( APPLICATION_WIDTH / 2 ) - ( BALL_RADIUS / 2) ;
		int start_y = ( APPLICATION_HEIGHT / 2 ) - ( BALL_RADIUS / 2) ;
		ball = new GOval( start_x, start_y, BALL_RADIUS*2, BALL_RADIUS*2);
		ball.setFilled(true);
		add(ball);
		addMouseListeners();
	}
	
	private void velBall() {
		vy = 3.0;
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) vx = -vx;
	}
	
	private void moveBall() {
		ball.move (vx,vy);
		pause(DELAY);
		double ballX = ball.getX();
		//double ballY = ball.getY();
        //check for walls
        //need to get vx and vy at the point closest to 0 or the other edge
        if ( ballX - vx <= 0 && vx < 0 || ballX + vx >= (APPLICATION_WIDTH - BALL_RADIUS*2) && vx > 0) {
            vx = -vx;
        }
        if ((ball.getY() - vy <= 0 ) && vy < 0) {
            vy = -vy;
        }
        GObject collider = getCollidingObject();
        if (collider == paddle) {
        	vy = -vy;
        	AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
        	bounceClip.play();
        }
        else if (collider != null) {
        	remove(collider);
        	AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
        	bounceClip.play();
        	brickCounter --;
        	vy = -vy;
        }
        
    }
	
	private GObject getCollidingObject() {
		if (getElementAt(ball.getX(), ball.getY()) != null) {
			return getElementAt(ball.getX(), ball.getY());
			}
		else if (getElementAt(ball.getX() + (BALL_RADIUS * 2), ball.getY()) != null) {
			return getElementAt(ball.getX() + (BALL_RADIUS * 2), ball.getY());
		}
		else if (getElementAt(ball.getX(), ball.getY() + (BALL_RADIUS * 2)) != null) {
			return getElementAt(ball.getX(), ball.getY() + (BALL_RADIUS * 2));
		}
		else if (getElementAt(ball.getX() + (BALL_RADIUS * 2), ball.getY() + (BALL_RADIUS * 2)) != null) {
			return getElementAt(ball.getX() + (BALL_RADIUS * 2), ball.getY() + (BALL_RADIUS * 2));
		} else {
			return null;
		}
	}
	
	//Set paddle as an object
	//Create paddle
	//Make paddle move to mouse movements... YEAH BITCH, JAVA!
	private GRect paddle;
	
	private void setPaddle() {
		double x = APPLICATION_WIDTH - getWidth() - PADDLE_WIDTH/2;
		double y = APPLICATION_HEIGHT -(PADDLE_Y_OFFSET - PADDLE_HEIGHT);
		paddle = new GRect( x , y , PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setColor(Color.black);
		add(paddle);
	}
	
    public void mouseMoved(MouseEvent e) {
        double paddleX = e.getX();
        double paddleY = HEIGHT - PADDLE_Y_OFFSET;
        paddle.setLocation(paddleX, paddleY);
        if ((paddleX + PADDLE_WIDTH) > WIDTH) {
            paddle.setLocation((WIDTH - PADDLE_WIDTH), paddleY);
        }
    }

/* This method will build the bricks for the Breakout game! */
    
	private void buildBricks() {
		// Coordinates of the first bricks from left-top
		int x = (getWidth()*2) - APPLICATION_WIDTH - BRICK_WIDTH;
		int y = BRICK_Y_OFFSET;
		
		// Loop that count the numbers of rows until NBRICK_ROWS is met
		for (int i = 0; i < NBRICK_ROWS; i++) {
			
			//Loop that counts bricks per row until snetinel is met
			for (int j = 0; j < NBRICKS_PER_ROW; j++) {
				brick = new GRect( x += (BRICK_WIDTH + BRICK_SEP), y, BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				if ( i == 0 || i == 1) {
				brick.setColor(Color.red);
				} else if ( i == 2 || i == 3) {
					brick.setColor(Color.orange);
				} else if ( i == 4 || i == 5) {
					brick.setColor(Color.yellow);
				} else if ( i == 6 || i == 7 ) {
					brick.setColor(Color.green);
				} else if ( i == 8 || i == 9 ) {
					brick.setColor(Color.cyan);
				}
				add(brick);
			}
		//Setup x y coordinates for next row
		y += (BRICK_HEIGHT + BRICK_SEP);
		x = APPLICATION_WIDTH - APPLICATION_WIDTH - BRICK_WIDTH;
		}
	}
	
/* Message displayed troughout the game. Names speak for themselves. */
	private GLabel welcome;
	
	private void welcomeMessage() {
		welcome = new GLabel("", 0, 200);
		welcome.setLabel("Welcome to my first game! Click mouse to begin...");
		add(welcome);
	}
	
	private GLabel numberOfLives;
	
	private void setLives(int i) {
		numberOfLives = new GLabel("", 200, 10);
		numberOfLives.setLabel("LIVES LEFT: " + i);
		add(numberOfLives);
	}
	
	private void victoryMessage() {
		GLabel victory = new GLabel("", 200, 200);
		victory.setLabel("WOW! AMAZING! YOU WON!");
		add(victory);
	}
		
	
	private void gameOver() {
		GLabel gameover = new GLabel("", 0, 200);
		gameover.setLabel("Sorry, game over!");
		add(gameover);
	}
	
	private int brickCounter = NBRICK_ROWS * NBRICKS_PER_ROW;

}
