import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {
	public void run() {
		repair();
		if(frontIsClear()) {
			quatro();
		}
	}
	
	private void repair() {
		turnLeft();
		while(frontIsClear()){
			if(beepersPresent()) {
				move();
			}else {
				putBeeper();
			}
		}
		if(noBeepersPresent()) {
			putBeeper();
		}
		turnAround();
		while(frontIsClear()) {
			move();
		}
		turnLeft();
	}
	
	private void quatro() {
		for (int i = 0; i<4; i++) {
			move();
		}
		run();
	}
}
