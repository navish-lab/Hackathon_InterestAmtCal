package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.CalculateEmiPage;

public class EMICalculator extends HomeLoanEMI {
	
	CalculateEmiPage calculateEmiPage;
	
	@Test(priority = 28,groups = {"Master","Sanity"})
	public void calculatorsDropDownValidationFromHomeLoan() {
		
		logger.info("****** Starting TC_028 Calculators Drop Down ******");
		
		logger.info("Clicking on Calcullators Drop Down");
		emiCalculatorPage.clickOnCalculatorsDropDown();
		
		boolean result = emiCalculatorPage.validateCalculatorsDropDown();
		
		Assert.assertEquals(true,result);
		
		logger.info("****** finished TC_028 Calculators Drop Down ******");
	}
	
	@Test(priority = 29,groups = {"Master","Sanity"},dependsOnMethods = {"calculatorsDropDownValidationFromHomeLoan"})
	public void emiCalculatorPageValidation() {
		
		logger.info("****** Starting TC_029 EMI Calculator Page ******");
		
		calculateEmiPage = new CalculateEmiPage(driver); 
		
		logger.info("Clicking on Loan Calculator Link");
		emiCalculatorPage.clickOnLoanCalculator();
		
		boolean result = calculateEmiPage.verifyPageHeader();
		
		Assert.assertEquals(true, result);
		
		logger.info("****** finished TC_029 MI Calculator Page ******");
		
	}
}
