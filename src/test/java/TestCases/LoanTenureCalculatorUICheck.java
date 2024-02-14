package TestCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utils.ExcelUtility;

public class LoanTenureCalculatorUICheck extends LoanAmountCalculatorUICheck {

	@Test(priority = 50,groups = {"Master","Sanity"})
	public void validateLoanTenureCalculatorLink() {
		
		logger.info("****** Starting TC_050 Loan Tenure Calculator Link ******");
		
		boolean result = emiCalculatorPage.clickOnLoanTenureCalculator();
		
		Assert.assertEquals(true, result);
		
		logger.info("****** finished TC_048 Loan Tenure Calculator Link ******");
	}
	
	@Test(priority = 51,groups = {"Master","Regression"} ,dependsOnMethods = {"validateLoanTenureCalculatorLink"})
	public void verifyLoanTenureLoanAmountInputFieldPresence() {
		
		logger.info("****** Starting TC_051 Loan Tenure Loan Amount Input Field ******");
		
		boolean result = calculateEmiPage.validateLoanAmountInputFieldPresence();
		
		Assert.assertEquals(true, result);
		
		logger.info("****** finished TC_051 Loan Tenure Loan Amount Input Field ******");
	}
	
	@Test(priority = 52,dataProvider = "loanTenureCalculatorData",groups = {"Master","Regression"} ,dependsOnMethods = {"validateLoanTenureCalculatorLink"})
	public void validateLoanTenureLoanAmountInputFieldByPassingValue(String loanAmount ,String interestRate ,String loanCharges) throws InterruptedException {
		
		logger.info("****** Starting TC_052 Loan Tenure Loan Amount Input Field ******");
		
		calculateEmiPage.setLoanAmount(loanAmount);
		
		boolean result = calculateEmiPage.getLoanAmountScaleReading();
		
		Assert.assertEquals(true, result);
		
		logger.info("****** finished TC_052 Loan Tenure Loan Amount Input Field ******");
		
	}
	
	@Test(priority = 53,groups = {"Master","Regression"} ,dependsOnMethods = {"validateLoanTenureCalculatorLink"})
	public void verifyLoanTenureEmiInputFieldPresence() {
		
		logger.info("****** Starting TC_053 Loan Tenure EMI Input Field ******");
		
		boolean result = loanAmountCalculatorPage.validateEMIInputFieldPresence();
		
		Assert.assertEquals(true, result);
		
		logger.info("****** finished TC_053 Loan Tenure EMI Input Field ******");
	}
	
	@Test(priority = 54,groups = {"Master","Regression"} ,dependsOnMethods = {"validateLoanTenureCalculatorLink"})
	public void verifyLoanTenureInterestRateInputFieldPresence() {
		
		logger.info("****** Starting TC_054 Loan Tenure Interest Rate Input Field ******");
		
		boolean result = calculateEmiPage.validateInterestRateInputFieldPresence();
		
		Assert.assertEquals(true, result);
		
		logger.info("****** finished TC_054 Loan Tenure Interest Rate Input Field ******");
	}
	
	@Test(priority = 55,dataProvider = "loanTenureCalculatorData",groups = {"Master","Regression"} ,dependsOnMethods = {"validateLoanTenureCalculatorLink"})
	public void validateLoanTenureInterestRateInputFieldByPassingValue(String loanAmount ,String interestRate ,String loanCharges) throws InterruptedException {
		
		logger.info("****** Starting TC_055 Loan Tenure Interest Rate Input Field ******");
		
		calculateEmiPage.setInterestRate(interestRate);
		
		boolean result = calculateEmiPage.getInterstRateScaleReading();
		
		Assert.assertEquals(true, result);
		
		logger.info("****** finished TC_055 Loan Tenure Interest Rate Input Field ******");
	}
	
	@Test(priority = 56,groups = {"Master","Regression"} ,dependsOnMethods = {"validateLoanTenureCalculatorLink"})
	public void verifyLoanTenuteLoanChargesInputFieldPresence() {
		
		logger.info("****** Starting TC_056 Loan Tenure Loan Charges Input Field ******");
		
		boolean result = calculateEmiPage.verifyLoanChargesInputFieldPresence();
		
		Assert.assertEquals(true, result);
		
		logger.info("****** finished TC_056 Loan Tenure Loan Charges Input Field ******");
	}
	
	@Test(priority = 57,dataProvider = "loanTenureCalculatorData",groups = {"Master","Regression"} ,dependsOnMethods = {"validateLoanTenureCalculatorLink"})
	public void validateLoanTenureLoanChargesInputFieldByValue(String loanAmount ,String interestRate ,String loanCharges) {
		
		logger.info("****** Starting TC_57 Loan Tenure Loan Charges Input Field ******");
		
		calculateEmiPage.setLoanCharges(loanCharges);
		
		boolean result = calculateEmiPage.getLoanChargesScaleReading();
		
		Assert.assertEquals(true, result);
		
		logger.info("****** finished TC_057 Loan Tenure Loan Charges Input Field ******");
	}
	
	@DataProvider(name = "loanTenureCalculatorData")
	public Object[][] loanTenuerCalculatordpMethod(){
		
		int rowNumber = 0;
		int cellNumber = 0;
		
		try {
			rowNumber = ExcelUtility.getRowNumber("LoanTenureCalculatorTestData");
			cellNumber = ExcelUtility.getCellNumber("LoanTenureCalculatorTestData");
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		Object[][] loanTenureCalculatorData = new Object[rowNumber-1][cellNumber];
		
		for(int i=0;i<rowNumber-1;i++) {
			for(int j=0;j<cellNumber;j++) {
				try {
					loanTenureCalculatorData[i][j] = ExcelUtility.readExcelData("LoanTenureCalculatorTestData", (i+1), j);
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		
		return loanTenureCalculatorData;
	}
}
