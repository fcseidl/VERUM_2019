package Motzkin_Calculator_App;
/**
 * Pascal's Triangle implemented as a proper Riordan Array.
 * 
 * @author Frank Seidl
 *
 */
public class PascalsTri extends SimpleRiordanArray {

	private static int A[] = {1,1};
	private static int Z[] = {1};
	
	public PascalsTri() {
		super(1, A, Z);
	}
	
	public long binom(int n, int k) {
		return getEntry(n,k);
	}
	
	public void printBinomTransform(int seq[]) {
		for (int i = 0; i < seq.length; i++) {
			int newTerm = 0;
			for (int j = 0; j <= i; j++) {
				newTerm += binom(i,j) * seq[j];
			}
			System.out.print(newTerm + ",");
		}
	}
	
	public void printInverseBinomTransform(int seq[]) {
		for (int i = 0; i < seq.length; i++) {
			int newTerm = 0;
			for (int j = 0; j <= i; j++) {
				newTerm += Math.pow(-1, i-j) * binom(i,j) * seq[j];
			}
			System.out.print(newTerm + ",");
		}
	}
}
