package com.seminolestate.jared;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
/**
 * @author Jared Thompson
 *
 */
public class LoanCalculator 
{
	public static final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
	
	private double principle, interest, monthlyPayment;
	private LocalDate start, end;
	private int loanTerm;
	
	/**
	 * Generate new loan
	 * @param principle - Initial Loan amount
	 * @param interest - Yearly interest rate
	 * @param loanTermYears - Loan term in years
	 */
	public LoanCalculator(double principle, double interest, int loanTermYears)
	{
		this.principle = principle;
		this.interest = interest;
		this.monthlyPayment = (principle * this.getMonthlyInterest()) / (1 - Math.pow(1 + this.getMonthlyInterest(), -(loanTermYears * 12)));
		
		this.start = LocalDate.parse(LocalDate.now().toString(), DateTimeFormatter.ofPattern("yyyy-M-dd"));
		this.end = LocalDate.parse(LocalDate.now().plusYears(loanTermYears).toString(), DateTimeFormatter.ofPattern("yyyy-M-dd"));
		
		this.loanTerm = (int)ChronoUnit.MONTHS.between(this.getLoanStart(), this.getLoanEnd());
	}
	
	public double getStartPrinciple()
	{
		return this.principle;
	}
	
	public double getInterest()
	{
		return this.interest;
	}
	
	public double getMonthlyPayment()
	{
		return Math.round(this.monthlyPayment * 100.0) / 100.0;
	}
	
	//Returns the interest percentage; to get the interest payed each month, multiply this by the remaining principle
	public double getMonthlyInterest()
	{
		return (this.getInterest() / 100) / 12;
	}
	
	public LocalDate getLoanStart()
	{
		return this.start;
	}
	
	public LocalDate getLoanEnd()
	{
		return this.end;
	}
	
	public int getLoanTermMonths()
	{
		return this.loanTerm;
	}
	
	public int getLoanMonthsRemaining()
	{
		return this.getLoanMonthsRemaining(this.getLoanEnd());
	}
	
	//Custom months remaining
	public int getLoanMonthsRemaining(LocalDate date)
	{
		return LocalDate.now().isBefore(this.getLoanEnd()) ? (int)ChronoUnit.MONTHS.between(LocalDate.now(), date) : 0;
	}
	
	//Custom months payed
	public int getMonthsPayed(LocalDate date)
	{
		return this.getLoanTermMonths() - this.getLoanMonthsRemaining(date);
	}
	
	public double getInterestPayed()
	{
		return this.getInterestPayed(this.getLoanEnd());
	}
	
	public double getInterestPayed(LocalDate date)
	{
		double remainingPrinciple = this.getStartPrinciple();
		double interestPayed = 0;
		
		for(int j = 0; j < this.getMonthsPayed(date); j++)
		{
			interestPayed += remainingPrinciple * this.getMonthlyInterest();
			remainingPrinciple -= (this.getMonthlyPayment() - (remainingPrinciple * this.getMonthlyInterest()));
		}
		
		return interestPayed;
	}
	
	public double getPrincipleRemaining()
	{
		return this.getPrincipleRemaining(this.getLoanEnd());
	}
	
	public double getPrincipleRemaining(LocalDate date)
	{
		double remainingPrinciple = this.getStartPrinciple();
		
		for(int j = 0; j < this.getMonthsPayed(date); j++)
			remainingPrinciple -= (this.getMonthlyPayment() - (remainingPrinciple * this.getMonthlyInterest()));
		
		return remainingPrinciple;
	}

	@Override
	public String toString() 
	{
		return "Original Principle: " + nf.format(this.getStartPrinciple()) 
		+ ", Yearly Interest: %" + String.format("%.3f", this.getInterest()) 
		+ ", Monthly Payment: " + this.getMonthlyPayment() 
		+ ", Loan Start Date: " + this.getLoanStart().toString()
		+ ", Loan End Date: " + this.getLoanEnd().toString();
	}
}