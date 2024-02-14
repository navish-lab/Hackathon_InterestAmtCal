package TestCases;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import PageObject.CarLoanPage;
import PageObject.EMICalculatorPage;
import TestBase.BaseClass;
import Utils.ExcelUtility;

public class CarLoanEMI extends BaseClass {
	
	
	EMICalculatorPage emiCalculatorPage;
	CarLoanPage carLoanPage;


	@Test(priority = 1 , groups = {"Master","Sanity"})
	public void clickOnCarLoanLink() {
		
		logger.info("****** Starting TC_001 Car Loan Link Element ******");
		
		carLoanPage = new CarLoanPage(driver);
		emiCalculatorPage = new EMICalculatorPage(driver);
		
		logger.info("Clicking on the Car Loan Link");
		emiCalculatorPage.clickOnCarLoanLink();
		
		boolean carLoanAmountElement = carLoanPage.carLoanAmountElement();
		
		Assert.assertEquals(carLoanAmountElement, true);
		
		logger.info("****** finished TC_001 Car Loan Link Element ******");
	}
	
	@Test(priority = 2,dataProvider = "carLoanData",groups = {"Master","Regression"} ,dependsOnMethods = {"clickOnCarLoanLink"})
	public void setCarAmount(String carAmount ,String interestRate ,String loanTenure) {
		
		logger.info("****** Starting TC_002 Car Amount Slider Element ******");
		
		String actCarAmount = carAmount.replaceAll(",","");
		
		logger.info("Entering Data into Car Amount Input Field");
		carLoanPage.setCarLoanAmount(actCarAmount);
	
		logger.info("****** finished TC_002 Car Amount Slider Element ******");
		
	}
	
	@Test(priority = 3,dataProvider = "carLoanData",groups = {"Master","Regression"} ,dependsOnMethods = {"clickOnCarLoanLink"})
	public void setInterestRate(String carAmount ,String interestRate ,String loanTenure ) {
		
		logger.info("****** Starting TC_003 Car Interest Slider Element ******");
		
		logger.info("Entering Data into Car Interest Input Field");
		carLoanPage.setInterestRate(interestRate);
		
		logger.info("****** finished TC_003 Car Interest Slider Element ******");
	}
	
	@Test(priority = 4,dataProvider = "carLoanData",groups = {"Master","Regression"} ,dependsOnMethods = {"clickOnCarLoanLink"})
	public void setLoanTenure(String carAmount ,String interestRate ,String loanTenure ) {
		logger.info("****** Starting TC_004 Car Loan Tenure Slider Element ******");
		
		logger.info("Entering Data into  Loan Tenure Input Field");
		carLoanPage.setLoanTenure(loanTenure);
		
		logger.info("****** finished TC_004 Car Loan Tenure Slider Element ******");
	}
	
	@Test(priority = 5,groups = {"Master","Regression"} ,dependsOnMethods = {"clickOnCarLoanLink"})
	public void clickOnyear() {
		
		logger.info("****** Starting TC_005 Year Element ******");
		
		try {
			logger.info("Clicking on the Year Element");
			carLoanPage.clickOnYear();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Assert.fail();
		}
		
		logger.info("****** finished TC_005 Year Element ******");
	}
	
	@Test(priority = 6,groups = {"Master","Regression"} ,dependsOnMethods = {"clickOnCarLoanLink"})
	public void readData() {
		
		logger.info("****** Starting TC_006 Reading Data From Table ******");
		try {
			carLoanPage.readData();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Assert.fail();
		}
		logger.info("****** finished TC_006 Reading Data From Table ******");
	}
	
	@DataProvider(name = "carLoanData")
	public Object[][] carLoanEmidpMethod(Method method){
		
		int rowNumber = 0;
		int cellNumber = 0;
		
		try {
			rowNumber = ExcelUtility.getRowNumber("CarLoanTestData");
			cellNumber = ExcelUtility.getCellNumber("CarLoanTestData");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Object carLoanData[][] = new Object[rowNumber-1][cellNumber];
		
		for(int i=0;i<rowNumber-1;i++) {
			for(int j=0;j<cellNumber;j++) {
				try {
					carLoanData[i][j] = ExcelUtility.readExcelData("CarLoanTestData", (i+1),j);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
		
		//System.out.println(Arrays.deepToString(carLoanData));
		
		return carLoanData;
		
	}
	

}
