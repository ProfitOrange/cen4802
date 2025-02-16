package com.seminolestate.jared;

/**
 * @author Jared Thompson
 * 1/19/25
 */
public class Recursion 
{

	public static void main(String[] args) 
	{
		int initVal = 10;
		
		
		System.out.println("The " + initVal + " term of the Fibonacci sequence is: " + fibonacci(initVal));
	}
	
	/**
	 * Returns the fibonacci sequence value for the given number in
	 * @param val the value to be caculated
	 * @return the result of the calculation
	 */
	private static int fibonacci(int val)
	{
		return (val == 0 || val == 1) ? val : fibonacci(val - 1) + fibonacci(val - 2);
	}
}