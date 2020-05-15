package sorting;

import java.util.*;

public class QuickSortClass
{
   static int qcount = 0;

   //Main method responsible for testing taking the parameter args
   public static void main(String[] args)
   {
      Random gen=new Random(); int[] a=new int[32];
      int i; for (i=0; i<a.length; i++) a[i]=gen.nextInt(5000)+1;
      System.out.println("Initial array:");
      for (i=0; i<a.length; i++) System.out.println(a[i] + " ");
      System.out.println();
      Quicksort(a, 0, a.length-1);
      System.out.println("Sorted array:");
      for (i=0; i<a.length; i++) System.out.println(a[i] + " ");
      System.out.println("Done!");
      System.out.println(" qcount: "+ qcount);
   }
   
   //Quicksort method for completing the sorting taking the parameters a, left, and right
   public static void Quicksort(int[] a, int left, int right) {
	   if(left >= right) { //if the left index is greater than the right, not possible
		   return;
	   }
	   int pivot = a[(left+right)/2]; //establishes the pivot point to be midway through the data
	   int index = partition(a, left, right, pivot); //uses int index to call partition
	   Quicksort(a, left, index - 1); //Recursive call on the left side
	   Quicksort(a, index, right); //Recursive call for the right
   }
   
   //Partition method, working with quicksort with the parameters a, left, right, and pivot
   public static int partition(int[] a, int left, int right, int pivot) {
	   while(left <= right) { //While left index is less than or equal to the right most
		   while(a[left] < pivot) { //while the array value at left index is less than the pivot
			   left++; //iterate the left index
		   }
		   while(a[right] > pivot) { //while the array value at right index is less than the pivot
			   right--; //iterate the right index
		   }
		   if (left <= right) { //case when the left index is less than or equal to right index
			   int temp = a[left]; //initialize temp with the value at the left index of a
			   a[left] = a[right]; //set value at left index with value at right
			   a[right] = temp; //set the value at right index equal to the temp
			   left++; //iterate left
			   right--; //iterate right
		   }
		   qcount++; //q count increases always
	   }
	   return left;
   }   
}



  
