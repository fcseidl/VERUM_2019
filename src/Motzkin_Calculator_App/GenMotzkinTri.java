package Motzkin_Calculator_App;
/**
 * This class computes and prints the contents of a generalized (colored, higher-order) 
 * Motzkin triangle.
 * 
 * @author Frank Seidl
 *
 */
public class GenMotzkinTri extends SimpleRiordanArray {

	/**
	 * Construct (x,y)-colored generalized Motzkin Triangle.
	 * 
	 * @param x
	 * @param y
	 */
	public GenMotzkinTri(int[] x, int[] y) {
		super(1, y, x);		// A and Z sequences must be modified after this
		
		// x and y must be same length
		if(x.length != y.length) {
			throw new IllegalArgumentException("x and y vectors must have same length.");
		}
		
		// Set Z-sequence
		Z = new int[x.length + 1];
		for(int i = 0; i < x.length; i++) {
			Z[i] = x[i];
		}
		Z[x.length] = 1;
		
		// Set A-sequence
		A = new int[y.length + 2];
		A[0] = 1;
		for(int i = 0; i < y.length; i++) {
			A[i+1] = y[i];
		}
		A[y.length+1] = 1;
	}
	
	// other methods can be found in superclass SimpleRiordanArray
}