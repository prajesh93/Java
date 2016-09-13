/**
 *The program determines the factors of the given number.
 *
 * $Id: Factorization.java, v1.1 09/05/2015
 *
 * @author      Prajesh Jhumkhawala
 * @author      Ashwini Singh
 */

import java.util.*;
import java.io.*;

public class Factorization {
	public static void main(String args[]) throws IOException {
		// Initialising the variables
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the number whose factorization has to be found");
		int number = Integer.parseInt(br.readLine());
		int prime_array[] = new int[200];
		int temp1;
		int temp = 0;
		// The for loop stores all the prime numbers into prime_array.
		for (int var = 2; var < number + 1; var++) {

			temp1 = 2;
			while (var % temp1 != 0) {
				temp1++;

			}
			if (temp1 == var) {
				prime_array[temp] = var;
				temp++;
			}
		}
		int prime_array1[] = new int[temp];

		// Stores the elements of prime_array into a new array called
		// prime_array1
		for (int i = 0; i < temp; i++) {
			prime_array1[i] = prime_array[i];
		}

		// Code for finding the prime factors
		int temp_num = number; // stores the number into temp_numbersince
								// temp_number is modified ahead and then
								// compared to the original number
		int answer = 1;
		boolean repeat = true;
		String answer_str = "1*";
		while (repeat == true) {
			for (int i = 0; i < prime_array1.length; i++) // Checks all the
															// elements of the
															// prime_array1 to
															// find if they are
															// divisible by
															// number or not

			{
				while (temp_num % prime_array1[i] == 0) // Divides the number
														// with the elements of
														// prime_array1 to check
														// if it is divisible or
														// not with the prime
														// numbers
				{
					answer *= prime_array1[i];
					answer_str += prime_array1[i]; // stores the prime number
													// into the answer_str
					temp_num /= prime_array1[i];
					answer_str += "*";
				}

				if (answer == number) // checks if further division is required
										// or not
				{
					repeat = false;

				}
				if (i == prime_array.length - 1)
					repeat = false;

			}
		}
		
		System.out.println("The factorization of " + number + " is " + answer_str.substring(0, answer_str.length() - 1));
	}
}
