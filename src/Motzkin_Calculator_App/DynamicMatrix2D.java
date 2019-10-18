package Motzkin_Calculator_App;
/**
 * A dynamically resizeble 2D array class largely written by StackOverflow user WhiteFang34.
 * 
 * @author WhiteFang34
 * @modified Frank Seidl: appropriated to act as underlying data structure in RecursiveArray class. 
 * Changed matrix from int[][] to long[][]. Removed main method. Added minimal documentation.
 *
 */
public class DynamicMatrix2D {
	private long[][] matrix = new long[5][5];

	/**
	 * Assign a value to the (x,y)-entry of the matrix, resizing only as necessary.
	 * @param x
	 * @param y
	 * @param value
	 */
    public void set(int x, int y, long value) {
        if (x >= matrix.length) {
            long[][] tmp = matrix;
            matrix = new long[x + 1][];
            System.arraycopy(tmp, 0, matrix, 0, tmp.length);
            for (int i = x; i < x + 1; i++) {
                matrix[i] = new long[y];
            }
        }

        if (y >= matrix[x].length) {
            long[] tmp = matrix[x];
            matrix[x] = new long[y + 1];
            System.arraycopy(tmp, 0, matrix[x], 0, tmp.length);
        }

        matrix[x][y] = value;
    }

    /**
     * @param x
     * @param y
     * @return the (x,y)-entry of the matrix
     */
    public long get(int x, int y) {
    	if(x < 0 || y < 0)
    		return 0;
        return x >= matrix.length || y >= matrix[x].length ? 0 : matrix[x][y];
    }
    
    /*
     * TEST MAIN
     *
    public static void main(String[] args) {
        DynamicMatrix2D matrix2d = new DynamicMatrix2D();

        matrix2d.set(1, 1, 1);     // set (1, 1) to 1
        matrix2d.set(10, 10, 2);   // set (10, 10) to 2
        matrix2d.set(100, 100, 3); // set (100, 100) to 3

        System.out.println(matrix2d.get(1, 1));     // outputs 1
        System.out.println(matrix2d.get(10, 10));   // outputs 2
        System.out.println(matrix2d.get(100, 100)); // outputs 3 
    }
    */
}
