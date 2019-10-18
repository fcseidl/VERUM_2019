package Motzkin_Calculator_App;
/**
 * A 2D array whose (i,j)-entry can be computed recursively in terms of previous entries.
 * Memoization is used to speed up frequently performed calculations.
 * 
 * @author Frank Seidl
 *
 */
public abstract class RecursiveArray {
	
	protected DynamicMatrix2D arr = new DynamicMatrix2D();		
		// stores memoized entries of array. Dynamically resizeable.
	
	/**
	 * This method returns an entry of this recursive array. Memoized entries will be 
	 * returned automatically. Entries which are not yet stored will be computed and 
	 * memoized before they are returned. The since it is implemented as a DynamicMatrix2D
	 * object, the array will automatically resize if necessary.
	 * 
	 * @param i
	 * @param j
	 * @return (i,j)-entry of this array
	 */
	public long getEntry(int i, int j) {
		if(!alreadyMemoized(i, j))			// compute and memoize entry if this has not been done already
			arr.set(i, j, computeEntry(i,j));
		return arr.get(i, j);
	}
	
	/**
	 * @param i
	 * @param j
	 * @return whether the (i,j)-entry of this array has been memoized yet.
	 */
	 protected abstract boolean alreadyMemoized(int i, int j);
	 
	 
	/**
	 * @param i
	 * @param j
	 * @return the (i,j)-entry of this array, computed using the recursion relation.
	 */
	 protected abstract long computeEntry(int i, int j);
}

