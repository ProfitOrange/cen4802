package com.seminolestate.jared;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;

/**
 * @author Jared Thompson
 * 3-14-2025
 */
public class Logging 
{
	private static final int FIBONACCI_LIMIT = 50; //46 before integer rolls over
	
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logging.class);
	
	public static void main(String[] args)
	{
		Configurator.initialize(null, "src/log4j2.xml"); //Add log4j2 config xml
		
		Scanner input = new Scanner(System.in);
		
		int term = -1;
		
		while(term == -1)
		{
			System.out.println("Enter a term to calculate the fibonacci sequence to: ");
			term = parseInt(input);
		}
		
		//System.out.println("The " + term + " term of the Fibonacci sequence is: " + Recursion.fibonacci(term));
		System.out.println("The " + term + " term of the Fibonacci sequence is: " + Recursion.fastFibonacci(term));
		
		long timeInit = 0;
		long timeTaken = 0;
		
		for(int i = 0; i <= term; i++) //Last value before integer rolls over to the negatives
		{
			timeInit = System.currentTimeMillis();
			long value = Recursion.fastFibonacci(i);
			timeTaken = System.currentTimeMillis() - timeInit;
			
			if(value < 0)
			{
				logger.error("Positive integer limit reached at term: " + (i - 1) + "! Unable to continue parsing, closing program!");
				break;
			}
			
			logger.info("Value at " + i + " term: " + value);
			logger.info("Time taken to calculate: " + timeTaken + " milliseconds");
			
			if(timeTaken > 1000)
				logger.warn("Fibonacci calculation term: " + i + " is causing the program to experience significant slow downs!");
		}
		
		input.close();
	}
	
	//Re-used from older projects
	private static int parseInt(Scanner valueIn)
	{
		try
		{
			int value = Integer.parseInt(valueIn.nextLine());

			if(value >= 0 && value <= FIBONACCI_LIMIT)
				return value;

			else
				logger.warn("Value entered: " + value + "; Value must be >= 0 and <= " + FIBONACCI_LIMIT + "!\n");
		}

		catch(NumberFormatException e)
		{
			logger.error("A non-integer value was entered! Integer values only!", e);
			e.printStackTrace();
		}

		return -1;
	}
}