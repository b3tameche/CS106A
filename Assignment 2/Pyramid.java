import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

	private static final int BRICK_WIDTH = 30;
	private static final int BRICK_HEIGHT = 12;
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
		drawPyramid();
	}
	
	private void drawPyramid() {
		int brickCount = BRICKS_IN_BASE;
		int lineNumber = 1;
		while (brickCount > 0) {
			int x = (getWidth() - BRICK_WIDTH*brickCount)/2;
			int y = getHeight() - lineNumber * BRICK_HEIGHT;
			for (int i = 0; i < brickCount; i++) {
				GRect brick = new GRect(x + i*BRICK_WIDTH, y, BRICK_WIDTH, BRICK_HEIGHT);
				add(brick);
			}
			brickCount -= 1;
			lineNumber += 1;
		}
	}
}

