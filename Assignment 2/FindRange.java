import acm.program.*;

public class FindRange extends ConsoleProgram {
	public static final int SENTINEL = 0;
	
	public void run() {
		println("This program finds the largest and smallest numbers.");
		
		int i = readInt();
		
		int smallest = i;
		int largest = i;
		
		if (i == 0) {
			println("SENTINEL DETECTED, ABORTING...");
		}else {
			while (i != SENTINEL) {
				if (i > largest) {
					largest = i;
				}
				if (i < smallest) {
					smallest = i;
				}
				i = readInt();
			}
			println("smallest: " + smallest);
			println("largest: " + largest);			
		}
	}
}

