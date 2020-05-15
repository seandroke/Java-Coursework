// Name: Sean Droke

package intcoll5;

//*********************************************************************
//FILE NAME    : Intcoll5.java
//DESCRIPTION  : This file contains the class Intcoll5.
//*********************************************************************

import java.util.LinkedList;
import java.util.ListIterator;

public class Intcoll5 {
	private LinkedList<Integer> c;

	// Constructor without parameter.
	
	public Intcoll5() {
		c = new LinkedList<>();
	}

	/* Constructor with parameter. Same thing is done reagrdless of parameter.
	* LinkedList is dynamic */
	
	public Intcoll5(int i) {
		this();
	}

	/* Duplicate argument list. All the elements will be the same.
	 * No return. Does nothing if trying to copy to ourselves. */
	
	public void copy(Intcoll5 obj) {
		if (this != obj) { // Prevent P.copy(P)
			// Create a temp list
			LinkedList<Integer> newlist = new LinkedList<>();
			// Iterate through the argument list, setting the corresponding elements
			ListIterator<Integer> iter = obj.c.listIterator();
			while (iter.hasNext()) {
				newlist.add(iter.next());
			}
			// Set this list to newlist.
			c = newlist;
		}
	}

	// Returns true if parameter int is in the collection.
	
	public boolean belongs(int i) {
		// Walk through the list, checking for info=i
		if (i < 0)
			// If negative, return false right away
			return false;
		
		return this.c.contains(i);
	}

	/* Adds int to the collection or does nothing if it already exists.
	 * No return. */
	
	public void insert(int i) {
		// If i is not in the collection already - this checks for negatives in belongs()
	      Integer I=new Integer(i);
	      if ((i>0)&&(!c.contains(i)))  {  c.addFirst(I); 
		}
	}

	/* Removes int i from collection if it exists.
	 * If it does not exist, does nothing. No return. */
	
	public void omit(int i) {
		this.c.remove(new Integer(i));
	}

	// Returns an int representing size of the collection.
	
	public int get_howmany() {
		return this.c.size();
	}

	// Print each element of a collection on a new line. No return.
	
	public void print() {
		ListIterator<Integer> iter = this.c.listIterator();
		while (iter.hasNext()) {
			System.out.println(iter.next().intValue());
		}
	}

	/* Return true only if the collections are the same length and contain
	 * the same elements.*/
	
	public boolean equals(Intcoll5 obj) {
		// Not equal if different number of elements
		boolean result = (this.get_howmany() == obj.get_howmany());
		// Not equal if any of the elements are missing
		/* Since both collections have the same number, we only need to walk 
		 * through one list (not both).
		 */
		ListIterator<Integer> iter = obj.c.listIterator();
		while (iter.hasNext() && result) {
			// Set result false if one isn't in the other
			result = this.belongs(iter.next());
		}
		
		return result;
	}
}