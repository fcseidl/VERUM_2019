package Motzkin_Calculator_App;

import java.util.*;
/**
 * Driver for generalized Motzkin number calculation. Offers user-friendly navigation menu.
 * 
 * @author Frank Seidl, 2019
 *
 */
public class MotzkinApp {
	
	private static Scanner input = new Scanner(System.in);
	private static PascalsTri pascal = new PascalsTri();
	
	// canned prompts to user
	private final static String NUM_MENU = "Type the number next to the action you'd like to take.";
	private final static String WRONG_NUM = "Oops, please enter a number corresponding to a menu item.";
	private final static String MAIN_MENU = "Returning to main menu...";

	
	/**
	 * Call this method if user wants to compute generalized Motzkin numbers.
	 */
	private static void genMotzkin() {
		int m, x[], y[];
		
		// get order and color scheme
		System.out.println("What order of Motzkin numbers would you like to compute?\nm = ");
		m = getNonNegInt();
			
		System.out.println("Enter the components of the x vector in order, pressing ENTER between each.");
		x = new int[m];
		for (int i = 0; i < m; i++) {
			x[i] = getInt();
		}
		
		System.out.println("Now enter the components of the y vector in order.");
		y = new int[m];
		for (int i = 0; i < m; i++) {
		y[i] = getInt();
		}
		// read back input
		System.out.println("MotzkinApp is preparing to compute the generalized Motzkin triangle where");			System.out.print("x = (");
		for (int i = 0; i < m; i++){
			if(i < m - 1) 
				System.out.print(x[i] + ", ");
			else
				System.out.print(x[i] + "),\n");
		}
		System.out.print("y = (");
		for (int i = 0; i < m; i++){
			if(i < m - 1) 
				System.out.print(y[i] + ", ");
			else
				System.out.print(y[i] + ").\n");
		}
			
		// compute
		Riordan(new GenMotzkinTri(x,y));
	}

	
	/**
	 * Gets user-specified A- and Z-sequences.
	 */
	private static void customAZ() {
		// get A
		System.out.println("How many terms does the A-sequence polynomial have?");
		int A[] = new int[getNonNegInt()];
		System.out.println("Enter the coefficients of the A-sequence in order, pressing ENTER between each.");
		for (int i = 0; i < A.length; i++) {
			A[i] = getInt();
		}
			
		// get Z
		System.out.println("How many terms does the Z-sequence polynomial have?");
		int Z[] = new int[getNonNegInt()];
		System.out.println("Now enter the coefficients of the Z-sequence in order.");
		for (int i = 0; i < Z.length; i++) {
			Z[i] = getInt();
		}
			
		// get initial entry
		System.out.println("What is the initial (0,0)-entry of the array?");
		long initEntry = (long)getInt();
			
		// read back inputs
		System.out.println("MotzkinApp is preparing to compute the proper Riordan array with initial "
				+ "(0,0)-entry " + initEntry + ", ");
		System.out.print("A-sequence ");
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i] + ", ");
		}
		System.out.print("and Z-sequence " + Z[0]);
		for (int i = 1; i < Z.length; i++) {
			System.out.print(", " + Z[i]);
		}
			
		// compute
		Riordan(new SimpleRiordanArray(initEntry, A, Z));
	}
	
	
	/**
	 * Call this method when user wants to compute entries of a proper Riordan array.
	 */
	private static void Riordan(SimpleRiordanArray arr) {
		for (;;) {
			// what to do?
			System.out.println(NUM_MENU);
			System.out.println("1. Print main-column sequence of triangle in OEIS-readable form");
			System.out.println("2. Print entire triangle");
			System.out.println("3. Calculate a specific entry");
			System.out.println("4. Print triangle row sums in OEIS-readable form");
			
			switch(input.next()) {
				case "1":	// main column
					System.out.println("How many entries?");
					int num = getInt();
					arr.printMainCol(num);
					break;
				case "2":	// whole triangle
					System.out.println("How many rows?");
					int rows = getInt();
					arr.printTriangle(rows);
					break;
				case "3":	// specific entry
					System.out.println("Enter the row-coordinate:\nn = ");
					int n = getInt();
					System.out.println("Enter the column-coordinate:\nk = ");
					int k = getInt();
					System.out.println("The (" + n + "," + k + ")-entry of the triangle is " + arr.getEntry(n, k));
					break;
				case "4":	// main column
					System.out.println("How many rows?");
					int r = getInt();
					arr.printRowSums(r);
					break;
				default:
					System.out.println(WRONG_NUM);
					continue; // restart
			}
			break;
		}
	}
	

	/**
	 * Calculate binomial transform of user specified sequence
	 */
	private static void binomTransform() {
		int seq[] = getSeq();
		System.out.println("The binomial transform of your sequence is ");
		pascal.printBinomTransform(seq);
	}
	
	
	private static int[] getSeq() {
		int seq[];
		System.out.println("How many elements of the sequence will you input?");
		seq = new int[getNonNegInt()];
		System.out.println("Enter the elements of the sequence in order, pressing ENTER between each.");
		for (int i = 0; i < seq.length; i++) {
			seq[i] = getInt();
		}
		return seq;
	}
	
	/**
	 * @return an integer of the user's specification
	 */
	private static int getInt() {
		int data;
		for (;;) {
			try {
				data = input.nextInt();
				return data;
			}
			catch (InputMismatchException ex) {
				input.next();
			}
			System.out.println("You must input an integer.");
		}
	}
	
	
	/**
	 * @return a nonnegative integer of the user's specification
	 */
	private static int getNonNegInt() {
		int data;
		for (;;) {
			try {
				data = input.nextInt();
				if (data >= 0)
					return data;
			}
			catch (InputMismatchException ex) {
				input.next();
			}
			System.out.println("You must input a nonnegative integer.");
		}
	}


	
	// main
	public static void main(String[] args) {
		
		// welcome
		System.out.println("Welcome to MotzkinApp! \nThis application can be used to compute entries of "
				+ "generalized Motzkin triangles, \nPascal's triange, and in general, any proper Riordan "
				+ "array with polynomial \nA- and Z-sequences."
				+ "\n\nAuthor: Frank Seidl, VERUM 2019\n");
		
		// loop main menu
		for(;;) {
			// what would you like to do?
			System.out.println(NUM_MENU);
			System.out.println("1. Compute generalized Motzkin numbers");
			System.out.println("2. Compute binomial coefficients");
			System.out.println("3. Compute entries of some proper Riordan array with polynomial A- and Z-sequences");
			System.out.println("4. Compute the binomial transform of a sequence");
			System.out.println("5. QUIT");
		
			// parse input
			switch(input.next())
			{
				case "1": 
					genMotzkin();
					break;
				case "2":
					System.out.println("MotzkinApp is preparing to compute Pascal's triangle.");
					Riordan(pascal);
					break;
				case "3":
					customAZ();
					break;
				case "4":
					binomTransform();
					break;
				case "5":
					System.out.println("---MotzkinApp terminated---");
					return;
				default: 
				System.out.println(WRONG_NUM);
			}
			System.out.println("\n" + MAIN_MENU);
		}
		
		
		
	}

}
