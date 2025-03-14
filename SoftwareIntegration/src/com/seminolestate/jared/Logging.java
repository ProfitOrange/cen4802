package com.seminolestate.jared;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;

/**
 * @author Jared Thompson
 * 3-14-2025
 */
public class Logging 
{
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logging.class);
	
	public static void main(String[] args)
	{
		Configurator.initialize(null, "src/log4j2.xml"); //Add log4j2 config xml
		
		logger.info("Hello World");
		
		long timeInit = 0;
		long timeTaken = 0;
		
		for(int i = 0; i < 30; i++)
		{
			timeInit = System.currentTimeMillis();
			logger.info("Value at " + i + " term: " + Recursion.fibonacci(i));
			timeTaken = System.currentTimeMillis() - timeInit;
			logger.info("Time taken to calculate: " + timeTaken + " milliseconds");
		}
	}
}
