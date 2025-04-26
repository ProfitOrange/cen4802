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
		int secVal = 15;
		int thirdVal = 46;

		System.out.println("The " + initVal + " term of the Fibonacci sequence is: " + fibonacci(initVal));
		System.out.println("The " + secVal + " term of the Fibonacci sequence is: " + fibonacci(secVal));
		System.out.println("The " + thirdVal + " term of the Fibonacci sequence is: " + fastFibonacci(thirdVal));
	}
	
	/**
	 * Returns the fibonacci sequence value for the given number in
	 * @param val the value to be caculated
	 * @return the result of the calculation
	 */
	public static long fibonacci(long val)
	{
		return (val == 0 || val == 1) ? val : fibonacci(val - 1) + fibonacci(val - 2);
	}
	
	/**
	 * Significantly faster way of calculating large fibonnaci numbers
	 * Returns the fibonacci sequence value for the given number in
	 * @param val the value to be caculated
	 * @return the result of the calculation
	 */
	public static long fastFibonacci(int val)
	{
		if(val == 0 || val == 1)
			return val;
		
		long first = 0, second = 1;
		
		for (int i = 1; i < val; i++) 
		{
            long next = first + second;
            first = second;
            second = next;
        }
		
		return second;
		
	}
}