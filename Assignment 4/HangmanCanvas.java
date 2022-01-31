import acm.graphics.*;
import java.util.*;

public class HangmanCanvas extends GCanvas {

	public void reset() {
		removeAll();
		drawScaffold();
	}

	public void displayWord(String word) {
		if(current != null) {
			remove(current);
		}
		showProgress(word);
	}

	public void noteIncorrectGuess(char letter) {
		if(currentIncorrect != null) {
			remove(currentIncorrect);
		}
		showIncorrect(letter);
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	
	
	private static final double CENTERX = 377/2; 
	private static final double TOPY = 236 - 171 - 50;
	private static final double HEAD_X = CENTERX - HEAD_RADIUS;
	private static final double HEAD_Y = TOPY + ROPE_LENGTH;
	private static final double BODY_START_Y = TOPY + ROPE_LENGTH + 2*HEAD_RADIUS;
	private static final double BODY_END_Y = TOPY + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH;
	private static final double ARM_START_Y = HEAD_Y + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
	
	private void drawScaffold() { 
		double startX = CENTERX - BEAM_LENGTH;
		double startY = TOPY;
		double endX = startX;
		double endY = startY + SCAFFOLD_HEIGHT;
		
		GLine scaffold = new GLine(startX, startY, endX, endY);
		GLine beam = new GLine(startX, startY, endX + BEAM_LENGTH, startY);
		GLine rope = new GLine(CENTERX, startY, CENTERX, startY + ROPE_LENGTH);
		
		add(scaffold);
		add(beam);
		add(rope);
	}
	
	private void drawHead() {
		GOval head = new GOval(2*HEAD_RADIUS, 2*HEAD_RADIUS);
		head.setLocation(HEAD_X, HEAD_Y);
		add(head);
	}
	
	private void drawBody() {
		GLine body = new GLine(CENTERX, BODY_START_Y, CENTERX, BODY_END_Y);
		add(body);
	}
	
	private void drawLeftArm() {
		GLine leftUpperArm = new GLine(CENTERX, ARM_START_Y, CENTERX - UPPER_ARM_LENGTH, ARM_START_Y);
		GLine leftLowerArm = new GLine(CENTERX - UPPER_ARM_LENGTH, ARM_START_Y, CENTERX - UPPER_ARM_LENGTH, ARM_START_Y + LOWER_ARM_LENGTH);
		add(leftUpperArm);
		add(leftLowerArm);
	}
	
	private void drawRightArm() {
		GLine rightUpperArm = new GLine(CENTERX, ARM_START_Y, CENTERX + UPPER_ARM_LENGTH, ARM_START_Y);
		GLine rightLowerArm = new GLine(CENTERX + UPPER_ARM_LENGTH, ARM_START_Y, CENTERX + UPPER_ARM_LENGTH, ARM_START_Y + LOWER_ARM_LENGTH);
		add(rightUpperArm);
		add(rightLowerArm);
	}
	
	private void drawLeftLeg() { 
		GLine leftHip = new GLine(CENTERX, BODY_END_Y, CENTERX - HIP_WIDTH, BODY_END_Y);
		GLine leftLeg = new GLine(CENTERX - HIP_WIDTH, BODY_END_Y, CENTERX - HIP_WIDTH, BODY_END_Y + LEG_LENGTH);
		add(leftHip);
		add(leftLeg);
	}
	
	private void drawRightLeg() {
		GLine rightHip = new GLine(CENTERX, BODY_END_Y, CENTERX + HIP_WIDTH, BODY_END_Y);
		GLine rightLeg = new GLine (CENTERX + HIP_WIDTH, BODY_END_Y, CENTERX + HIP_WIDTH, BODY_END_Y + LEG_LENGTH);
		add(rightHip);
		add(rightLeg);
	}
	
	private void drawLeftFoot() {
		GLine leftFoot = new GLine(CENTERX - HIP_WIDTH, BODY_END_Y + LEG_LENGTH, CENTERX - HIP_WIDTH - FOOT_LENGTH, BODY_END_Y + LEG_LENGTH);
		add(leftFoot);
	}
	
	private void drawRightFoot() {
		GLine rightFoot = new GLine(CENTERX + HIP_WIDTH, BODY_END_Y + LEG_LENGTH, CENTERX + HIP_WIDTH + FOOT_LENGTH, BODY_END_Y + LEG_LENGTH);
		add(rightFoot);
	}
	
	
	private GLabel current; 
	private void showProgress(String word) {
		current = new GLabel(word);
		current.setFont("-18");
		current.setLocation(CENTERX - BEAM_LENGTH - 2, TOPY + SCAFFOLD_HEIGHT + 25);
		add(current);
	}
	
	private GLabel currentIncorrect; 
	public String currentIncorrectNotes = ""; 
	private void showIncorrect(char note) {  
		if (currentIncorrectNotes.indexOf(note) == -1) {
			currentIncorrectNotes += note;			
		}
		currentIncorrect = new GLabel(currentIncorrectNotes);
		currentIncorrect.setFont("-15");
		currentIncorrect.setLocation(CENTERX - BEAM_LENGTH - 2, TOPY + SCAFFOLD_HEIGHT + 45);
		add(currentIncorrect);
	}
	
	
	public void drawNext() {
		if (currentIncorrectNotes.length() == 1) {
			drawHead();
		}else if (currentIncorrectNotes.length() == 2) {
			drawBody();
		}else if (currentIncorrectNotes.length() == 3) {
			drawLeftArm();
		}else if (currentIncorrectNotes.length() == 4) {
			drawRightArm();
		}else if (currentIncorrectNotes.length() == 5) {
			drawLeftLeg();
		}else if (currentIncorrectNotes.length() == 6) {
			drawRightLeg();
		}else if (currentIncorrectNotes.length() == 7) {
			drawLeftFoot();
		}else {
			drawRightFoot();
		}		
	}
}
