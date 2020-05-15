//*********************************************************************
// FILE NAME    : Intcoll1.java
// DESCRIPTION  : This file contains the class Intcoll1.
//*********************************************************************

package intcoll1;
import java.util.*;

public class Intcoll1
{
   private int[] c; //private integer array c

   //constructor without parameter, creates a c array of size 500, value at position 0 is 0
   public Intcoll1()
   {
      	c = new int[500+1];
      	c[0] = 0;
   }

   // constructor with parameter integer i
   public Intcoll1(int i)
   {
    c = new int[i+1]; //collection size is that of i
    	c[0] = 0; //same initial position
   }

   //copy function with parameter obj of type intcoll1
   public void copy(Intcoll1 obj) 
   {
      	if (this != obj) //if the new array set is not the same as the set we are copying
      	{
		c = new int[obj.c.length]; //c becomes a new array, the same size as the size of obj's array
		int j = 0; //initialize j
		while (obj.c[j] != 0) //while obj's collection at position j isn't 0
		{
			c[j] = obj.c[j]; j++; //the new collection at j is the same as the obj's array at position j, increment j
		}
		c[j] = 0; //Otherwise the c at j is empty
	}
   }

   //test to see if the integer i belongs in the collection it is called upon. Parameter int i to test
   public boolean belongs(int i) 
   {
      	int j = 0; //initialize a new j
      	while ((c[j] != 0)&&(c[j] != i)) j++; //while the collection at position j is not 0 or i, increment j
      	return ((i>0)&&(c[j] == i)); //return true if the i is greater than 0 and collection contains i, false otherwise
   }

   // insert function for adding to a collection, parameter intake of an integer i
   public void insert(int i)
   {
      	if (i > 0) //if i is greater than 0
	{
	 	int j = 0; //initialize j counter
	     	while ((c[j] != 0) && (c[j] != i)) j++; //while collection at j isn't 0 or i increment j
		if (c[j] == 0) //if the collection at j is empty
	     	{
			if (j == c.length - 1) //and j is the end of the collection
		   	{
				int[] temp = new int[((c.length)*2)+1]; //new int array temp created with double the size of the length of c
				for(int x = 0; x < c.length; x++) { //for an int x, traverse the array
					temp[x] =c[x]; //set the temp at position x to be the value at collection position x
				}
				c = temp; //set c to the pointer of temp array
		   	}
		   	c[j] = i; c[j + 1] = 0;
		}
	}
   }

   //function to omit values from a collection, taking in int parameter i
   public void omit(int i)
   {
	if (i>0) //as long as i is greater than 0, exists
	{
      		int j = 0; //initialize j
      		while ((c[j] != 0)&&(c[j] != i)) j++; //while collection at j isn't 0 or i, increment j
      		if (c[j] == i) //if position j in array c is i
      		{
         		int k = j+1; //k becomes the position to the right of j
         		while (c[k] != 0) k++; //while this value exists, increment k
         		c[j] = c[k-1]; c[k-1]=0; // the collection at j becomes a value set to 0
      		}
	}
   }

   //maintains a count of the number of items in the array
   public int get_howmany()
   {
      	int j=0, howmany=0; //initialize variables

      	while (c[j]!=0) {howmany++; j++;} //traverse c until the end of the array is met, increment howmany for every value
      	return howmany;
   }

   //print function without a parameter
   public void print()
   {
      	int j = 0; //initialize
      	System.out.println(); //print
      	while (c[j] != 0) //while an item exists in the collection
      	{
         	System.out.println(c[j]); j++; //print it and increment
      	}
   }

   //function to determine if collections are equal, taking a parameter obj of type Intcoll QUESTION** 
   public boolean equals(Intcoll1 obj)
   {
      	int j = 0; boolean result = true; //initialize j and a boolean result
      	while ((c[j] != 0)&&result) //for the collection and a true result
      	{
         	result = obj.belongs(c[j]); j++; //result will be true while c[j] belongs in obj, increment j
      	}
      	j = 0; //set j to 0 again
      	while ((obj.c[j] != 0)&&result) //traverse obj and result must hold
      	{
         	result = belongs(obj.c[j]); j++; //result returns if c[j] belongs to obj, increment j
      	}
      	return result; //return result again
   }
}


