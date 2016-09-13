    /**
     * 	A simple program that will generate all possible combinations of n people attending a party.  
     *  
     * $Id: Subsets.java, v1.1 08/30/2015
	 *
     * @author      Prajesh Jhumkhawala
     * @author      Ashwini Singh
     */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Subsets
{
  public static void main(String[] args)
    throws IOException
  {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter number of elements");
    int n = Integer.parseInt(br.readLine());
    int Total_elements = 1;
    int value = 0;
    String op = "{}";
    for (int index = 1; index <= n; index++) 
    {
      Total_elements *= 2; // generate number of all possible sets.
    }
    int w=0;
    int x = Total_elements;
    int[][] a = new int[Total_elements][n];
    // create a matrix of mxn, where m: all possible number of sets, n: number of elements.
    // 0 represents that element is absent and 1 represents that element is present.
    for (int col = 0; col < n; col++)
    {
      int row = 0;
      while (row != Total_elements)
      {
        if (w < x/2) {
        	
        	a[row][col] = 0;
            row++;
            w++;
        }
        else if(w>=x/2 && w<x)
        {
        	a[row][col] = 1;
            row++;
            w++;
        }
        if(w>=x)
        	w=0;
         }
     x=x/2;
    }
//    // Print matrix
//    for (int row = 0; row < Total_elements; row++)
//    {
//      for (int col = 0; col < n; col++) {
//        System.out.print(a[row][col] + "\t");
//      }
//      System.out.println();
//    }
    // Check for 1 in matrix and get corresponding element as the element of subset.
    for (int row = 0; row < Total_elements; row++)
    {
    	int flag=0;
      for (int col = 0; col < n; col++)
      {
    if(a[row][col]==1 && flag==0)
    {
    	op=op+",{"+(col+1)+"}";    // for 1st occurrence of 1, flag is set to 1.
    	flag=1;
    }
    else if(a[row][col]==1 && flag==1)  // if flag is 1 then it will be concatenated with previous element.
    {
    	op=op.substring(0,op.length()-1);
    	op=op+","+(col+1)+"}";
    }
      }
    }
    System.out.println(op);
  }
}
