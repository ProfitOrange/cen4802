package com.seminolestate.jared.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.seminolestate.jared.LoanCalculator;
import com.seminolestate.jared.Recursion;

class UnitTesting 
{
	private LoanCalculator loanCalc;
	
	@BeforeEach
	public void setUp()
	{
		loanCalc = new LoanCalculator(400000, 4.5, 30);
		System.out.println("Object Succesfully Created!");
	}
	
	@AfterEach
	public void tearDown()
	{
		System.out.println("Runs after the test annotation");
	}
	
	
	@Test
	public void test() 
	{
		assertEquals(55, Recursion.fibonacci(10));
		assertEquals(610, Recursion.fibonacci(15));

		
		
		
		
		//Other Stuff
		assertEquals(2026.74, loanCalc.getMonthlyPayment());
		assertEquals(1500.00, loanCalc.getMonthlyInterest() * loanCalc.getPrincipleRemaining());
		
		assertEquals(30 * 12, loanCalc.getLoanMonthsRemaining());
		
		//Test that principle remaining 18yrs, 9months from now is correct
		assertEquals(229,876.52, loanCalc.getPrincipleRemaining(LocalDate.of(LocalDate.now().getYear() + 18, 9, 30)));
		assertEquals(195,558.89, loanCalc.getPrincipleRemaining(LocalDate.of(LocalDate.now().getYear() + 21, 1, 30)));
		
		System.out.println("Tests Completed Succesfully!");
	}

}
