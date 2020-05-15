// Sean Droke

package intcoll6;

//*********************************************************************
//FILE NAME    : Intcoll6.java
//DESCRIPTION  : This file contains the class Intcoll6.
//*********************************************************************

import java.util.*;

public class Intcoll6 {
    private btNode c;
    private int howmany;

    // Creates an empty collection of integers size 500
    public Intcoll6() {
        c = null;
        howmany = 0;
    }

    // Creates an empty collection of integers with a capacity of i
    // parameter representing the capacity of the collection, i >= 0
    public Intcoll6(int i) {
        c = null;
        howmany = 0;
    }

    // Overwrites collection with a copy of the contents in the collection
    // of obj when both aren't equal. Both collections are same size and capacity
    // paramater obj  serves as the reference to the collection
    public void copy(Intcoll6 obj) {
        if (this != obj) {
            howmany = obj.howmany;
            c = copytree(obj.c);
        }
    }

    // A copy method to work with the binary algorithms
    private static btNode copytree(btNode t) {
        btNode root = null;
        if (t != null) {
            root = new btNode();
            root.info = t.info;
            root.left = copytree(t.left);
            root.right = copytree(t.right);
        }
        return root;
    }

    // Returns true if integer is in collection, else returns false
    // paramater i is the integer being tested to belong in collection
    // return will be true or false depending on if i in collection
    public boolean belongs(int i) {
        btNode p = c;
        while ((p != null) && (p.info != i)) {
            if (p.info > i) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return (p != null);
    }    

    // Add an int to collection. If collection full, double the size.
    // paramater i is the integer to be added, i > 0
    public void insert(int i) {
        btNode pred = null, p = c; //Establishes a temp variable of class btNode

        while ((p != null) && (p.info != i)) { //Ensures p is not empty and is not the value to be inserted
            pred = p; //temp \variable becomes p
            if (p.info > i) p = p.left; //if node value p is greater than i, move left
            else p = p.right; //else move right
        }
        if (p == null) { //case if p is an empty node
            howmany++;
            p = new btNode(i, null, null); //creates a new btNode with null left and right children
            if (pred != null) { //Ensures p or current node is not a null set
                if (pred.info > i) pred.left = p; //Acts similarly to that above
                else pred.right = p;
            } else c = p; //Else c and p are thus the same
        }
    }

    // Removes number if it belongs to collection
    // paramater i is the integer to be removed, i > 0
 // If collection contains i, i will be omitted
    public void omit(int i)
    {
        btNode p = c; //point to the root node
        btNode oneUp = null; //blank node
        while(p!=null && p.info!=i) {
            oneUp = p;
            if(p.info < i)
                p = p.right;
            else
                p = p.left;
        }
        /* p should now point to the node representing i
           prev should be pointing to the root node of i
        */

        if(p != null) { //assuming that i is in the collection
            btNode q = p; //q points to p (node containing i)
            if(p.right == null) //if there is no right-branch
                q = p.left; //q points to to the left branch of the i-node
            else if(p.left == null) //if there is no left-branch
                q = p.right; //q points to the i-right-branch
            else { //if both branches have children
                btNode j = p.left; //j points to the left of i
                if(j.right == null) { //check if the i-left has a right branch
                    q = j; //if so, q (i-node) points to j (i-left-node)
                    q.right = p.right; //i-right-node points to the i-right-node
                  //ie. the left-node shifts upwards
                } else { //if j.r is not null
                    while(j.right.right != null) //finds the last right-branch
                        j = j.right; 
                    //j will land at the second to last right-branch
                    q = j.right; //q is the last right-node
                    j.right = q.left;
                    q.right = p.right; 
                    q.left = p.left;
                    /* This shifts the remaining nodes around */
                }
            }

            if(oneUp == null) //Only true if the root node is i
                c = q; //c points to q based on above logic
            else if(oneUp.right == p) //if the i-node was a right-branch
                oneUp.right = q; //point prev.r to q from above logic
            else //prev.l was the i-node
                oneUp.left = q; //point the left-node to q from above 

            howmany--; //decrement how_many
        }
    }
     
    
    // returns the size of the collection
    public int get_howmany() {
        return howmany;
    }
    
    // prints the collection
    public void print() {
        printtree(c);
    }

    // Print method applied to the binary concepts
    private static void printtree(btNode t) {
        if (t != null) {
            printtree(t.left);
            System.out.println(t.info);
            printtree(t.right);
        }
    }
    
    // returns true if collections are the same, else false
    // paramater obj is the reference to a collection
    // returns true if collections are equal, else false
    public boolean equals(Intcoll6 obj) {
        int j = 0; boolean result = (howmany == obj.howmany);
        if (result) {
            int[] a = new int[howmany];
            int[] b = new int[howmany];
            toarray(c, a, 0);
            toarray(obj.c, b, 0);

            j = 0;
            while ((result) && (j < howmany)) {
                result = (a[j] == b[j]); j++;
            }
        }
        return result;
    }

    // Equals method applied to binary concepts
    private static int toarray(btNode t, int[] a, int i) {
        int num_nodes = 0;
        if (t != null) {
            num_nodes = toarray(t.left, a, i);
            a[num_nodes + i] = t.info;
            num_nodes = num_nodes + 1 + toarray(t.right, a, num_nodes + i + 1);
        }
        return num_nodes;
    }
    

    // The inner class for a binary tree
    private static class btNode {
        private int info;
        private btNode left;
        private btNode right;

        private btNode(int s, btNode lt, btNode rt) {
            info = s; left = lt; right = rt;
        }

        private btNode() {
            info = 0; left = null; right = null;
        }
    }
}
