package sorting;

import java.util.*;

public class InsertionSortClass
{
   static int icount = 0;

   //main method for testing purposes only
   public static void main(String[] args)
   {
      Random gen=new Random(); int[] a=new int[32];
      int i; for (i=0; i<a.length; i++) a[i]=gen.nextInt(5000)+1;
      System.out.println("Initial array:");
      for (i=0; i<a.length; i++) System.out.println(a[i] + " ");
      System.out.println();
      insertionsort(a);
      System.out.println("Sorted array:");
      for (i=0; i<a.length; i++) System.out.println(a[i] + " ");
      System.out.println("Done!");
      System.out.println(" icount: "+ icount);
   }
   
   //insertion sort method for ordering the values passed to arr
   public static void insertionsort(int[] arr) {
	     int i, j, newValue; //initializes variables i, j, and newValue
	     for (i = 1; i < arr.length; i++) { //iterate i over the size of the array beginning at 1
	         newValue = arr[i]; //new value takes the value at the index position i of arr
	         j = i; //j becomes index i
	         while (j > 0 && arr[j - 1] > newValue) { //iterate through j determining when j is greater that newval
	             arr[j] = arr[j - 1]; //set the value of j and value to right of j equal to one another
	             j--; //iterate j to left
	             icount++; //increase count
	         }
	         arr[j] = newValue; //the array at position j becomes the new value
	     }
	 }
	}