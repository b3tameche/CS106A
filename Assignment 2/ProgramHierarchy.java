import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	private static final double RECT_WIDTH = 150; 
	private static final double RECT_HEIGHT = 50; 
	private static final double OFFSET_BETWEEN_SUBCLASSES = 26; 
	private static final double LINE_HEIGHT = 45; 
		
	public void run() {
		double programStartingX = (getWidth() - RECT_WIDTH)/2; 
		double programStartingY = (getHeight() - RECT_HEIGHT*2 - LINE_HEIGHT)/2;
		double graphicsProgramStartingX = programStartingX - RECT_WIDTH - OFFSET_BETWEEN_SUBCLASSES;
		double dialogProgramStartingX = programStartingX + RECT_WIDTH + OFFSET_BETWEEN_SUBCLASSES;
		double subclassStartingY = programStartingY + RECT_HEIGHT + LINE_HEIGHT; 
		
		GLabel program = new GLabel("Program");
		double programLabelX = getWidth()/2 - program.getWidth()/2;
		double programLabelY = programStartingY + RECT_HEIGHT/2 + program.getAscent()/2;
		
		GLabel graphicsProgram = new GLabel("GraphicsProgram");
		double graphicsProgramLabelX = graphicsProgramStartingX + RECT_WIDTH/2 - graphicsProgram.getWidth()/2;
		double graphicsProgramLabelY = subclassStartingY + RECT_HEIGHT/2 + graphicsProgram.getAscent()/2;
		
		GLabel consoleProgram = new GLabel("ConsoleProgram");
		double consoleProgramLabelX = getWidth()/2 - consoleProgram.getWidth()/2;
		double consoleProgramLabelY = subclassStartingY + RECT_HEIGHT/2 + consoleProgram.getAscent()/2;
		
		GLabel dialogProgram = new GLabel("DialogProgram");
		double dialogProgramLabelX = dialogProgramStartingX + RECT_WIDTH/2 - dialogProgram.getWidth()/2;
		double dialogProgramLabelY = subclassStartingY + RECT_HEIGHT/2 + dialogProgram.getAscent()/2;
		
		drawBox(RECT_WIDTH, RECT_HEIGHT, programStartingX, programStartingY, program, programLabelX, programLabelY);
		drawBox(RECT_WIDTH, RECT_HEIGHT, graphicsProgramStartingX, subclassStartingY, graphicsProgram, graphicsProgramLabelX, graphicsProgramLabelY);
		drawBox(RECT_WIDTH, RECT_HEIGHT, programStartingX, subclassStartingY, consoleProgram, consoleProgramLabelX, consoleProgramLabelY);
		drawBox(RECT_WIDTH, RECT_HEIGHT, dialogProgramStartingX, subclassStartingY, dialogProgram, dialogProgramLabelX, dialogProgramLabelY);
		connect(); 
	}
	
	private void drawBox(double width, double height, double x, double y, GLabel label, double labelX, double labelY) {
		GRect box = new GRect(x, y, width, height);
		label.setLocation(labelX, labelY);
		add(box);
		add(label);
	}
	
	private void connect() {
		double baseY = (getHeight() - RECT_HEIGHT*2 - LINE_HEIGHT)/2 + RECT_HEIGHT;
		double startingX = getWidth()/2 - RECT_WIDTH - OFFSET_BETWEEN_SUBCLASSES; 
		
		for (int i = 0; i<3; i++) {
			GLine line = new GLine(getWidth()/2, baseY, startingX + i*(RECT_WIDTH + OFFSET_BETWEEN_SUBCLASSES), baseY + LINE_HEIGHT);
			add(line);
		}
	}
}
