//Name: Sean Droke

//*********************************************************************
// FILE NAME    : MultiStringcoll.java
// DESCRIPTION  : This file contains the class MultiStringColl
//*********************************************************************

package assignment6;

import java.util.*;
public class MultiStringColl {
    private btNode c;
    private int howmany;
    private int total;
    // Constructor which creates an empty collection with the capacity for 500 integers.
    public MultiStringColl() {
        c = null;
        howmany = 0;
        total = 0;
    }
    // Constructor which accepts input parameter integer i, and creates an empty collection with capacity for int i integers.
    public MultiStringColl(int i) {
        c = null;
        howmany = 0;
        total = 0;
    }
    // copy function for the program that calls upon copytree to duplicate collections
    public void copy(MultiStringColl obj) {
        if(!(this.equals(obj))){
            howmany=obj.howmany;
            total = obj.total;
            c = copytree(obj.c);
        }
    }
    // Returns true if the input parameter string i exists in the collection
    public int belongs(String i) {
        btNode p = c;
        while((p!=null)&&(i.compareTo(p.info)!=0)){
            if(i.compareTo(p.info)<0){
                p = p.left;
            }else{
                p = p.right;
            }
        }
        if(p!=null){
            return p.count;
        }else{
            return 0;
        }


    }
    // Insert function for parameter string i, to add strings to a collection
    public void insert(String i) {
        btNode pred = null;
        btNode p = c;
        if(p!=null){
            while((p!=null)&&(p.info.compareTo(i)!=0)){
                pred = p;
                if(i.compareTo(p.info)<0){
                    p = p.left;
                }else{
                    p = p.right;
                }
            }
        }

        if(p!=null){
            p.count++;
            total++;
        }
        else if(p==null){
            howmany++;
            total++;
            p = new btNode(i, null, null, 1);
            if(pred!=null){
                if(i.compareTo(pred.info)<0){
                    pred.left=p;
                } else{
                    pred.right=p;
                }
            } else{
                c = p;
            }
        }

    }

    // omit function for removing a string from the collection, takes in parameter string i to be removed
	public void omit(String i) {
		if (c == null) return; //check for an empty collection
		
		btNode parent = null; // temporary obj of type btnode to retain parent nodes
		btNode p = c; // traversal pointer
		
		while (!p.info.equals(i)) { 		// Traverse until the end of string i is met
			parent = p;
			
			if (p.info.compareTo(i) > 0)
				p = p.left;
			else
				p = p.right;
		} // p then will point to the node that contains the string i
		
		if (p.count > 1) { 		// Case for multiple occurances, decrement the count, and decrement the total number of strings in collection.
			p.count--;
			total--;;
			return;
		}
		
		if(p.count == 1) { //decrements distinct integers when they occur multiple times
			howmany--;
		}

		btNode pbelow = p; // temporary obj of type btnode representing the node below p

		if (p.right == null) { // No right sibling
			pbelow = p.left; //pbelow takes on the left child
			
		} else if (p.left == null) { // No left sibling
			pbelow = p.right; // pbelow takes on the right child
			
		} else { // Both siblings

			btNode pbelow2 = p.left; // takes on the other node below p

			if (pbelow2.right == null) {
				// Move one of p's child under the other child
				pbelow = pbelow2;
				pbelow.right = p.right;
				
			} else {

				while (pbelow2.right.right != null) {
					pbelow2 = pbelow2.right;
				}

				pbelow = pbelow2.right;
				pbelow2.right = pbelow.left;
				pbelow.right = p.right;
				pbelow.left = p.left;
			}
		}

		if (parent == null) {
			c = pbelow; // pbelow will be null if there's no right sibling.
			
		} else if (parent.right == p) {
			parent.right = pbelow; // "skip over" p from parent to pbelow
			
		} else {
			parent.left = pbelow; // Will be null if removing leaf
		}
		if(p.count > 1) { // Case when something appears more than once, it is then not a distinct string, decrement howmany
			howmany--;
		}
		
	total--;
	}
	
    // Returns how many distinct strings are in the collection.
    public int get_howmany() {
        return howmany;
    }
    // Returns the total number of strings in the collection.
    public int get_total(){
        return total;
    }


    // Prints the values in the collections
    public void print() {
        printtree(c);
    }
    // Returns true if collections are entirely the same
    public boolean equals(MultiStringColl obj) {
        if(howmany!=obj.howmany){
            return false;
        }
        else{
            btNode[] thisArray = new btNode[howmany];
            btNode[] objThisArray = new btNode[obj.howmany];
            toarray(c, thisArray, 0);
            toarray(obj.c, objThisArray, 0);
            for(int i = 0; i<thisArray.length; i++){
                if((thisArray[i].info.compareTo(objThisArray[i].info)!=0) || (thisArray[i].count!=objThisArray[i].count)){
                    return false;
                }
            }

        }
        return true;
    }

    // generic btnode class to create the various nodes of the tree
    
    private static class btNode{
        private String info;
        private btNode left;
        private btNode right;
        private int count;
        public btNode(){
            info = null;
            left = null;
            right = null;
            count = 0;
        }
        public btNode(String i, btNode lt, btNode rt, int ct){
            info = i;
            left = lt;
            right = rt;
            count = ct; 
        }
    }
    
    //copy function adapted to copy all aspects of the binary tree
    private static btNode copytree(btNode t){
        btNode result = null;
        if(t!=null){
            result = new btNode(t.info, copytree(t.left), copytree(t.right), t.count);
        }
        return result;
    }
    //print function adapted for the binary components
    private static void printtree(btNode t){
        if(t!=null){
            printtree(t.left);
            System.out.println(t.info + "("+t.count+")");
            printtree(t.right);
        }
    }
    //submits necessary data to an array
    private static int toarray(btNode t, btNode[] a, int i){
        int num_nodes = 0;
        if(t!=null){
            num_nodes = toarray(t.left, a, i);
            a[num_nodes+i] = new btNode(t.info, null, null, t.count);
            num_nodes = num_nodes + 1 + toarray(t.right, a, num_nodes+i+1);
        }
        return num_nodes;
    }
}