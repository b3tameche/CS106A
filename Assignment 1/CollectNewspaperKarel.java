import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {
	public void run() {
		moveToNewspaper();
		takeNewspaper();
		comingHome();
	}
	
	private void moveToNewspaper() {
		while(leftIsBlocked()) {
			if(frontIsBlocked()) {
				turnRight();
			}
			move();
		}
		turnLeft();
		move();
	}
	
	private void takeNewspaper() {
		pickBeeper();
		turnAround();
	}
	
	private void comingHome() {
		move();
		turnRight();
		move();
		while(!facingWest() || frontIsClear()) {
			if(frontIsBlocked()) {
				turnLeft();
			}
			move();
		}
		turnAround();
	}
}
