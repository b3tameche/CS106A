import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
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
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;

	/** Initial Y coordinate for each double-row, this variable changes during execution */
	double initialY = BRICK_Y_OFFSET;
	
	/** Number of times the game lets us play */
	int turns = NTURNS;
	
	/** Remaining number of bricks */
	int brickCount = NBRICKS_PER_ROW * NBRICK_ROWS;
	
	/** Time between frames (used as milliseconds) */
	private static final int PAUSE = 7;
	
	/** Code starts from here */
	public void init() {
		addMouseListeners();
	}
	public void run() {
		initializeBricks();
		initializePaddle();
		
		waitForClick();
		
		turnInBall(ball);
	}
	
	
	private void drawBricks(Color color, double Y) {
		int initialX = (getWidth() - NBRICKS_PER_ROW * BRICK_WIDTH - BRICK_SEP * (NBRICKS_PER_ROW - 1)) / 2; 
		for (int i = 0; i < NBRICKS_PER_ROW; i++) {
			for (int j = 0; j < 2; j++) {
				GRect brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
				brick.setColor(color);
				brick.setFilled(true);
				add(brick, initialX + i * (BRICK_WIDTH + BRICK_SEP), Y + j * (BRICK_HEIGHT + BRICK_SEP));
			}
		}
		initialY = initialY + 2 * (BRICK_HEIGHT + BRICK_SEP); 
	}
	
	
	private void initializeBricks() {
		drawBricks(Color.red, initialY);
		drawBricks(Color.orange, initialY);
		drawBricks(Color.yellow, initialY);
		drawBricks(Color.green, initialY);
		drawBricks(Color.cyan, initialY);
	}
	
	/* This code block below stores actual paddle (which is GRect-type object) inside "paddle" */
	GObject paddle = paddle();
	private GRect paddle() {
		GRect paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setColor(Color.black);
		paddle.setFilled(true);
		return paddle;
	}
	
	private void initializePaddle(){
		add(paddle, getWidth()/2 - PADDLE_WIDTH/2, getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
	}
	
	public void mouseMoved(MouseEvent e) {
		double coordinateX = e.getX() - PADDLE_WIDTH/2;
		double coordinateY = getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
		if (coordinateX <= getWidth() - PADDLE_WIDTH && coordinateX >= 0) {
			paddle.setLocation(coordinateX, coordinateY);			
		}
	}

	GObject ball = ball();
	private GOval ball() {
		GOval ball = new GOval(2*BALL_RADIUS, 2*BALL_RADIUS);
		ball.setColor(Color.black);
		ball.setFilled(true);
		return ball;
	}
	
	/** This code block below is for animation and actual gameplay */
	private double vx;
	private double vy = 3.0;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	private void turnInBall(GObject object) {
		add(ball, getWidth()/2 - BALL_RADIUS, getHeight()/2 - BALL_RADIUS);
		vx = rgen.nextDouble(-3.0, 3.0); 
		while (true) {
			if (object.getX() >= getWidth() - 2*BALL_RADIUS) {
				vx = -vx;
			}
			if (object.getX() <= 0) {
				vx = -vx;
			}
			if(object.getY() <= 0) {
				vy = -vy;
			}
			if (object.getY() >= getHeight() - 2*BALL_RADIUS) { 
				if (turns != 1) {
					turns -= 1; 
					
					remove(ball); 
					
					waitForClick();

					turnInBall(ball);			
				}else { 
					break;
				}
			}
			GObject collider = getCollidingObject(object); 
			if (collider != null && collider != paddle) { 
				remove(collider); 
				brickCount -= 1; 
			}
			if (brickCount == 0) {
				remove(ball); 
				break; 
			}
			object.move(vx, vy);
			pause(PAUSE);
		}
	}
	
	private GObject getCollidingObject(GObject obje) {
		
		GObject object1 = getElementAt(obje.getX(), obje.getY());
		GObject object2 = getElementAt(obje.getX() + 2*BALL_RADIUS, obje.getY());
		GObject object3 = getElementAt(obje.getX(), obje.getY() + 2*BALL_RADIUS);
		GObject object4 = getElementAt(obje.getX() + 2*BALL_RADIUS, obje.getY() + 2*BALL_RADIUS);
		GObject placeholder = null; // áƒ”áƒ¡ áƒ£áƒ‘áƒ áƒ�áƒšáƒ�áƒ“ return-áƒ˜áƒ¡áƒ—áƒ•áƒ˜áƒ¡ áƒ¨áƒ”áƒ•áƒ¥áƒ›áƒ”áƒœáƒ˜
		 
		if (object1 != null && vy<0) {
			vy = -vy;
			return object1;
		}else if (object2 != null && vy<0) {
			vy = -vy;
			return object2;
		}else if (object3 != null && vy>0) {
			vy = -vy;
			return object3;
		}else if (object4 != null && vy>0) {
			vy = -vy;
			return object4;
		}
		
		if(getElementAt(obje.getX()-1, obje.getY()+BALL_RADIUS) != null) {
			vx=-vx;
			return getElementAt(obje.getX()-1, obje.getY()+BALL_RADIUS);
		}else if(getElementAt(obje.getX()+ 2*BALL_RADIUS + 1, obje.getY() + BALL_RADIUS) != null) {
			vx=-vx;
			return getElementAt(obje.getX()+ 2*BALL_RADIUS + 1, obje.getY() + BALL_RADIUS);
		}
		return placeholder;
	}
}
