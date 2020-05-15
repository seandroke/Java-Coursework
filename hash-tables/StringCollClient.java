//Name: Sean Droke

//*********************************************************************
// FILE NAME    : StringCollClient.java
// DESCRIPTION  : This file contains the class StringCollClient.
//*********************************************************************

package assignment6;

import java.util.*;

public class StringCollClient
{
   public static final String SENTINEL = "###";

   public static void main(String[] args)
   {
      String input; Scanner keyboard=new Scanner(System.in);
      StringColl P=new StringColl(), N=new StringColl(), L= new StringColl();

      System.out.println("Enter a +String to be inserted or a -String to be removed or ### to quit:");
      input=keyboard.nextLine();
      while(!(input.equals(SENTINEL)))
      {
         if (input.charAt(0) == '+') {P.insert(input.substring(1)); L.insert(input.substring(1));}
         else if(input.charAt(0) == '-') {N.insert(input.substring(1)); L.omit(input.substring(1));}
         System.out.println("Enter next string to be inserted or removed or ### to quit:");
         input=keyboard.nextLine();
      }
      System.out.println("\nThe inputs in collection P are:");
      P.print();
      System.out.println("\nThe inputs in collection N are:");
      N.print();
      System.out.println("\nThe inputs in collection L are:");
      L.print();

      if (P.equals(L)) System.out.println("\nP and L are equal.");
      else System.out.println("\nP and L are NOT equal.");
      StringColl A=new StringColl(); A.copy(L);
      System.out.println("\nThe inputs in the copy of L are:\n");
      A.print();

      String x = "test";
      if(P.belongs(x))
         System.out.println("\n" + x + " is in collection P");
      else
         System.out.println("\n" + x + " is not in collection P");
   }
}