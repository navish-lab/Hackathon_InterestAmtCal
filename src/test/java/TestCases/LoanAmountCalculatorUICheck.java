package TestCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObject.LoanAmountCalculatorPage;
import Utils.ExcelUtility;

public class LoanAmountCalculatorUICheck extends CalculateEmiUICheck {
	
	LoanAmountCalculatorPage loanAmountCalculatorPage;
	
	@Test(priority = 40,groups = {"Master","Sanity"})
	public void validateLoanAmountCalculatorLink() {
		
		logger.info("****** Starting TC_040 Loan Amount Calculator Link ******");
		
		boolean result = emiCalculatorPage.clickOnLoanAmountCalculator();
		
		Assert.assertEquals(true, result);
		
		logger.info("****** finished TC_040 Loan Amount Calculator Link  ******");
		
	}
	
	@Test(priority = 41,groups = {"Master","Regression"} ,dependsOnMethods = {"validateLoanAmountCalculatorLink"})
	public void verifyEmiInputFieldPresence(){
		
		logger.info("****** Starting TC_041 Loan Amount EMI Input Field ******");
		
		loanAmountCalculatorPage = new LoanAmountCalculatorPage(driver);
		
		boolean result = loanAmountCalculatorPage.validateEMIInputFieldPresence();
		
		Assert.assertEquals(true, result);
		
		logger.info("****** finished TC_041 Loan Amount EMI Input Field ******");
		
	}
	
	@Test(priority = 42,dataProvider = "loanAmountCalculatorData",groups = {"Master","Regression"} ,dependsOnMethods = {"validateLoanAmountCalculatorLink"})
	public void verifyEmiInputFieldByPassingValue(String emi ,String interestRate ,String loanTenureInMonths ,String loanCharges) {

		logger.info("****** Starting TC_042 Loan Amount EMI Input Field ******");
		
		loanAmountCalculatorPage.setEMI(emi);
		
		boolean result = loanAmountCalculatorPage.getEmiScaleReading();
		
		Assert.assertEquals(true, result);
		
		logger.info("****** finished TC_042 Loan Amount EMI Input Field ******");
	}
	
	@Test(priority = 43,groups = {"Master","Regression"} ,dependsOnMethods = {"validateLoanAmountCalculatorLink"})
	public void verifyLoanAmountInterestRateInputFieldPresence() {
		
		logger.info("****** Starting TC_043 Loan Amount Interest Rate Input Field ******");
		
		boolean result = calculateEmiPage.validateInterestRateInputFieldPresence();
		
		Assert.assertEquals(true, result);
		
		logger.info("****** finished TC_043 Loan Amount Interest Rate Input Field ******");
	}
	
	@Test(priority = 44,dataProvider = "loanAmountCalculatorData",groups = {"Master","Regression"} ,dependsOnMethods = {"validateLoanAmountCalculatorLink"})
	public void validateLoanAmountInterestRateInputFieldByPassingValue(String emi ,String interestRate ,String loanTenureInMonths ,String loanCharges) throws InterruptedException {
		
		logger.info("****** Starting TC_044 Loan Amount Interest Rate Input Field ******");
		
		calculateEmiPage.setInterestRate(interestRate);
		
		boolean result = calculateEmiPage.getInterstRateScaleReading();
		
		Assert.assertEquals(true, result);
		
		logger.info("****** finished TC_044 Loan Amount Interest Rate Input Field ******");
		
	}
	
	@Test(priority = 45,groups = {"Master","Regression"} ,dependsOnMethods = {"validateLoanAmountCalculatorLink"})
	public void verifyLoanAmountTenureInputFieldPresence() throws InterruptedException {
		
		logger.info("****** Starting TC_045 Loan Amount Tenure Input Field ******");
		
		boolean result = calculateEmiPage.validateLoanTenureInputFieldPresence();
		
		Assert.assertEquals(true, result);
		
		logger.info("****** finished TC_045 Loan Amount Tenure Input Field ******");

	}
	
	@Test(priority = 46,dataProvider = "loanAmountCalculatorData",groups = {"Master","Regression"} ,dependsOnMethods = {"validateLoanAmountCalculatorLink"})
	public void validateLoanAmountTenureInputFieldByPassingValue(String emi ,String interestRate ,String loanTenureInMonths ,String loanCharges) throws InterruptedException {
		
		logger.info("****** Starting TC_046 Loan Amount Tenure Input Field ******");
		
		calculateEmiPage.setLoanTenure(loanTenureInMonths);
		
		boolean result = calculateEmiPage.getLoanTenureScaleReading();
		
		Assert.assertEquals(true, result);
		
		logger.info("****** finished TC_046 Loan Amount Tenure Input Field ******");
	}
	
	@Test(priority = 47,groups = {"Master","Regression"} ,dependsOnMethods = {"validateLoanAmountCalculatorLink"})
	public void validateLoanAmountLoanTenureMonthButton() throws InterruptedException {
		
		logger.info("****** Starting TC_047 Loan Amount Tenure Month Button ******");
		
		System.out.println("Loan Tenure Slider Scale value in Months");
		calculateEmiPage.printLoanTenureScaleValue();
		
		loanAmountCalculatorPage.clickOnLoanTenureButton();
		Thread.sleep(3000);
		
		System.out.println("Loan Tenure Slider Scale Value in Years");
		calculateEmiPage.printLoanTenureScaleValue();
		
		String loanTenureInMonths = properties.getProperty("loanAmountTenure");
		
		String loanTenure = calculateEmiPage.getLoanTenure();
		
		Assert.assertEquals(loanTenureInMonths, loanTenure);
		
		logger.info("****** finished TC_047 Loan Amount Tenure Month Button ******");
	}
	
	@Test(priority = 48,groups = {"Master","Regression"} ,dependsOnMethods = {"validateLoanAmountCalculatorLink"})
	public void verifyLoanAmountLoanChargesInputFieldPresence() {
		
		logger.info("****** Starting TC_048 Loan Amount Loan Charges Input Field ******");
		
		boolean result = calculateEmiPage.verifyLoanChargesInputFieldPresence();
		
		Assert.assertEquals(true, result);
		
		logger.info("****** finished TC_048 Loan Amount Loan Charges Input Field ******");
		
	}
	
	@Test(priority = 49,dataProvider = "loanAmountCalculatorData",groups = {"Master","Regression"} ,dependsOnMethods = {"validateLoanAmountCalculatorLink"})
	public void validateLoanAmountLoanChargesInputFieldByPassingValue(String emi ,String interestRate ,String loanTenureInMonths ,String loanCharges) {
		
		logger.info("****** Starting TC_049 Loan Amount Loan Charges Input Field ******");
		
		calculateEmiPage.setLoanCharges(loanCharges);
		
		boolean result = calculateEmiPage.getLoanChargesScaleReading();
		
		Assert.assertEquals(true, result);
		
		logger.info("****** finished TC_049 Loan Amount Loan Charges Input Field ******");
	}
	
	@DataProvider(name = "loanAmountCalculatorData")
	public Object[][] loanAmountCalculatordpMethod(){
		
		int rowNumber = 0;
		int cellNumber = 0;
		
		try {
			rowNumber = ExcelUtility.getRowNumber("LoanAmountCalculatorTestData");
			cellNumber = ExcelUtility.getCellNumber("LoanAmountCalculatorTestData");
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		Object[][] loanAmountCalculatorData = new Object[rowNumber-1][cellNumber];
		
		for(int i=0;i<rowNumber-1;i++) {
			for(int j=0;j<cellNumber;j++) {
				try {
					loanAmountCalculatorData[i][j] = ExcelUtility.readExcelData("LoanAmountCalculatorTestData",(i+1),j);
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		
		return loanAmountCalculatorData;
	}
	
}
