import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {

	
	private static final double OUTER_RADIUS = 72;
	private static final double MIDDLE_RADIUS = 47;
	private static final double INNER_RADIUS = 22;
	
	private static final double OUTER_DIAMETER = 2*OUTER_RADIUS;
	private static final double MIDDLE_DIAMETER = 2*MIDDLE_RADIUS;
	private static final double INNER_DIAMETER = 2*INNER_RADIUS;
	
	public void run() {
		drawCircle(getWidth()/2 - OUTER_RADIUS, getHeight()/2 - OUTER_RADIUS, OUTER_DIAMETER, Color.red); 
		drawCircle(getWidth()/2 - MIDDLE_RADIUS, getHeight()/2 - MIDDLE_RADIUS, MIDDLE_DIAMETER, Color.white); 
		drawCircle(getWidth()/2 - INNER_RADIUS, getHeight()/2 - INNER_RADIUS, INNER_DIAMETER, Color.red);
	}
	
	private void drawCircle(double x, double y, double diameter, Color color) {
		GOval circle = new GOval(x, y, diameter, diameter);
		circle.setFillColor(color);
		circle.setFilled(true);
		add(circle);
	}
}

