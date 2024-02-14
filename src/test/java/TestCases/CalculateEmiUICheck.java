package TestCases;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObject.CalculateEmiPage;
import Utils.ExcelUtility;

public class CalculateEmiUICheck extends EMICalculator {
	
	CalculateEmiPage calculateEmiPage;
	
	@Test(priority = 30,groups = {"Master","Sanity"} ,dependsOnMethods = {"emiCalculatorPageValidation"})
	public void validateEmiCalculatorLink() {
		
		logger.info("****** Starting TC_030 EMI Calculator Link ******");
		
		boolean result = emiCalculatorPage.validateEmiCalculatorLink();
		
		Assert.assertEquals(true, result);
		
		logger.info("****** finished TC_030 EMI Calculator Link ******");
	}
	
	@Test(priority = 31,groups = {"Master","Regression"} ,dependsOnMethods = {"validateEmiCalculatorLink"})
	public void verifyEmiCalculatorLoanAmountInputFieldPresence() {
		
		logger.info("****** Starting TC_031 EMI Calculator Loan Amount Input Field ******");
		
		calculateEmiPage = new CalculateEmiPage(driver);
		
		boolean result = calculateEmiPage.validateLoanAmountInputFieldPresence();
		
		Assert.assertEquals(true, result);
		
		logger.info("****** finished TC_031 EMI Calculator Loan Amount Input Field  ******");
	}
	
	@Test(priority = 32,dataProvider = "EMICalculatorData",groups = {"Master","Regression"} ,dependsOnMethods = {"validateEmiCalculatorLink"})
	public void validateEmiCalculatorLoanAmountInputFieldByPassingValue(String loanAmount ,String interestRate ,String loanTenure ,String loanCharges) throws InterruptedException {
		
		logger.info("****** Starting TC_032 EMI Calculator Loan Amount Input Field ******");
		
		calculateEmiPage.setLoanAmount(loanAmount);
		
		boolean result = calculateEmiPage.getLoanAmountScaleReading();
		
		Assert.assertEquals(true, result);
		
		logger.info("****** finished TC_032 EMI Calculator Loan Amount Input Field  ******");
	}
	
	@Test(priority = 33,groups = {"Master","Regression"} ,dependsOnMethods = {"validateEmiCalculatorLink"})
	public void verifyEmiCalculatorInterestRateInputFieldPresence() {
		
		logger.info("****** Starting TC_033 EMI Calculator Interest Rate Input Field ******");
		
		boolean result = calculateEmiPage.validateInterestRateInputFieldPresence();
		
		Assert.assertEquals(true, result);
		
		logger.info("****** finished TC_033 EMI Calculator Interest Rate Input Field  ******");
		
	}
	
	@Test(priority = 34,dataProvider = "EMICalculatorData",groups = {"Master","Regression"} ,dependsOnMethods = {"validateEmiCalculatorLink"})
	public void validateEmiCalculatorInterestRateInputFieldByPassingValue(String loanAmount ,String interestRate ,String loanTenure ,String loanCharges) throws InterruptedException {
		
		logger.info("****** Starting TC_034 EMI Calculator Interest Rate Input Field ******");
		
		calculateEmiPage.setInterestRate(interestRate);
		
		boolean result = calculateEmiPage.getInterstRateScaleReading();
		
		Assert.assertEquals(true, result);
		
		logger.info("****** finished TC_034 EMI Calculator Interest Rate Input Field  ******");
		
	}
	
	@Test(priority = 35,groups = {"Master","Regression"} ,dependsOnMethods = {"validateEmiCalculatorLink"})
	public void verifyEmiCalculatorLoanTenureInputFieldPresence() {
		
		logger.info("****** Starting TC_035 EMI Calculator Loan Tenure Input Field ******");
		
		boolean result = calculateEmiPage.validateLoanTenureInputFieldPresence();
		
		Assert.assertEquals(true, result);
		
		logger.info("****** finished TC_035 EMI Calculator Loan Tenure Input Field  ******");
		
	}
	
	@Test(priority = 36,dataProvider = "EMICalculatorData",groups = {"Master","Regression"} ,dependsOnMethods = {"validateEmiCalculatorLink"})
	public void validateEmiCalculatorLoanTenureInputFieldByPassingValue(String loanAmount ,String interestRate ,String loanTenure ,String loanCharges) throws InterruptedException {
		
		logger.info("****** Starting TC_036 EMI Calculator Loan Tenure Input Field ******");
		
		calculateEmiPage.setLoanTenure(loanTenure);
		
		boolean result = calculateEmiPage.getLoanTenureScaleReading();
		
		Assert.assertEquals(true, result);
		
		logger.info("****** finished TC_036 EMI Calculator Loan Tenure Input Field  ******");
		
	}
	
	@Test(priority = 37,groups = {"Master","Regression"} ,dependsOnMethods = {"validateEmiCalculatorLink"})
	public void validateEmiCalculatorLoanTenureMonthButton() throws InterruptedException {
		
		logger.info("****** Starting TC_037 EMI Calculator Loan Tenure Month Button ******");
		
		System.out.println("Loan Tenure Slider Scale value in Years");
		calculateEmiPage.printLoanTenureScaleValue();
		
		logger.info("Clicking on EMI Calculator Loan Tenure Month Button ");
		calculateEmiPage.clickOnLoanTenureMonthButton();
		Thread.sleep(3000);
		
		System.out.println("Loan Tenure Slider Scale Value in Months ");
		calculateEmiPage.printLoanTenureScaleValue();
		
		String loanTenureInMonths = properties.getProperty("emiCalculatorLoanTenureInMonths");
		
		String loanTenure = calculateEmiPage.getLoanTenure();
		
		Assert.assertEquals(loanTenureInMonths, loanTenure);
		
		logger.info("****** finished TC_037 EMI Calculator Loan Tenure Month Button ******");
		
	}
	
	@Test(priority = 38,groups = {"Master","Regression"} ,dependsOnMethods = {"validateEmiCalculatorLink"})
	public void verifyEmiCalculatorLoanChargesInputFieldPresence() {
		
		logger.info("****** Starting TC_038 EMI Calculator Loan Charges Input Field ******");
		
		boolean result = calculateEmiPage.verifyLoanChargesInputFieldPresence();
		
		Assert.assertEquals(true, result);
		
		logger.info("****** finished TC_038 EMI Calculator Loan Charges Input Field  ******");
	}
	
	@Test(priority = 39,dataProvider = "EMICalculatorData",groups = {"Master","Regression"} ,dependsOnMethods = {"validateEmiCalculatorLink"})
	public void validateEmiCalculatorLoanChargesInputFieldByPassingValue(String loanAmount ,String interestRate ,String loanTenure ,String loanCharges) {
		
		logger.info("****** Starting TC_039 EMI Calculator Loan Charges Input Field ******");
		
		calculateEmiPage.setLoanCharges(loanCharges);
		
		boolean result = calculateEmiPage.getLoanChargesScaleReading();
		
		Assert.assertEquals(true, result);
		
		logger.info("****** finished TC_039 EMI Calculator Loan Charges Input Field  ******");
	}
	
	
	@DataProvider(name = "EMICalculatorData")
	public Object[][] calculateEmiUICheckdpMethod() {
		
		int rowNumber = 0;
		int cellNumber = 0;
		
		try {
			rowNumber = ExcelUtility.getRowNumber("EMICalculatorTestData");
			cellNumber = ExcelUtility.getCellNumber("EMICalculatorTestData");
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		Object[][] emiCalculatorData = new Object[rowNumber-1][cellNumber];
		
		for(int i=0;i<rowNumber-1;i++) {
			for(int j=0;j<cellNumber;j++) {
				try {
					emiCalculatorData[i][j] = ExcelUtility.readExcelData("EMICalculatorTestData", (i+1), j);
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		
		return emiCalculatorData;	
	}
	
}
