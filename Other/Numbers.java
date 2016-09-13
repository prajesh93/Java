/**

       *  A Program that looks for all numbers m, and n, which meets all of the following properties:
       *- n is the k.st prime number (73 is the 21. prime number) 
       *- m is mirror of k.st prime number (37 is the 12th. prime number) 
       *- bN is a palindrome
       *
       *
       * $Id: Numbers.java, v1.3 08/30/2015 
       *
       * @author   Prajesh Jhumkhawala
       * @author   Ashwini Singh
       * 
       *      
       *
       */
import java.io.*;
import java.util.*;

class Numbers
{	
	/**
 	* This method takes a String as a argument and checks if its binary form is a palindrome or not
 	*
 	* @param       num	    	String whose Binary form has to be checked to see if it is a palindrome or not
 	
 	* @return              		True if the binary form of the String is palindrome, else False
 	*
 	*/
	public static void checkBinary(String num)
	{	String tempStr="";
		int num1=Integer.parseInt(num);
		String binary1=Integer.toBinaryString(num1);
		if(num1<8)
		{
			if(num1<4)
			tempStr="00"+binary1;
			else
			tempStr="0"+binary1;	
			
			binary1=tempStr;
		}
		String b2=((new StringBuffer(binary1)).reverse()).toString();
		
		if(binary1.equals(b2))
		{
			System.out.println("Prime Number is "+ num);
			System.out.println("The binary value of "+num+ " is "+ binary1);
		
		}
	}

	/**
 	* This method takes a String as a argument and determines whether the given String is Prime or not.
 	*
 	* @param       checkPrime    	input to be checked for Prime number
 	
 	* @return              		True if the argument is Prime, else False
 	*
 	*/
	public static boolean prime(String checkPrime)
	{
		int temp=0;
		int checkPrimeInt=Integer.parseInt(checkPrime);
		for(int tempVar=2;tempVar<checkPrimeInt;tempVar++)
		{
			if(checkPrimeInt%tempVar==0)
			{
				temp=1;
				break;
			}
		}
		if(temp==0)
		return true;
		else
		return false;

	}

	/**
 	* This method takes a String as a argument and determines whether the reverse of the given String is Prime or not.
 	*
 	* @param       checkInt    	Reverse of the variable whose reverse has to be checked for being Prime
 	
 	* @return              		Calls checkBinary()to check if the binary are palindrome or not
 	*
 	*/

	public static void check(int checkInt)
	{
		String tempStr=Integer.toString(checkInt);
		StringBuffer tempstrBuffer=new StringBuffer(tempStr);
		StringBuffer tempstrBuffer1=tempstrBuffer.reverse();
		String Number=tempstrBuffer1.toString();
		boolean check_Prime=prime(Number);
		if(check_Prime==true)
		{
			checkBinary(tempStr);
		}
	}
	
	/**
 	* Checks for the prime numbers between 2 and 100000
 	*
 	* 
 	* @return              		Calls check() to find if it is reverse is Prime or not
 	*
 	*/

	public static void main(String args[]) throws IOException
	{	int temp1;
		Scanner sc=new Scanner(System.in);
		System.out.println("This program will display the prime numbers which have pallindromic binary form.");
		System.out.println("Enter the number upto which you want to check");
		int endValue=sc.nextInt();
		for(int var=2;var<endValue;var++)
		{
			int temp=0;
			temp1=2;
			while(var%temp1!=0)
			{
				temp1++;
			
			}
			if(temp1==var)
			{
				check(var);
			}
		}
	}
}