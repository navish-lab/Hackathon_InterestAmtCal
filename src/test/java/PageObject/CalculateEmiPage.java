package PageObject;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CalculateEmiPage extends BasePage {
	
	public CalculateEmiPage(WebDriver driver) {
		super(driver);
	}
	
	// Web Elemets
	
	// page header element
	@FindBy(xpath = "//div[@class='page-header']/h1")
	WebElement pageHeaderElement;
	
	// loan Amount input field element
	@FindBy(xpath = "//input[@id='loanamount']")
	WebElement loanAmountInputFieldElement;
	
	// Loan Amount Slider tip element
	@FindBy(xpath = "//div[@id='loanamountslider']/span")
	WebElement loanAmountSliderTipElement;
	
	// Loan Amount Slider scale elements
	@FindBy(xpath = "//div[@id='loanamountsteps']/span")
	List<WebElement> loanAmountSliderScaleElements;
	
	// Interest Rate input field element
	@FindBy(xpath = "//input[@id='loaninterest']")
	WebElement interestRateInputFieldElement;
	
	// Interest Rate Slider tip element
	@FindBy(xpath = "//div[@id='loaninterestslider']/span")
	WebElement interestRateSliderTipElement;
	
	// Interest Rate Slider Scale elements
	@FindBy(xpath = "//div[@id='loanintereststeps']/span")
	List<WebElement> interestRateSliderScaleElements;
	
	// Loan Tenure input field element
	@FindBy(xpath = "//input[@id='loanterm']")
	WebElement loanTenureInputFieldElement;
	
	// Loan Tenure Slider tip element
	@FindBy(xpath = "//div[@id='loantermslider']/span")
	public WebElement loanTenureSliderTipElement;
	
	// Loan Tenure Slider Scale Elements
	@FindBy(xpath = "//div[@id='loantermsteps']/span")
	public List<WebElement> loanTenureSliderScaleElements;
	
	// Loan Tenure Slider Scale Value Elements
	@FindBy(xpath = "//div[@id='loantermsteps']/span/span")
	List<WebElement> loanTenureSliderScaleValueElements;
	
	// Loan Tenure month button
	@FindBy(xpath = "//input[@id='loanmonths']/..")
	WebElement loanTenureMonthButtonElement;
	
	// Loan Charges input field element
	@FindBy(xpath = "//input[@id='loanfees']")
	WebElement loanChargesInputElement;
	
	// Loan Charges Slider tip element
	@FindBy(xpath = "//div[@id='loanfeesslider']/span")
	WebElement loanChargesSliderTipElement;
	
	// Loan Charges Slider Scale Elements
	@FindBy(xpath = "//div[@id='loanfeessteps']/span")
	List<WebElement> loanChargesSliderScaleElement;
	
	
	// Action Methods 
	
	// Method to validate page header is present or not
	public boolean verifyPageHeader() {
		if(pageHeaderElement.isDisplayed()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// Method to validate loan Amount input field present on the page or not 
	public boolean validateLoanAmountInputFieldPresence() {
		return loanAmountInputFieldElement.isDisplayed();
	}
	
	// Method to set the loan amount in the loan amount input field
	public void setLoanAmount(String loanAmount) throws InterruptedException {
		loanAmountInputFieldElement.sendKeys(Keys.CONTROL +"a");
		loanAmountInputFieldElement.sendKeys(loanAmount);
		loanAmountInputFieldElement.sendKeys(Keys.ENTER);

	}
	
	// Method to validate the Loan Amount slider based on the input value on the input field
	public boolean getLoanAmountScaleReading() {
		
		String sliderTipStyle = loanAmountSliderTipElement.getAttribute("style");
		
		for(WebElement scale:loanAmountSliderScaleElements) {
			String scaleStyle = scale.getAttribute("style");
			if(sliderTipStyle.equals(scaleStyle)) {
				return true;
			}
		}
		return false;
	}
	
	// Method to set validate Interest Rate input field present on the page or not 
	public boolean validateInterestRateInputFieldPresence() {
		return interestRateInputFieldElement.isDisplayed();
	}
	
	// Method to interest rate in Interest rate input field
	public void setInterestRate(String interestRate) throws InterruptedException {
		
		interestRateInputFieldElement.sendKeys(Keys.CONTROL + "a");
		interestRateInputFieldElement.sendKeys(interestRate);
		interestRateInputFieldElement.sendKeys(Keys.ENTER);
		
	}
	
	// Method to validate the Interest Rate slider based on the input value on the input field
	public boolean getInterstRateScaleReading() {
		
		String sliderTipStyle = interestRateSliderTipElement.getAttribute("style");
		
		for(WebElement scale:interestRateSliderScaleElements) {
			String scaleStyle = scale.getAttribute("style");
			if(sliderTipStyle.equals(scaleStyle)) {
				return true;
			}
		}
		return false;
	}
	
	// Method to validate Loan Tenure input field present on the page ot not 
	public boolean validateLoanTenureInputFieldPresence() {
		return loanTenureInputFieldElement.isDisplayed();
	}
	
	// Method to set loan Tenure in loan tenure input field 
	public void setLoanTenure(String loanTenure) throws InterruptedException {
		loanTenureInputFieldElement.sendKeys(Keys.CONTROL + "a");
		loanTenureInputFieldElement.sendKeys(loanTenure);
		loanTenureInputFieldElement.sendKeys(Keys.ENTER);
	}
	
	// Method to validate the loan tenure slider based on the input value on the input field
	public boolean getLoanTenureScaleReading() {
		
		String sliderTipStyle = loanTenureSliderTipElement.getAttribute("style").replaceAll("[%;]+", "");
		
		for(WebElement scale:loanTenureSliderScaleElements) {
			String scaleStyle = scale.getAttribute("style").replaceAll("[%;]+", "");
			if(sliderTipStyle.startsWith(scaleStyle)) {
				return true;
			}
		}
		
		return false;
	}
	
	// Method to click on the Loan Tenure Month button Element 
	public void clickOnLoanTenureMonthButton() {
		loanTenureMonthButtonElement.click();

	}
	
	// Method to get data from loanTenure input field
	public String getLoanTenure() {
		return loanTenureInputFieldElement.getAttribute("value");
	}
	
	// Method to print all Loan Tenure Scale values
	public void printLoanTenureScaleValue() {
		for(WebElement scaleValuElement:loanTenureSliderScaleValueElements) {
			System.out.println(scaleValuElement.getText());
		}
	}
	
	// Method to validate Loan Charges input field present on the page or nots
	public boolean verifyLoanChargesInputFieldPresence() {
		return loanChargesInputElement.isDisplayed();
	}
	
	// Method set loan charges in loan charges input field
	public void setLoanCharges(String loanCharges) {
		loanChargesInputElement.sendKeys(Keys.CONTROL +"a");
		loanChargesInputElement.sendKeys(loanCharges);
		loanChargesInputElement.sendKeys(Keys.ENTER);
	}
	
	// Method to validate the loan charges slider based on the input value on the input field 
	public boolean getLoanChargesScaleReading() {
		
		String sliderStyle = loanChargesSliderTipElement.getAttribute("style");
		for(WebElement scale:loanChargesSliderScaleElement) {
			String scaleStyle = scale.getAttribute("style");
			if(sliderStyle.equals(scaleStyle)) {
				return true;
			}
		}
		return false;
	}
	

}
