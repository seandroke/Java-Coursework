//Name: Sean Droke

//**********************************************************
// FILE: NAME   : MultiStringCollClient.java
// DESCRIPTION  : This is a client of class MultiStringColl.
//**********************************************************

package assignment6;

import java.util.*;

public class MultiStringCollClient
{
   public static final String SENTINEL = "###";

   public static void main(String[] args)
   {
      String value; String firstCharacter; String reformatValue; Scanner keyboard=new Scanner(System.in);
      MultiStringColl P=new MultiStringColl(), N=new MultiStringColl(), L= new MultiStringColl();

      System.out.println("Enter a +string to be inserted, -string to remove it, or ### to quit:");

      value=keyboard.nextLine();
      firstCharacter = String.valueOf(value.charAt(0));

      reformatValue  = value.substring(1,value.length());
      while(!(value.equals(SENTINEL)))
      {
         if (firstCharacter.equals("+")) {
             P.insert(reformatValue); 
             L.insert(reformatValue);
         }
         else {N.insert(reformatValue); L.omit(reformatValue);}
         System.out.println("Enter +string to insert another string, -string to remove another string, or ### to quit:");
         value=keyboard.nextLine();
         firstCharacter = String.valueOf(value.charAt(0));
         reformatValue  = value.substring(1,value.length());

      }
      System.out.println("\nThe values in collection P are:");
      P.print();
      System.out.println("\nThe number of distinct strings in collection P is:");
      System.out.println(P.get_howmany());
      System.out.println("\nThe total number of strings in collection P is:");
      System.out.println(P.get_total());
      System.out.println("\nThe values in collection N are:");
      N.print();
      System.out.println("\nThe number of distinct strings in collection N is:");
      System.out.println(N.get_howmany());
      System.out.println("\nThe total number of strings in collection N is:");
      System.out.println(N.get_total());
      System.out.println("\nThe values in collection L are:");
      L.print();
      System.out.println("\nThe number of distinct strings in collection L is:");
      System.out.println(L.get_howmany());
      System.out.println("\nThe total number of strings in collection L is:");
      System.out.println(L.get_total());
      if (P.equals(N)) System.out.println("\nP and N are equal.");
      else System.out.println("\nP and N are NOT equal.");
      MultiStringColl A=new MultiStringColl(); A.copy(L);
      System.out.println("\nThe values in the copy of L are:\n");
      A.print();


   }
}