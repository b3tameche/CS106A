import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
	public void run() {
		if(frontIsBlocked()) {
			if(leftIsClear()) {
				turnLeft();
				completeLine();
			}else {
				putBeeper();
			}
		}else {
			chickFillA();
		}
	}
	
	private void chickFillA() {
		completeLine();
		checkFromEastSide();
		checkFromWestSide();
	}
	
	private void completeLine() {
		while(frontIsClear()) {
			if(noBeepersPresent()) {
				putBeeper();
			}
			move();
			if(frontIsClear()) {
				move();
				putBeeper();
			}
		}
	}
	
	private void checkFromEastSide() {
		if(facingEast() && leftIsClear()) {
			if(noBeepersPresent()) {
				turnLeft();
				move();
				turnLeft();
				putBeeper();
				chickFillA();
			}else {
				turnLeft();
				move();
				turnLeft();
				if (frontIsClear()) {
					move();
				}
				chickFillA();
			}
		}
	}
	
	private void checkFromWestSide() {
		if(facingWest() && rightIsClear()) {
			if(noBeepersPresent()) {
				turnRight();
				move();
				turnRight();
				putBeeper();
				chickFillA();
			}else {
				turnRight();
				move();
				turnRight();
				if(frontIsClear()) {
					move();
				}
				chickFillA();
			}
		}
	}
}
