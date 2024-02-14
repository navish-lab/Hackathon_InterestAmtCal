package PageObject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Utils.ExcelUtility;


public class HomeLoanPage extends BasePage{
	
	public HomeLoanPage(WebDriver driver) {
		super(driver);
	}
	
	// Web Elements
	
	// Home loan details header element 
	@FindBy(xpath = "//h3[@id='ld']")
	WebElement homeLoanDetailsHeaderElement;
	
	// Advertisement dismiss button
	@FindBy(xpath = "//div[@id='dismiss-button']")
	WebElement advertisementDismissButtonElement;
	
	// Advertisement iframe element
	@FindBy(xpath = "//iframe[@id='ad_iframe']")
	WebElement advertisementIframeElement;
	
	// First Iframe Element
	@FindBy(xpath = "//iframe[@id='google_esf']/following-sibling::ins/div/iframe")
	WebElement firstIframeElement;
	
	// Home Value input box element
	@FindBy(xpath = "//input[@id='homeprice']")
	WebElement homeValueInputElement;
	
	// Loan Amount input box element
	@FindBy(xpath = "//input[@id='homeloanamount']")
	WebElement loanAmountInputElement;
	
	// Down Payment input box element
	@FindBy(xpath = "//input[@id='downpayment']")
	WebElement downPaymentElement;
	
	// Down Payment rupees button element
	@FindBy(xpath = "//input[@id='downpayment']/../div/div/label[2]")
	WebElement downPaymentRupeesButtonElement;
	
	// Loan Insurance Input element
	@FindBy(xpath = "//input[@id='homeloaninsuranceamount']")
	WebElement loanInsuranceInputElement;
	
	// Interest Rate input element
	@FindBy(xpath = "//input[@id='homeloaninterest']")
	WebElement interesetRateInputElement;
	
	// Loan Tenure input element
	@FindBy(xpath = "//input[@id='homeloanterm']")
	WebElement loanTenureInputElement;
	
	// Loan Tenure months button
	@FindBy(xpath = "//input[@id='homeloanmonths']//..")
	WebElement loanTenureMonthsButtonElement;
	
	// Loan Charges input element
	@FindBy(xpath = "//input[@id='loanfees']")
	WebElement loanChargesInputElement;
	
	// Loan Charges rupees button element
	@FindBy(xpath = "//input[@id='loanfeesrupees']/..")
	WebElement loanChargesRupeesButtonElement;
	
	// Loan Start Month and Year element
	@FindBy(xpath = "//input[@id='startmonthyear']")
	WebElement loanStartMonthAndYearInputElement;
	
	// Datepicker element
	@FindBy(xpath = "//div[@class='datepicker-months']")
	WebElement datePickerElement;
	
	// Loan Start Year
	@FindBy(xpath = "(//table[@class='table-condensed'])[2]/thead/tr[2]/th[2]")
	WebElement loanYearElement;
	
	// Year change button (Next)
	@FindBy(xpath = "(//table[@class='table-condensed'])[2]/thead/tr[2]/th[3]")
	WebElement nextYearButtonElement;
	
	// List of Month Elements
	@FindBy(xpath = "(//table[@class='table-condensed'])[2]/tbody/tr/td/span")
	List<WebElement> monthsElement;
	
	// One time expenses
	@FindBy(xpath = "//input[@id='onetimeexpenses']")
	WebElement oneTimeExpensesElement;
	
	// One Time expenses rupees button
	@FindBy(xpath = "//input[@id='onetimeexpensesrupees']/..")
	WebElement oneTimeExpensesRupeesElement;
	
	// Property Taxes / year input element
	@FindBy(xpath = "//input[@id='propertytaxes']")
	WebElement propertyTaxesPerYearElement;
	
	// Property Taxes / year rupees button element
	@FindBy(xpath = "//input[@id='propertytaxesrupees']/..")
	WebElement propertyTaxesPerYearRupeesButtonElement;
	
	// Home Insurance / year input Element
	@FindBy(xpath = "//input[@id='homeinsurance']")
	WebElement homeInsurancePerYearInputElement;
	
	//Home Insurance / Year rupees button element
	@FindBy(xpath = "//input[@id='homeinsurancerupees']/..")
	WebElement homeInsurancePerYearRupeesButtonElement;
	
	// Maintenance Expenses / month element
	@FindBy(xpath = "//input[@id='maintenanceexpenses']")
	WebElement maintenanceExpensesPerMonthElement;
	
	// Year on Year table
	@FindBy(xpath = "//div[@id='paymentschedule']")
	WebElement yearOnYearTableElement;
	
	// Year on Year table Headers
	@FindBy(xpath = "//div[@id='paymentschedule']/table/tbody/tr[1]/th")
	List<WebElement> headersElements;
	
	// Year on Year table row
	@FindBy(xpath = "//tr[@class='row no-margin yearlypaymentdetails']")
	List<WebElement> dataTableRowElements;
	
	
	// Action Methods
	
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	
	// Method to validate home loan emi calculator link
	public boolean validateHomeLoanEmiCalculator() {
		if(homeLoanDetailsHeaderElement.isDisplayed()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// Method to handle Advertisement
	public void handleAdvertisement() throws InterruptedException {
		try {
			boolean res = firstIframeElement.isDisplayed();
			if(res) {
				// first iframe
				driver.switchTo().frame(firstIframeElement);
				// second iframe
				driver.switchTo().frame(advertisementIframeElement);
				advertisementDismissButtonElement.click();
				driver.switchTo().defaultContent();
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// Method to set the home value amount
	public void setHomeValue(String homeValueAmount) {
		
		homeValueInputElement.clear();
		homeValueInputElement.sendKeys(homeValueAmount);
	}
	
	// Method to get loan amount
	public String getLoanAmount() {
		
		String loanamount = loanAmountInputElement.getAttribute("value");
		
		return loanamount;
	}
	
	// Method to set down payment
	public void setdownPayment(String downPayment) {
		downPaymentElement.clear();
		downPaymentElement.sendKeys(downPayment);
		
	}
	
	// Method to click on DownPayment rupees button
	public void clickOnDownPaymentRupeesButton() {
		downPaymentRupeesButtonElement.click();
	}
	
	// Method to get DownPayment Amount
	public String getDownPaymentAmount() {
		return downPaymentElement.getAttribute("value");
	}
	
	// Method to set loan insurance
	public void setLoanInsurance(String loanInsuranceAmount) {
		loanInsuranceInputElement.sendKeys(Keys.CONTROL +"a");;
		loanInsuranceInputElement.sendKeys(loanInsuranceAmount);
	}
	
	// Method to set the Interest Rate
	public void setInteresrRate(String interestRate) throws InterruptedException {
		interesetRateInputElement.sendKeys(Keys.CONTROL +"a");
		
		interesetRateInputElement.sendKeys(interestRate);
	}
	
	// Method to set the loan tenure 
	public void setLoanTenure(String loanTenure) {
		loanTenureInputElement.sendKeys(Keys.CONTROL +"a");
		loanTenureInputElement.sendKeys(loanTenure);
	}
	
	// Method to click on the Loan Tenure Months button
	public void clickOnLoanTenureMonthsButton() {
		
		
		jse.executeScript("arguments[0].click();", loanTenureMonthsButtonElement);
		
	}
	
	// Method to get the Loan Tenure in Months
	public String getLoanTenureInMonths() throws InterruptedException {
		
		return loanTenureInputElement.getAttribute("value");
	}
	
	// Method to set loan charges 
	public void setLoanCharges(String loanCharges) throws InterruptedException {
		loanChargesInputElement.clear();
		loanChargesInputElement.sendKeys(loanCharges);
		Thread.sleep(4000);
	}
	
	// Method to validate loan charges rupees button
	public void clickOnLoanChargesRupeesButton() {
		loanChargesRupeesButtonElement.click();
//		jse.executeScript("arguments[0].click();", loanChargesRupeesButtonElement);
	}
	
	// Method to get loan charges Amount
	public String getLoanChargesAmount() {
		return loanChargesInputElement.getAttribute("value");
	}
	
	// Method to select Loan start month and year
	public void clickOnLoanStartMonthAndYear() {
		
		loanStartMonthAndYearInputElement.click();
	}
	
	// Method to validate Date Picker 
	public boolean validateDatePicker() {
		
		if(datePickerElement.isDisplayed()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// Method to set loan start month and year
	public void setLoanStartMonthAndYear(String year,String month) {
		
		while(true) {
			if(loanYearElement.getText().equals(year)) {
				break;
			}
			else {
				nextYearButtonElement.click();
			}
			
		}
		try {
			for(WebElement element:monthsElement) {
				if(element.getText().equals(month)) {
					element.click();
				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// Method to set the oneTimeExpenses 
	public void setOneTimeExpenses(String oneTimeExpenses) {
		oneTimeExpensesElement.sendKeys(Keys.CONTROL +"a");
		oneTimeExpensesElement.sendKeys(oneTimeExpenses);
	}
	
	// Method to validate one time expenses rupees button
	public void clickOnOneTimeExpensesRupeesButton() throws InterruptedException {
		Thread.sleep(3000);
		oneTimeExpensesRupeesElement.click();
	}
	
	// Method to get one time expenses
	public String getOneTimeExpensesInRupees() {
		return oneTimeExpensesElement.getAttribute("value");
	}
	
	// Method to set the Property Taxes / year
	public void setPropertyTaxesPeryear(String taxesPerYear){
		propertyTaxesPerYearElement.sendKeys(Keys.CONTROL +"a");
		propertyTaxesPerYearElement.sendKeys(taxesPerYear);
		
	}
	
	// Method to validate Property Taxes / year rupees button
	public void clickOnPropertyTaxesPerYearRupeesButton() throws InterruptedException {
		Thread.sleep(3000);
		propertyTaxesPerYearRupeesButtonElement.click();
	}
	
	// Method to get property taxes / year amount
	public String getPropertyTaxesPerYearInRupees() {
		return propertyTaxesPerYearElement.getAttribute("value");
	}
	
	// Method to set Home Insurance / Year
	public void setHomeInsurancePerYear(String homeInsurance) {
		homeInsurancePerYearInputElement.sendKeys(Keys.CONTROL +"a");
		homeInsurancePerYearInputElement.sendKeys(homeInsurance);
	}
	
	// Method to validate Home Insurance / Year rupees button
	public void clickOnHomeInsurancePerYearRupeesButton() throws InterruptedException {
		Thread.sleep(3000);
		homeInsurancePerYearRupeesButtonElement.click();
	}
	
	// Method to get Home Insurance / Year amount
	public String getHomeInsurancePerYearInRupees() {
		return homeInsurancePerYearInputElement.getAttribute("value");
	}
	
	// Method to set Maintenance Expenses / month
	public void setMaintenanceExpensesPerMonth(String MaintenanceExpenses) {
		maintenanceExpensesPerMonthElement.sendKeys(Keys.CONTROL +"a");
		maintenanceExpensesPerMonthElement.sendKeys(MaintenanceExpenses);
	}
	
	// Method to print the Year on Year table
	public void readData() throws InterruptedException, IOException {
		jse.executeScript("arguments[0].scrollIntoView();", yearOnYearTableElement);
		
		Thread.sleep(4000);
		
		System.out.println("Year"+"\t"+"Principal (A)"+"\t"+"Interest (B)"+"\t"+"Taxes, Home Insurance & Maintenance (C)"+"\t"+"Total Payment (A+B+C)"+"\t"+"Balance"+"\t"+"Loan Paid To Date");
		
		for(int i=1;i<=dataTableRowElements.size();i++) {
			for(int j=0;j<headersElements.size();j++) {
				String data = driver.findElement(By.xpath("//tr[@class='row no-margin yearlypaymentdetails']["+i+"]/td["+(j+1)+"]")).getText().replaceAll("[,%]+","");
				if(data.contains("₹")) {
					data = data.substring(1);
				}
				ExcelUtility.writeData("HomeLoanYearOnYearTable", i, j, data);
				System.out.print(data+"\t");
			}
			System.out.println();
		}
		
	}
}
