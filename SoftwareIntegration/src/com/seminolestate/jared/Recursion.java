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
	
	private static int fibonacci(int val)
	{
		if(val == 0 || val == 1)
			return val;
		
		else
			return fibonacci(val - 1) + fibonacci(val - 2);
	}
}