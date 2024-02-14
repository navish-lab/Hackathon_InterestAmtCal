package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EMICalculatorPage extends BasePage{
	
	public EMICalculatorPage(WebDriver driver) {
		super(driver);
		
	}
	
	// Web Elemets
	
	// Car Loan Element
	@FindBy(xpath = "//li[@id='car-loan']/a")
	WebElement carLoanElement;
	
	// Calculators drop down element
	@FindBy(xpath = "//a[@id='menu-item-dropdown-2696']")
	WebElement calculatorsDropDownElement;
	
	// home loan emi calculator element
	@FindBy(xpath = "//ul[@class='dropdown-menu show']/li[1]/a")
	WebElement homeLoanEmiCalculatorElement;
	
	// Loan Calculator element
	@FindBy(xpath = "//ul[@class='dropdown-menu show']/li[2]/a")
	WebElement loanCalculatorElement;
	
	// EMI Calculator link Element
	@FindBy(xpath = "//li[@id='emi-calc']")
	WebElement emiCalculatorLinkElement;
	
	// Loan Amount Claculator link Element
	@FindBy(xpath = "//li[@id='loan-amount-calc']")
	WebElement loanAmountCalculatorLinkElement;
	
	// Loan Tenure Calculator link Element
	@FindBy(xpath = "//li[@id='loan-tenure-calc']")
	WebElement loanTenureCalculatorxLinkElement;
	
	
	// Action Methods
	
	// Method to click on the car loan link
	public void clickOnCarLoanLink() {
		carLoanElement.click();
	}
	
	// Method to click on the calculators drop down element
	public void clickOnCalculatorsDropDown() {
		calculatorsDropDownElement.click();
	}
	
	// Method to validate the drop down
	public boolean validateCalculatorsDropDown() {
		
		if(homeLoanEmiCalculatorElement.isDisplayed()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// Method to click on the home loan emi calculator
	public void clickOnHomeLoanEmiCalculator() {
		
		homeLoanEmiCalculatorElement.click();
	}
	
	// Method to click on the Loan Calculator element
	public void clickOnLoanCalculator() {
		loanCalculatorElement.click();
	}
	
	// Method to validate emi Calculate link
	public boolean validateEmiCalculatorLink() {
		
		String clickAction = emiCalculatorLinkElement.getAttribute("class");
		
		if(clickAction.equals("active")) {
			return true;
		}
		return false;
	}
	
	// Method to click on the Loan Amount Calculator link
	public boolean clickOnLoanAmountCalculator() {
		
		loanAmountCalculatorLinkElement.click();
		
		String clickAction = loanAmountCalculatorLinkElement.getAttribute("class");
		
		if(clickAction.equals("active")) {
			return true;
		}
		return false;
	}
	
	// Method to click on the Loan Tenure Calculator link
	public boolean clickOnLoanTenureCalculator() {
		
		loanTenureCalculatorxLinkElement.click();
		
		String clickAction = loanTenureCalculatorxLinkElement.getAttribute("class");
		
		if(clickAction.equals("active")) {
			return true;
		}
		return false;
	}
	
}
