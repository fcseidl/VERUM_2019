package Motzkin_Calculator_App;
/**
 * This class implements a proper Riordan array whose A- and Z-sequences are polynomials.
 * (That is, they have only finitely many nonzero terms.)
 * 
 * @author Frank Seidl
 *
 */
public class SimpleRiordanArray extends RecursiveArray {

	protected int A[], Z[];			// A and Z sequences
	protected int lastComputedDiag;	// useful for making dynamic computation efficient
	
	/**
	 * Construct a proper Riordan Array with finite A- and Z-sequences.
	 * 
	 * @param firstEntry
	 * @param Aseq
	 * @param Zseq
	 */
	public SimpleRiordanArray(long firstEntry, int Aseq[], int Zseq[]) {
		super();
		arr.set(0, 0, firstEntry);
		lastComputedDiag = 0;
		A = Aseq;
		Z = Zseq;
	}
	
	/**
	 * Determine whether or not (i,j)-entry is already memoized. Possibility of false negatives 
	 * causes redundant computations to occur. This inefficiency is worst if the Z-sequence is 
	 * a much shorter polynomial than the A-sequence.
	 */
	protected boolean alreadyMemoized(int i, int j) {
		if (arr.get(i,j) != 0 || j > i)	// if entry has been modified or if coordinates are outside triangle
			return true;
		if (i > lastComputedDiag)	// if below last computed antidiagonal then definitely not yet computed
			return false;
		
		// last-resort check which diagonal this entry is in
		int slope = Math.min(A.length - 2, Z.length - 1);
		return slope*i + j <= slope*lastComputedDiag;
	}

	/**
	 * This method should only be called if the (i,j)-entry of this Riordan array is not already stored.
	 * @param i
	 * @param j
	 * @return the (i,j)-entry of this array, computed using the recursion relation.
	 */
	protected long computeEntry(int i, int j) {
		long sum = 0;		// field to be returned

		if (j == 0) {		// use Z-sequence
			for (int a = 0; a < Z.length; a++) {
				sum += Z[a] * getEntry(i-1, j+a);
			}
		}
		else {				// use A-sequence
			for (int a = 0; a < A.length; a++) {
				sum += A[a] * getEntry(i-1, j-1+a);
			}
		}
		
		// memoize entry
		arr.set(i, j, sum);
		if(j==0)
			lastComputedDiag = i;
		return sum;
	}
	
	/*
	 * Getters
	 */
	
	public int[] getA() {
		return A;
	}

	public int[] getZ() {
		return Z;
	}

	/*
	 * Printing methods
	 */
	
	/**
	 * Print the first rows of this triangle in a readable format. (Unless numbers are 
	 * too large!)
	 * 
	 * @param rows
	 */
	public void printTriangle(int rows) {
		for (int n = 0; n < rows; n++) {
			for (int k = 0; k <= n; k++) {
				System.out.print(getEntry(n,k) + "\t");
			}
			System.out.println();
		}
	}
	
	/**
	 * Print the first N entries of main column in OEIS-readable format
	 * 
	 * @param R
	 */
	public void printMainCol(int N) {
		for (int n = 0; n < N; n++) {
			System.out.print(getEntry(n,0));
			if(n != N-1)
				System.out.print(",");
		}
		System.out.println();
	}
	
	/**
	 * Print first rows row sums in OEIS-readable format
	 * 
	 * @param rows
	 */
	public void printRowSums(int rows) {
		for (int n = 0; n < rows; n++) {
			int sum = 0;
			for (int k = 0; k <= n; k++) {
				sum += getEntry(n,k);
			}
			System.out.print(sum + ",");
		}
		System.out.println();
	}
}
