/* Name: Sean Droke
 * CIS 2168 - 008
 * Assignment 1
 * January 29, 2018
 
 * Description: Utilize howmany instead of 0
 
 */ 

package intcoll2;

public class Intcoll2 {
	private int[] c;
	private int howmany;

	/* Constructor without a parameter. Collection size of 500.
	 */
	public Intcoll2() {
		c = new int[2+1];
		this.howmany = 0;
	}

	/* Constructor with parameter int for size of collection
	 */
	public Intcoll2(int i) {
		c = new int[i];
		this.howmany = 0;
	}

	/* Duplicates lists of elements, same in both
	 * No return, does nothing if trying to copy to ourselves.
	 */
	public void copy(Intcoll2 obj) {
		if (this != obj) { 
			c = new int[obj.c.length];
			
			int j = 0;
			while (j < obj.howmany) {
				c[j] = obj.c[j];
				j++;
			}
			
			this.howmany = obj.howmany;
		}
	}

	/* Returns true only if parameter int is in the collection.
	 */
	public boolean belongs(int i) {
		int j = 0;
		while (j < this.howmany-1 && c[j] != i)
			j++;
		return (i > 0 && c[j] == i);
	}

	/* Adds an int to the collection so long as it exists.
	 * Double the array size if space runs out
	 * No return.
	 */
	public void insert(int i) {
		if (i > 0 && !belongs(i)) {
			
			if (this.howmany == c.length) { 
				int[] temp = new int[c.length*2];
				for (int j=0; j < this.howmany; j++)
					temp[j] = c[j];
				c = temp; 
			}
			
			this.c[this.howmany] = i;
			this.howmany++;
		}
	}

	/* Remove int i from collection if it exists.
	 * If it does not exist, do nothing. No return.
	 */
	public void omit(int i) {
		if (i > 0) {
			int j = 0;
			
			while (j < this.howmany && c[j] != i)
				j++;
			
			if (c[j] == i) {
				this.howmany--;
				c[j] = c[this.howmany];
			}
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
		for (int j=0; j < this.howmany; j++)
			System.out.println(this.c[j]);
	}

	/* Return true only if the collections are the same in length and elements regardless of order.
	 */
	public boolean equals(Intcoll2 obj) {
		if (this.howmany != obj.howmany)
			return false;

		for (int i=0; i < this.howmany; i++) {
			if (!obj.belongs(c[i]))
				return false;
		}
		
		return true;
	}
}