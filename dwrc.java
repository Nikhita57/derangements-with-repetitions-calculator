/* Derangements, in math, are permutations in which
 * no element is in its original location. I have found
 * the formula for calculating the number of derangements
 * of any number of elements - except it only works when there
 * are no repeated elements. The formulas for repeated elements
 * are very complex. For now, I will try a brute-force method.
 * Edit: You can also use this site:
 * https://www.bluffton.edu/homepages/facstaff/nesterd/java/rookpolynomials.html
 * Just make sure that after you input the board size, click "derangement," and add
 * the extra black squares for identical letters, you divide it by the factorial of each number
 * of repeated letters, as for a general permutation with repeated elements. I believe this
 * is necessary to give the correct answer. It would be very helpful if someone could prove
 * or disprove this contention mathematically.
 */

import java.util.*;
public class dwrc {
    public static int dcount = 0;   
    
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter a word.");
		System.out.println("One word.");
		//need it to be lowercased because we don't distinguish letters by case
		String dword = reader.next().toLowerCase();
	    char[] darray = dword.toCharArray();
		ArrayList<Integer> df = new ArrayList();
		ArrayList<Character> usedChar = new ArrayList();
		Integer rtimes = 1;
		boolean already = false;
		for (int m = 0; m < darray.length; m++) {
		    for (int n = m+1; n < darray.length; n++)	
		     {
		    	already = false;
		    	for (int p = 0; p < usedChar.size(); p++) {
		    		if (darray[m] == usedChar.get(p)) {
		    			already = true;
		    		}
		    	}
		    	if ((darray[m] == darray[n]) && !already) {
		    	   rtimes++;
		    	}
		     }
		   usedChar.add(darray[m]); 
		   df.add(rtimes);
		   rtimes = 1;
		}
		    char[] darray2 = new char[darray.length];
		    for (int k = 0; k < darray.length; k++) {
		    	darray2[k] = darray[k];
		    }
		    	
		    findPermutations(darray, darray2, 0);
		    for (int q = 0; q < df.size(); q++) {
		    	for (int r = df.get(q); r >= 1; r--) {
		    		System.out.println(r);
		    		dcount /= r;
		    	}
		    }
		    System.out.println("There are " + dcount + " derangements!");
		  
		

	}
	
	/* This is an altered version of code from here:
	 * http://www.bowdoin.edu/~ltoma/teaching/cs107/fall05/Examples/Anagram.java
	 * Just giving credit.
	 */

	public static void findPermutations (char[] darray, char[] darray2, int i) {
		        
				if (i == darray2.length-1) {
					checkDerangement(darray, darray2);
				}
				else {
					for (int j=i; j< darray2.length; j++) {
						//swap a[i] with a[j]
						char c = darray2[i]; 
						darray2[i] = darray2[j]; 
						darray2[j] = c;
						findPermutations(darray, darray2, i+1);
						//swap back
						c = darray2[i]; 
						darray2[i] = darray2[j]; 
						darray2[j] = c;
					}
				}
	}
	
	public static void checkDerangement (char[] darray, char[] darray2) {
		boolean isDerangement = true;
		for (int i = 0; i < darray.length; i++) {
			if (darray[i] == darray2[i]) {
				isDerangement = false;
			}
		}
		if (isDerangement) {
			dcount++;
	    }

    }	
}
