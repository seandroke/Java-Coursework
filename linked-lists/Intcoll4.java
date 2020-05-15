// Name: Sean Droke

package intcoll4;

//*********************************************************************
//FILE NAME    : Intcoll4.java
//DESCRIPTION  : This file contains the class Intcoll4.
//*********************************************************************


public class Intcoll4 {
	private ListNode c;
	private int howmany;

	
	public Intcoll4() {
		c = null;
		howmany = 0;
	}

	
	public Intcoll4(int i) {
		this();
	}


	private class ListNode {
		private ListNode link; //creates a link of type ListNode
		private int info; //serves to be the node itself

		
		public ListNode() {
			link = null; //initializes a listnode with no link
			info = 0; //no current infor
		}
	}


	public void copy(Intcoll4 obj) {
		if (this != obj) { // if the collection currently is not the same already
			ListNode newlist; //create a new listnode
			if (obj.howmany == 0) { //size is 0
				newlist = null; //create an empty new list
			} else {
				ListNode cur = obj.c; //new ListNode value cur set to obj.c
				newlist = new ListNode(); // Creates the new list
				newlist.info = cur.info; // Set the info for the first item
				ListNode p = newlist;  // Pointer to follow the newlist as it follows cur
				cur = cur.link; // Move to the next item in obj.c
				while (cur != null) {
					p.link = new ListNode(); // Create the next node
					p = p.link; // link it
					p.info = cur.info; // Set the info
					cur = cur.link;   // Move the current pointer to the next node in obj
				}
			}
			c = newlist;
			howmany = obj.howmany;
		}
	}



	/* Returns true if parameter int is in the collection.

	 */

	public boolean belongs(int i) {
		
		// Walk through the list, checking for info=i
		
		if (i < 0)

			// If negative, return false right away
			
			return false;

		

		ListNode cur = c; //temporary list node created to represent c

		while (cur != null && cur.info != i)

			// Loop exits when we reach the end (null) or find i.
			
			cur = cur.link;

		

		if (cur == null) // We reached the end

			return false;

		else // The end was reached

			return true;

	}



	/* Adds int to the collection or does nothing if it already exists.

	 * No return. */

	public void insert(int i) {

		// If i is not in the collection already - this checks for negatives in belongs()
		
		if (!belongs(i)) {

			ListNode next = new ListNode();

			next.info = i;

			next.link = this.c; // This points the new node's link to the beginning of the existing list

			c = next;

			howmany++;

		}

	}



	/* Removes int i from collection if it exists.

	 * If it does not exist, does nothing. No return. */

	public void omit(int i) {

		if (belongs(i)) {

			ListNode cur = c; //temp listnode for the current collection

			ListNode prev = null; //empty temp listnode

			
			//this does the traversal of the linked list
			while (cur != null && cur.info != i) { //while c has values, and doesn't contain what we are omitting

				// Loop exits when we reach the end (null) or find i.

				prev = cur; //set prev to c

				cur = cur.link; //set cur to the link value

			}

			
			
			if (cur != null) { 
				
				// We found i; don't do (cur.info == i) to avoid a possible null pointer dereference

				if (prev == null) {
					
					// If i is the first Node in the list,

					// set c to the next item in the list (it's link).

					c = cur.link; 

				} else {
					
					// Link the previous node with the next node (thus skipping over the current node).

					prev.link = cur.link;

				}

				this.howmany--; //decrement howmany for omittance

			}

		}

	}



	// Returns an int representing size of the collection. 

	public int get_howmany() {

		return this.howmany;

	}



	/* Print each element of a collection on a new line. No return.

	 */

	public void print() {

		ListNode cur = c;

		while (cur != null) {

			System.out.println(cur.info);

			cur = cur.link;

		}

	}



	/* Return true only if the collections are the same length and contain

	 * the same elements.

	 */

	public boolean equals(Intcoll4 obj) {

		// Not equal if different number of elements
		
		boolean result = (this.howmany == obj.howmany);
		
		// Not equal if any of the elements are missing

				// Since both collections have the same number, we only need

				// to walk through one list (not both).

		ListNode cur = this.c;

		while (cur != null && result) {

			result = obj.belongs(cur.info);

			cur = cur.link;

		}

		

		return result;

	}

}