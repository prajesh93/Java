
/**

 * 	 simple calculator that can evaluate the basic arithmetic operations: *, /, +, -, %, ^, (, ).
 *	 The normal precedence of the operators is as follows:
 *			Expressions within '( )' are executed first.
 *			Precedence(^) = 1
 *          Precedence(+) = 2
 *			Precedence(-) = 3
 *			Precedence(%) = 4
 *			Precedence(*) = 5
 *			Precedence(/) = 6
 *	 
     * $Id: Calculator.java, v1.1 09/05/2015
 *
     * @author      Prajesh Jhumkhawala
     * @author      Ashwini Singh
     */

import java.io.*;
import java.util.*;

public class Calculator {
	String[] stack;
	private int top; // Index till where the values are inserted
	int size;

	// Constructor to initialize arrays
	public Calculator(int arraySize) {
		size = arraySize;
		stack = new String[size];
		top = -1;
	}

	// Increment the index by 1 and then store the value to array
	public void push(String value) {

		top = top + 1;
		stack[top] = value;
	}

	// decrement the top index by one so that, top element can not be accessed
	public String pop() {
		int top1 = 0;
		if (!empty()) {
			top1 = top;
			top = top - 1;
		}
		return stack[top1];
	}

	public boolean empty() {
		return top == -1;
	}

	/**
	 * This method takes a String as a argument and calculates the answer of
	 * expression and returns it as an Integer.
	 *
	 * @param expression
	 *            Input String which has to be calculated.
	 * 
	 * @return Answer of given expression as an Integer.
	 *
	 */

	public static String Calculations(String expression) {
		boolean check_char = false;
		int Index_break_At = 0;
		boolean Operator_position = false;
		boolean Wrong_operator = false;
		String operatorString = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Calculator operator = new Calculator(expression.length());
		Calculator operator_temp = new Calculator(expression.length());
		Calculator dig = new Calculator(expression.length());
		Calculator dig_temp = new Calculator(expression.length());
		expression = expression.replace(" ", ""); // Removes White spaces.
		char[] expression1 = expression.toCharArray();

		// Check whether expression contains any character.
		for (int index = 0; index < expression.length(); index++) {
			check_char = Character.isLetter(expression1[index]);
			if (check_char) {
				System.out.println("Characters are used");
				return "";
			}
		}

		if (!check_char) {
			// Check whether expression is starting or ending with operators.
			if ((expression.startsWith("^")) || (expression.startsWith("+")) || (expression.startsWith("-"))
					|| (expression.startsWith("/")) || (expression.startsWith("*")) || (expression.startsWith("%"))
					|| (expression.endsWith("^")) || (expression.endsWith("+")) || (expression.endsWith("-"))
					|| (expression.endsWith("/")) || (expression.endsWith("*")) || (expression.endsWith("%"))) {
				System.out.println("Invalid Operator position");
				Operator_position = true;
				return "";
			} else {
				for (int index = 0; index < expression.length(); index++) {
					Character ch = Character.valueOf(expression.charAt(index)); // take
																				// values
																				// from
																				// expression
																				// and
																				// check
																				// whether
																				// it
																				// is
																				// a
																				// digit
																				// or
																				// not.
																				// If
																				// It's
																				// not
																				// a
																				// digit
																				// then
																				// it
																				// has
																				// to
																				// be
																				// operator.
					if (!Character.isDigit(ch.charValue())) {
						dig.push(expression.substring(Index_break_At, index)); // Store
																				// operands
						Index_break_At = index + 1;
						operator.push(expression.substring(index, Index_break_At)); // Store
																					// operator
						operatorString = expression.substring(index, Index_break_At);
						// If operators are not from mentioned operator then
						// program will terminate.
						if ((!operatorString.equals("+")) && (!operatorString.equals("-"))
								&& (!operatorString.equals("%")) && (!operatorString.equals("*"))
								&& (!operatorString.equals("/")) && (!operatorString.equals("^"))) {
							System.out.println("Wrong Operators");
							Wrong_operator = true;
							return "";
						}
					}
				}
				dig.push(expression.substring(Index_break_At, expression.length()));
			}
			if ((!Operator_position) && (!Wrong_operator)) {
				// Based on precedence, calculate the value of expression
				String op = "";
				String[] preceedence = { "^", "+", "-", "%", "*", "/" };
				int answer = 0;
				for (int i = 0; i < preceedence.length; i++) {
					boolean repeat = false;
					while (!operator.empty()) {
						op = operator.pop().toString();
						int n1 = Integer.parseInt(dig.pop().toString());
						int n2 = Integer.parseInt(dig.pop().toString());
						if (op.equals(preceedence[i])) {
							if (i == 0) {
								double answer1 = Math.pow((double) n2, (double) n1);
								answer = (int) answer1;
								dig_temp.push(Integer.toString(answer));
								repeat = true;
								break;
							}

							if (i == 1) {
								answer = n1 + n2;
								dig_temp.push(Integer.toString(answer));
								repeat = true;
								break;
							}
							if (i == 2) {
								answer = n2 - n1;
								repeat = true;
								dig_temp.push(Integer.toString(answer));
								break;
							}
							if (i == 3) {
								answer = n2 % n1;
								repeat = true;
								dig_temp.push(Integer.toString(answer));
								break;
							}
							if (i == 4) {
								answer = n1 * n2;
								dig_temp.push(Integer.toString(answer));
								repeat = true;
								break;
							}
							if (i == 5) {
								if (n1 == 0) {
									System.out.println("Divisibility by Zero");
									break;
								}
								answer = n2 / n1;
								repeat = true;
								dig_temp.push(Integer.toString(answer));
								break;
							}
						} else {
							dig_temp.push(Integer.toString(n1));
							dig.push(Integer.toString(n2));
							operator_temp.push(op);
						}
					}
					while (!operator_temp.empty()) {
						operator.push(operator_temp.pop());
					}
					while (!dig_temp.empty()) {
						dig.push(dig_temp.pop());
					}
					if (repeat) {
						i--;
					}
				}

			}

		}
		int ans = Integer.parseInt((dig.pop()).toString());
		return Integer.toString(ans);

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the expression");
		String expression = br.readLine();
		String exp_temp = expression;
		int start_of = 0; // last occurrence of '('
		int end_of = 0; // first occurrence of ')'
		while (start_of != -1) {
			start_of = exp_temp.lastIndexOf('(');
			end_of = exp_temp.indexOf(")", start_of);

			if (start_of == -1)
				break;
			if (end_of == -1) { //To check if the brackets are closed properly
				start_of = -2;
				end_of = -2;
				System.out.println("Brackets not closed properly");
				break;
			}
			String sub = exp_temp.substring(start_of + 1, end_of); // expression
																	// under ( )
			String x = Calculations(sub); // get the answer of sub.
			// Now, substitute the answer to the current expression and continue
			// the loop.
			exp_temp = exp_temp.substring(0, start_of) + x + exp_temp.substring(end_of + 1, exp_temp.length());
		}
		if (start_of != -2 || end_of != -2) {
			String Ans = Calculations(exp_temp);
			if (!Ans.equals(""))
				System.out.println("Answer is: "+Ans);
		}
	}
}
