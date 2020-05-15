/* Name: Sean Droke
 * CIS 2168 - 008
 * Assignment 1
 * January 29, 2018
 
 * Description: C array is now a boolean value
 
 */ 

package intcoll3;

public class Intcoll3 {
	private boolean[] c;
	private int howmany;

	/* Constructor without a parameter. Collection size of 500.
	 * Initalizes array as false.
	 */
	public Intcoll3() {
		c = new boolean[2+1];
		for (int i = 0; i < c.length; i++) c[i] = false;
		this.howmany = 0;
	}

	/* Constructor with parameter int for size of collection.
	* Initalizes the array as false.
	 */
	public Intcoll3(int i) {
		this.c = new boolean[i];
		for (int j = 0; j < c.length; j++) c[j] = false;
		this.howmany = 0;
	}

	/* Duplicates lists of elements, same in both
	 * No return, does nothing if trying to copy to ourselves.
	 */
	public void copy(Intcoll3 obj) {
		if (this != obj) { 
			c = new boolean[obj.c.length];
			
			for (int i=0; i < c.length; i++)
				c[i] = obj.c[i];
			
			this.howmany = obj.howmany;
		}
	}

	/* Returns true only if parameter int is present in the collection.
	 */
	public boolean belongs(int i) {
		if (i > 0 && i < c.length)
			return c[i];
		return false;
	}

	/* Adds an int to the collection so long as it exists.
	 * Expand the array to i+1 if space runs out
	 * No return.
	 */
	public void insert(int i) {
		if (!belongs(i)) {
			if (i >= c.length) { 
				boolean[] temp = new boolean[i+1];
				for (int j=0; j < temp.length; j++)
					if (j < c.length)
						temp[j] = c[j];
					else
						temp[j] = false;
				c = temp;
			}
			
			c[i] = true;
			this.howmany++;
		}
	}

	/* Remove int i from collection if it exists.
	 * If it does not exist, do nothing. No return.
	 */
	public void omit(int i) {
		if (i > 0 && i < c.length && c[i]) {
			c[i] = false;
			this.howmany--;
		}
	}

	/* Returns an int representing the size of the collection.
	 */
	public int get_howmany() {
		return this.howmany;
	}

	/* Print each element of a collection on a new line. No return.
	 */
	public void print() {
		System.out.println();
		for (int j=0; j < c.length; j++)
			if (c[j])
				System.out.println(j);
	}

	/* Return true only if the collections are the same length and content regardless of order
	 */
	public boolean equals(Intcoll3 obj) {
		if (this.howmany != obj.howmany)
			return false;
		for (int i=0; i < c.length; i++) {
			if (c[i] && !obj.belongs(i))
				return false;
		}
		
		return true;
	}
}