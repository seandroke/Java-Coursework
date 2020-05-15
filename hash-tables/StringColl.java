//Name: Sean Droke

//*********************************************************************
// FILE NAME    : StringColl.java
// DESCRIPTION  : This file contains the class StringColl.
//*********************************************************************

package assignment6;

import java.util.*;

public class StringColl {
    private btNode c; //initialize c of type bt node
    private int howmany; // initialize a counter howmany

    // Creates an empty collection, empty parameter
    public StringColl() {
        c = null; //sets btnode c to be a null set
        howmany = 0; //set int howmany to 0
    }

    // Creates an empty collection of integers with a parameter - capactity i
    public StringColl(int i) {
        c = null;
        howmany = 0;
    }

    // Creates a copy of a collection where elements are not equal. Takes in collection parameter obj of type StringColl
    public void copy(StringColl obj) {
        if (this != obj) { //ensures elements are not the same
            howmany = obj.howmany; //sets howmany equal to the size of collection obj.
            c = copytree(obj.c); //uses the copytree function on obj.c
        }
    }

    // An extension of the copy method. Parameter intake t of type btnode
    private static btNode copytree(btNode t) {
        btNode root = null; //the root is set to null
        if (t != null) { //while the tree node is not empty...
            root = new btNode(); //root takes on a new node
            root.info = t.info; //new root becomes that of t
            root.left = copytree(t.left); //left of the root is t.left
            root.right = copytree(t.right); //right of the root is t.right
        }
        return root; //returns the new root
    }

    // boolean functioning determining if parameter string i is in the collection
    public boolean belongs(String i) {
        btNode p = c; //sets a temporary btnode p equal to c
        while ((p != null) && !(p.info.equals(i))) { //while p isn't empty and p's contents aren't the same as string i
            if (p.info.compareTo(i) > 0) { //compares unicode values of p with string i. If equal, than 0. Otherwise continue
                p = p.left; //assign p to the left value
            } else {
                p = p.right; //if unicode values the same, assign p to the right value
            }
        }
        return (p != null); //return if p has contents, or hence i belongs
    }    

    // insert function, taking in the parameter string i
    public void insert(String i) {
        btNode pred = null, p = c; //sets a new btnode value pred to null, and p to c

        while ((p != null) && !(p.info.equals(i))) { //while p isn't empty and p's contents contain i
            pred = p; //pred is set to p
            if (p.info.compareTo(i) > 0) p = p.left; //compares unicode values of p with string i. If equal, than 0. Otherwise p set to left node
            else p = p.right; //if equal values than p set to the right node
        }
        if (p == null) { //instance than p is an empty set
            howmany++; //increment howmany
            p = new btNode(i, null, null); //creates a new node with no left or right children
            if (pred != null) { //if pred never received a value either
                if (pred.info.compareTo(i) > 0) pred.left = p; //unicode value comparison of pred and i, if not equal than the left node of pred is p
                else pred.right = p; //otherwise the right node of pred is p
            } else c = p; //else when p has contents, set them to replace c
        }
    }

    //Omit function with parameter intake of string i to remove from a collection
    public void omit(String i) {
        btNode p = c, pred = null; //temporary p takes the values of c and pred is initialized a null set
        while ((p != null) && !(p.info.equals(i))) { //while p isn't null and contains i
            pred = p; //pred takes the value of p
            if (p.info.compareTo(i) > 0) p = p.left; //if unicode values of p are not the same as i, p takes on the left value
            else p = p.right; //else p will take on the right value
        }
        if (p != null) { // if p has contents
            if (pred == null) { //but pred does not have contents
                if (p.left != null) { //amd the left child of p has contents
                    btNode last = p; //new node last is set to p
                    pred = p.left; //pred takes on the left side of p
                    p = p.left.right; //p maintains the left and right side
                    while (p != null) { //while loop ensuring p is not empty and iterating through tree
                        last = pred; //last becomes pred 
                        pred = p; //set pred equal to p
                        p = p.right; //set p to the right side
                    } 
                    c.info = pred.info; //c's contents become pred's contents
                    if (last == c) c.left = pred.left; //if last is equivalent to c than the left side of c is the left side of pred
                    else last.right = pred.left; //otherwise the right side of last is the left side of pred
                } else {
                    c = c.right; //otherwise c becomes its right node
                }
            } else {
                if (p.left != null) { //when p isn't null and the left side isn't null
                    if(p.right != null) { //the right side also is not null
                        btNode d = p, last = p; //new btnode d is set to p, last is also initialized as p
                        pred = d.left; //pred takes on the left value of d
                        d = d.left.right; //d retains its left and right sides
                        while (d != null) { // while d isn't null
                            last = pred; //last becomes pred
                            pred = d; //pred becomes d
                            d = d.right; //and d moves to the right
                        }
                        p.info = pred.info; //p's contents become pred's
                        if (last == p) last.left = pred.left; //if last is equal to p than left of last becomes left of pred
                        else last.right = pred.left; //else the right of last becomes the left of pred
                    } else {
                        if (pred.info.compareTo(p.info) > 0) pred.left = p.left; //if pred values not the same as p's, left of pred replaced with left of p
                        else pred.right = p.left; //otherwise the right of pred replaced with the left of p
                    }
                } else {
                    if (pred.info.compareTo(p.info) > 0) pred.left = p.right; //else if not the same, pred left becomes p right
                    else pred.right = p.right; //else pred right becomes p right
                }
            }
            howmany--; //decrement howmany for the removal
        }
    }
    
    // returns the number of integers stored in collection
    public int get_howmany() {
        return howmany;
    }
    
    // prints the members of collection
    public void print() {
        printtree(c);
    }

    // An extension of the print method.
    private static void printtree(btNode t) {
        if (t != null) {
            printtree(t.left);
            System.out.println(t.info);
            printtree(t.right);
        }
    }
    
    // returns true if collection contains every value in that of obj.
    public boolean equals(StringColl obj) {
        int j = 0; boolean result = (howmany == obj.howmany); //initialize int j, determine if the number of contents is the same
        if (result) { //if holds
            String[] a = new String[howmany]; //string a retains a new string with the number howmany
            String[] b = new String[howmany]; //same with b
            toarray(c, a, 0); //commits to an arra c, a, and the number 0
            toarray(obj.c, b, 0); //sends toarray c in obj, b, and the number 0

            j = 0; //j remains at 0
            while ((result) && (j < howmany)) { //while result holds and j is a number less than howmany
                result = (a[j].equals(b[j])); j++; //result returns if a contains j equals b contains j, increment j
            }
        }
        return result; //return the result
    }

    // toarray, created to place objects into an array taking paramters btNode t, a string 'a', and an integer i
    private static int toarray(btNode t, String[] a, int i) {
        int num_nodes = 0; //number of nodes initialized to zero
        if (t != null) { //if t is not empty
            num_nodes = toarray(t.left, a, i); //numnodes becomes object of type toarray passing the left node of t, a, and i
            a[num_nodes + i] = t.info; //a at position num_nodes + i is set equal to the contents of t
            num_nodes = num_nodes + 1 + toarray(t.right, a, num_nodes + i + 1); //num nodes incremented
        }
        return num_nodes; //return num_nodes
    }

    // This is an inner class for a binary tree
    private static class btNode {
        private String info;
        private btNode left;
        private btNode right;

        private btNode(String s, btNode lt, btNode rt) {
            info = s; left = lt; right = rt;
        }

        private btNode() {
            info = null; left = null; right = null;
        }
    }
}