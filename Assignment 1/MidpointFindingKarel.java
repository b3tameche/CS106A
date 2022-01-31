import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {
	public void run() {
		if(frontIsClear()) {
			slowDoe();
			cleanUpTheMess();
		}else { 
			putBeeper();
		}
	}
	
	private void slowDoe() {
		while(frontIsClear() && noBeepersPresent()) {
			move();
		}
		check();
		putBeeper();
		move();
		if(beepersPresent()) {
			turnAround();
			move();
		}else {
			slowDoe();
		}
	}
	
	private void check() {
		if(beepersPresent()) {
			turnAround();
			move();
		}else {
			turnAround();
		}
	}
	
	private void cleanSide() {
		while(frontIsClear()) {
			move();
			pickBeeper();
		}
	}
	
	private void cleanUpTheMess() {
		cleanSide();
		turnAround();
		while(noBeepersPresent()) {
			move();
		}
		cleanSide();
	}
}
