package TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import PageObject.HomeLoanPage;
import Utils.ExcelUtility;

public class HomeLoanEMI extends CarLoanEMI {

	HomeLoanPage homeLoanPage;
	
	@Test(priority = 7,groups = {"Master","Sanity"})
	public void calculatorsDropDownValidation() {
		
		logger.info("****** Starting TC_007 Calculators Drop Down ******");
		
		logger.info("Clicking on the Calculators Drop Down");
		emiCalculatorPage.clickOnCalculatorsDropDown();
		
		boolean result = emiCalculatorPage.validateCalculatorsDropDown();
		
		Assert.assertEquals(true,result);

		logger.info("****** finished TC_007 Calculators Drop Down ******");
	}
	
	@Test(priority = 8,groups = {"Master","Sanity"})
	public void homeLoanEmiCalculatorValidation() throws InterruptedException {
		
		logger.info("****** Starting TC_008 Home Loan Calculator Page ******");
		homeLoanPage = new HomeLoanPage(driver);
			
		emiCalculatorPage.clickOnHomeLoanEmiCalculator();
			
		homeLoanPage.handleAdvertisement();

		boolean result = homeLoanPage.validateHomeLoanEmiCalculator();
			
		Assert.assertEquals(true, result);

		logger.info("****** finished TC_008 Home Loan Calculator Page ******");
	}
	
	@Test(priority = 9,dataProvider = "homeLoanDetailsData",groups = {"Master","Regression"} ,dependsOnMethods = {"calculatorsDropDownValidation","homeLoanEmiCalculatorValidation"})
	public void validateHomeValueInputfield(String homeValue ,String downPayment ,String loanInsurance ,String interestRate ,String loanTenure ,String loanCharges ,
			String startMonthAndYear ,String oneTimeExpenses ,String propertyTax ,String homeInsurance ,String maintenanceExpenses) throws InterruptedException {
		
		logger.info("****** Starting TC_009 Home Value Input Field ******");
		
		logger.info("Entering data into Home Value Input Field");
		homeLoanPage.setHomeValue(homeValue);
		
		Thread.sleep(3000);
		
		String loanAmount = homeLoanPage.getLoanAmount();
		
		try {
			ExcelUtility.writeData("HomeLoanPageValidation", 1, 4,loanAmount.replaceAll("[₹,]+",""));
			ExcelUtility.fillGreenColor("HomeLoanPageValidation", 1, 5);
		} catch (Exception e) {
			try {
				ExcelUtility.fillRedColor("HomeLoanPageValidation", 1, 5);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} 
		Assert.assertEquals(homeValue, loanAmount);
		
		logger.info("****** finished TC_009 Home Value Input Field ******");
	}
	
	@Test(priority = 10,dataProvider = "homeLoanDetailsData",groups = {"Master","Regression"} ,dependsOnMethods = {"calculatorsDropDownValidation","homeLoanEmiCalculatorValidation"})
	public void validateDownPaymentInputFieldInPercentage(String homeValue ,String downPayment ,String loanInsurance ,String interestRate ,String loanTenure ,String loanCharges ,
			String startMonthAndYear ,String oneTimeExpenses ,String propertyTax ,String homeInsurance ,String maintenanceExpenses) throws InterruptedException {
		
		logger.info("****** Starting TC_010 Down Payment Input Field ******");
		
		logger.info("Entering data into Down Payment Input Field");
		homeLoanPage.setdownPayment(downPayment);
		
		Thread.sleep(3000);
		
		float homeValueData = Float.parseFloat(homeValue.replaceAll(",",""));
		
		float loanAmount = Float.parseFloat(homeLoanPage.getLoanAmount().replaceAll(",", "")); 
		
		float actualLoanAmount = homeValueData-(homeValueData/100)*Float.parseFloat(downPayment.replaceAll(",",""));
		
		try {
			ExcelUtility.writeData("HomeLoanPageValidation", 2, 4, Float.toString(actualLoanAmount));
			ExcelUtility.fillGreenColor("HomeLoanPageValidation", 2, 5);
		}
		catch (Exception e) {
			try {
				ExcelUtility.fillRedColor("HomeLoanPageValidation", 2, 5);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		Assert.assertEquals(actualLoanAmount, loanAmount);
		
		logger.info("****** finished TC_010 Down Payment Input Field ******");
	}
	
	@Test(priority = 11,groups = {"Master","Regression"} ,dependsOnMethods = {"calculatorsDropDownValidation","homeLoanEmiCalculatorValidation"})
	public void validateDownPaymentRupeesButton() {
		
		logger.info("****** Starting TC_011 Down Payment Rupees Button ******");
		
		logger.info("Clicking on Down Payment Rupees Button");
		homeLoanPage.clickOnDownPaymentRupeesButton();
		
		String downPaymentInRupees = properties.getProperty("downPaymentInRupees");
		
		String downPaymentOutput = homeLoanPage.getDownPaymentAmount();
		
		try {
			ExcelUtility.writeData("HomeLoanPageValidation", 3, 4,downPaymentOutput.replaceAll("[₹,]+",""));
			ExcelUtility.fillGreenColor("HomeLoanPageValidation", 3, 5);
		}catch (Exception e) {
			try {
				ExcelUtility.fillRedColor("HomeLoanPageValidation", 3, 5);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		Assert.assertEquals(downPaymentInRupees, downPaymentOutput);
		
		logger.info("****** finished TC_011 Down Payment Rupees Button ******");
	}
	
	@Test(priority = 12,dataProvider = "homeLoanDetailsData",groups = {"Master","Regression"} ,dependsOnMethods = {"calculatorsDropDownValidation","homeLoanEmiCalculatorValidation"})
	public void validateLoanInsuranceInputField(String homeValue ,String downPayment ,String loanInsurance ,String interestRate ,String loanTenure ,String loanCharges ,
			String startMonthAndYear ,String oneTimeExpenses ,String propertyTax ,String homeInsurance ,String maintenanceExpenses) throws InterruptedException {
		
		logger.info("****** Starting TC_012 Loan Insurance Input Field ******");
		
		logger.info("Entering data into Loan Insurance Input Field");
		homeLoanPage.setLoanInsurance(loanInsurance);
		
		Thread.sleep(3000);
		
		String loanAmount =  homeLoanPage.getLoanAmount();
		
		try {
			ExcelUtility.writeData("HomeLoanPageValidation", 4, 4, loanAmount.replaceAll("[₹,]+",""));
			ExcelUtility.fillGreenColor("HomeLoanPageValidation", 4, 5);
		}catch (Exception e) {
			try {
				ExcelUtility.fillRedColor("HomeLoanPageValidation", 4, 5);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		Assert.assertEquals(properties.getProperty("finalLoanAmount"), loanAmount);
		
		logger.info("****** finished TC_012 Loan Insurance Input Field ******");
	}
	
	@Test(priority = 13,dataProvider = "homeLoanDetailsData",groups = {"Master","Regression"} ,dependsOnMethods = {"calculatorsDropDownValidation","homeLoanEmiCalculatorValidation"})
	public void validateInterestRateInputField(String homeValue ,String downPayment ,String loanInsurance ,String interestRate ,String loanTenure ,String loanCharges ,
			String startMonthAndYear ,String oneTimeExpenses ,String propertyTax ,String homeInsurance ,String maintenanceExpenses) throws InterruptedException {
		
		logger.info("****** Starting TC_013 Interest Rate Input Field ******");
		
		try {
			logger.info("Entering data into Interest Rate Input Field");
			homeLoanPage.setInteresrRate(interestRate);
		}
		catch (Exception e) {
			// TODO: handle exception
			Assert.fail();
		}
		logger.info("****** finished TC_013 Interest Rate Input Field ******");
	}
	
	@Test(priority = 14,dataProvider = "homeLoanDetailsData",groups = {"Master","Regression"} ,dependsOnMethods = {"calculatorsDropDownValidation","homeLoanEmiCalculatorValidation"})
	public void validateLoanTenureInputField(String homeValue ,String downPayment ,String loanInsurance ,String interestRate ,String loanTenure ,String loanCharges ,
			String startMonthAndYear ,String oneTimeExpenses ,String propertyTax ,String homeInsurance ,String maintenanceExpenses) {

		logger.info("****** Starting TC_014 Loan Tenure Input Field ******");
		
		try {
			logger.info("Entering data into Loan Tenure Input Field");
			homeLoanPage.setLoanTenure(loanTenure);
		} catch (Exception e) {
			// TODO: handle exception
			Assert.fail();
		}
		
		logger.info("****** finished TC_014 Loan Tenure Input Field ******");
	}
	
	@Test(priority = 15,groups = {"Master","Regression"} ,dependsOnMethods = {"calculatorsDropDownValidation","homeLoanEmiCalculatorValidation"})
	public void validateLoanTenureMonthsButton() throws InterruptedException {
		
		logger.info("****** Starting TC_015 Loan Tenure Month Button ******");
		
		String loanTenureInMonths = properties.getProperty("loanTenureInMonths");
		
		logger.info("Clicking on Loan Tenure Month Button");
		homeLoanPage.clickOnLoanTenureMonthsButton();
		
		String loanTenureInMonthsOutput = homeLoanPage.getLoanTenureInMonths();
		
		try {
			ExcelUtility.writeData("HomeLoanPageValidation", 6, 4,loanTenureInMonthsOutput.replaceAll("[₹,]+",""));
			ExcelUtility.fillGreenColor("HomeLoanPageValidation", 6, 5);
		}catch (Exception e) {
			try {
				ExcelUtility.fillRedColor("HomeLoanPageValidation", 6, 5);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		Assert.assertEquals(loanTenureInMonths, loanTenureInMonthsOutput);
		
		logger.info("****** finished TC_015 Loan Tenure Month Button ******");
	}
	
	@Test(priority = 16,dataProvider = "homeLoanDetailsData",groups = {"Master","Regression"} ,dependsOnMethods = {"calculatorsDropDownValidation","homeLoanEmiCalculatorValidation"})
	public void validateLoanChargesInputField(String homeValue ,String downPayment ,String loanInsurance ,String interestRate ,String loanTenure ,String loanCharges ,
			String startMonthAndYear ,String oneTimeExpenses ,String propertyTax ,String homeInsurance ,String maintenanceExpenses) {
		
		logger.info("****** Starting TC_016 Loan Charges Input Field ******");
		
		try {
			logger.info("Entering data into Loan Charges Input Field");
			homeLoanPage.setLoanCharges(loanCharges);
		}
		catch (Exception e) {
			// TODO: handle exception
			Assert.fail();
		}
		
		logger.info("****** finished TC_016 Loan Charges Input Field ******");
	}
	
	@Test(priority = 17,groups = {"Master","Regression"} ,dependsOnMethods = {"calculatorsDropDownValidation","homeLoanEmiCalculatorValidation"})
	public void validateLoanChargesRupeesButtonElement() {
		
		logger.info("****** Starting TC_017 Loan Charges Rupees Button ******");
		
		String loanChargesInRupees = properties.getProperty("loanChargesInRupees");
		
		logger.info("Clicking on the Loan Charges Rupees Button");
		homeLoanPage.clickOnLoanChargesRupeesButton();
		
		String loanChargesOutput = homeLoanPage.getLoanChargesAmount();
		
		try {
			ExcelUtility.writeData("HomeLoanPageValidation", 7, 4, loanChargesOutput.replaceAll("[₹,]+",""));
			ExcelUtility.fillGreenColor("HomeLoanPageValidation", 7, 5);
		}catch (Exception e) {
			try {
				ExcelUtility.fillRedColor("HomeLoanPageValidation", 7,5);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		Assert.assertEquals(loanChargesInRupees,loanChargesOutput );
		
		logger.info("****** finished TC_017 Loan Charges Rupees Button ******");
		
	}
	
	@Test(priority = 18,groups = {"Master","Regression"} ,dependsOnMethods = {"calculatorsDropDownValidation","homeLoanEmiCalculatorValidation"})
	public void validateLoanStartMonthAndYearField() {
		
		logger.info("****** Starting TC_018 Loan Start Month And Year Input Field ******");
		
		logger.info("Clicking on Loan Start Month and Year Input Field");
		homeLoanPage.clickOnLoanStartMonthAndYear();
		
		Assert.assertEquals(true, homeLoanPage.validateDatePicker());
		
		logger.info("****** finished TC_018 Loan Start Month And Year Input Field******");
	}
	
	@Test(priority = 19,dataProvider = "homeLoanDetailsData",groups = {"Master","Regression"} ,dependsOnMethods = {"calculatorsDropDownValidation","homeLoanEmiCalculatorValidation"})
	public void validateLoanStartMonthAndYearInputField(String homeValue ,String downPayment ,String loanInsurance ,String interestRate ,String loanTenure ,String loanCharges ,
			String startMonthAndYear ,String oneTimeExpenses ,String propertyTax ,String homeInsurance ,String maintenanceExpenses) {
		
		logger.info("****** Starting TC_019 Loan Start Month And Year Input Field ******");
		
		String[] moantAndYear = startMonthAndYear.split("-");
		
		String loanStartYear = moantAndYear[2];
		String loanMonth = moantAndYear[1];
		
		logger.info("Entering data into Loan Start Month and Year Input Field");
		homeLoanPage.setLoanStartMonthAndYear(loanStartYear,loanMonth);
		
		logger.info("****** finished TC_019 Loan Start Month And Year Input Field ******");
	}
	
	@Test(priority = 20,dataProvider = "homeLoanDetailsData",groups = {"Master","Regression"} ,dependsOnMethods = {"calculatorsDropDownValidation","homeLoanEmiCalculatorValidation"})
	public void validateOneTimeExpensesInputField(String homeValue ,String downPayment ,String loanInsurance ,String interestRate ,String loanTenure ,String loanCharges ,
			String startMonthAndYear ,String oneTimeExpenses ,String propertyTax ,String homeInsurance ,String maintenanceExpenses) {
		
		logger.info("****** Starting TC_020 One Time Expenses Input Field ******");
		
		try {
			logger.info("Entering data into One Time Expenses Input Field");
			homeLoanPage.setOneTimeExpenses(oneTimeExpenses);
		}
		catch (Exception e) {
			// TODO: handle exception
			Assert.fail();
		}
		
		logger.info("****** finished TC_020 One Time Expenses Input Field ******");
	}
	
	@Test(priority = 21,groups = {"Master","Regression"} ,dependsOnMethods = {"calculatorsDropDownValidation","homeLoanEmiCalculatorValidation"})
	public void validateOneTimeExpensesRupeesButton() throws InterruptedException {
		
		logger.info("****** Starting TC_021 One Time Expenses Rupees Button ******");
		
		String oneTimeExpensesInRupees = properties.getProperty("oneTimeExpensesInRupees");
		
		logger.info("Clicking on One Time Expenses Button");
		homeLoanPage.clickOnOneTimeExpensesRupeesButton();
		
		String oneTimeExpensesOutput = homeLoanPage.getOneTimeExpensesInRupees();
		
		try {
			ExcelUtility.writeData("HomeLoanPageValidation", 9, 4, oneTimeExpensesOutput.replaceAll("[₹,]+",""));
			ExcelUtility.fillGreenColor("HomeLoanPageValidation", 9, 5);
		}
		catch (Exception e) {
			try {
				ExcelUtility.fillRedColor("HomeLoanPageValidation", 9, 5);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		Assert.assertEquals(oneTimeExpensesInRupees, oneTimeExpensesOutput);
		
		logger.info("****** finished TC_021 One Time Expenses Rupees Button ******");
		
	}
	
	@Test(priority = 22, dataProvider = "homeLoanDetailsData",groups = {"Master","Regression"} ,dependsOnMethods = {"calculatorsDropDownValidation","homeLoanEmiCalculatorValidation"})
	public void validatePropertyTaxesPerYearInputField(String homeValue ,String downPayment ,String loanInsurance ,String interestRate ,String loanTenure ,String loanCharges ,
			String startMonthAndYear ,String oneTimeExpenses ,String propertyTax ,String homeInsurance ,String maintenanceExpenses) {
		
		logger.info("****** Starting TC_022 Property Tax Input Field ******");
		
		try {
			homeLoanPage.setPropertyTaxesPeryear(propertyTax);
		}
		catch (Exception e) {
			// TODO: handle exception
			Assert.fail();
		}
		
		logger.info("****** finished TC_022 Property Tax Input Field ******");
	}
	
	@Test(priority = 23,groups = {"Master","Regression"} ,dependsOnMethods = {"calculatorsDropDownValidation","homeLoanEmiCalculatorValidation"})
	public void validatePropertyTaxesPerYearRupeesButton() throws InterruptedException {
		
		logger.info("****** Starting TC_023 Property Tax Rupees Button ******");
		
		String propertyTaxesPerYearInRupees = properties.getProperty("propertyTaxes/yearInRupees");
		
		logger.info("Clicking on Property Tax Rupees button");
		homeLoanPage.clickOnPropertyTaxesPerYearRupeesButton();
		
		String propertyTaxOutput = homeLoanPage.getPropertyTaxesPerYearInRupees();
		
		try {
			ExcelUtility.writeData("HomeLoanPageValidation", 10, 4, propertyTaxOutput.replaceAll("[₹,]+",""));
			ExcelUtility.fillGreenColor("HomeLoanPageValidation", 10, 5);
		}
		catch (Exception e) {
			try {
				ExcelUtility.fillRedColor("HomeLoanPageValidation", 10, 5);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		Assert.assertEquals(propertyTaxesPerYearInRupees, propertyTaxOutput);
		
		logger.info("****** finished TC_023 Property Tax Rupees Button ******");
	}
	
	@Test(priority = 24, dataProvider = "homeLoanDetailsData",groups = {"Master","Regression"} ,dependsOnMethods = {"calculatorsDropDownValidation","homeLoanEmiCalculatorValidation"})
	public void validateHomeInsurancePerYearInputField(String homeValue ,String downPayment ,String loanInsurance ,String interestRate ,String loanTenure ,String loanCharges ,
			String startMonthAndYear ,String oneTimeExpenses ,String propertyTax ,String homeInsurance ,String maintenanceExpenses) {
		
		logger.info("****** Starting TC_024 Home Insurance Input Field ******");
		
		try {
			logger.info("Entering Data into Home Insurance Input Field ");
			homeLoanPage.setHomeInsurancePerYear(homeInsurance);
		}
		catch (Exception e) {
			// TODO: handle exception
			Assert.fail();
		}
		
		logger.info("****** finished TC_024 Home Insurance Input Field ******");
	}
	
	@Test(priority = 25,groups = {"Master","Regression"} ,dependsOnMethods = {"calculatorsDropDownValidation","homeLoanEmiCalculatorValidation"})
	public void validateHomeInsurancePerYearRupeesButton() throws InterruptedException {
		
		logger.info("****** Starting TC_025 Home Insurance Rupees Button ******");
		
		String homeInsurancePerYearInRupees = properties.getProperty("homeInsurance/yearInRupees");
		
		logger.info("Clicking on Home Insurance Rupees Button");
		homeLoanPage.clickOnHomeInsurancePerYearRupeesButton();
		
		String homeInsuranceOutput = homeLoanPage.getHomeInsurancePerYearInRupees();
		
		try {
			ExcelUtility.writeData("HomeLoanPageValidation", 11, 4, homeInsuranceOutput.replaceAll("[₹,]+",""));
			ExcelUtility.fillGreenColor("HomeLoanPageValidation", 11,5);
		}
		catch (Exception e) {
			try {
				ExcelUtility.fillRedColor("HomeLoanPageValidation", 11, 5);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		Assert.assertEquals(homeInsurancePerYearInRupees, homeInsuranceOutput);
		
		logger.info("****** finished TC_025 Home Insurance Rupees Button ******");
		 
	}
	
	@Test(priority = 26, dataProvider = "homeLoanDetailsData",groups = {"Master","Regression"} ,dependsOnMethods = {"calculatorsDropDownValidation","homeLoanEmiCalculatorValidation"})
	public void validateMaintenanceExpensesPerMonthInputField(String homeValue ,String downPayment ,String loanInsurance ,String interestRate ,String loanTenure ,String loanCharges ,
			String startMonthAndYear ,String oneTimeExpenses ,String propertyTax ,String homeInsurance ,String maintenanceExpenses) {
		
		logger.info("****** Starting TC_026 Maintenance Expenses Input Field ******");
		
		try {
			logger.info("Entering Data into Maintenance Expenses Input Field ");
			homeLoanPage.setMaintenanceExpensesPerMonth(maintenanceExpenses);
		}
		catch (Exception e) {
			// TODO: handle exception
			Assert.fail();
		}
		
		logger.info("****** finished TC_026 Maintenance Expenses Input Field ******");
	}
	
	
	@Test(priority = 27,groups = {"Master","Regression"} ,dependsOnMethods = {"calculatorsDropDownValidation","homeLoanEmiCalculatorValidation"})
	public void printAllTheData() throws InterruptedException, IOException {
		logger.info("****** Starting TC_027 Reading Data from table ******");
		homeLoanPage.readData();
		logger.info("****** finished TC_027 Reading Data from table ******");
	}
	
	
	@DataProvider(name = "homeLoanDetailsData")
	public Object[][] homeLoanDetailsdpMethod(){
		
		int rowNumber = 0;
		int cellNumber = 0;
		
		try {
			rowNumber = ExcelUtility.getRowNumber("HomeLoanTestData");
			cellNumber = ExcelUtility.getCellNumber("HomeLoanTestData");
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		Object homeLoanDetails[][] = new Object[rowNumber-2][cellNumber-1];
		
		for(int i=0;i<rowNumber;i++) {
			for(int j=0;j<cellNumber;j++) {
				try {
					homeLoanDetails[i][j] = ExcelUtility.readExcelData("HomeLoanTestData", (i+2), j);
				}
				catch (Exception e) {
					// TODO: handle exception
					
				}
			}
		}
		
		return homeLoanDetails;
	}
	
}
