import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		println("Enter values to compute Pythagorean theorem.");
		int a = readInt("a: ");
		int b = readInt("b: ");
		a = a*a;
		b = b*b;
		double c = Math.sqrt(a+b);
		println(c);
	}
}
