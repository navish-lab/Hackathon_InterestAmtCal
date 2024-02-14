package PageObject;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoanAmountCalculatorPage extends BasePage {

	CalculateEmiPage calculateEmiPage;
	
	public LoanAmountCalculatorPage(WebDriver driver) {
		super(driver);
		calculateEmiPage = new CalculateEmiPage(driver);
	}
	
	// Web Elements 
	
	// EMI input field Element
	@FindBy(xpath = "//input[@id='loanemi']")
	WebElement emiInputFieldElement;
	
	// EMI Slider tip Element
	@FindBy(xpath = "//div[@id='loanemislider']/span")
	WebElement emiSliderTipElement;
	
	// EMI Slider Scale EElements
	@FindBy(xpath = "//div[@id='loanemisteps']/span")
	List<WebElement> emiSliderScaleElements;
	
	// Loan Tenure Year button
	@FindBy(xpath = "//input[@id='loanyears']/..")
	WebElement loanTenureYearButtonElement;
	
	
	// Action Methods 
	
	// Method to validate EMI input field present on the page or not 
	public boolean validateEMIInputFieldPresence() {
		return emiInputFieldElement.isDisplayed();
	}
	
	// Method to set the EMI in the loan amount input field
	public void setEMI(String emi) {
		emiInputFieldElement.sendKeys(Keys.CONTROL+"a");
		emiInputFieldElement.sendKeys(emi);
		emiInputFieldElement.sendKeys(Keys.ENTER);
	}
	
	// Method to validate the Loan Amount slider based on the input value on the input field
	public boolean getEmiScaleReading() {
		String sliderStyle = emiSliderTipElement.getAttribute("style");
		for(WebElement scale:emiSliderScaleElements) {
			String scaleStyle = scale.getAttribute("style");
			if(sliderStyle.equals(scaleStyle)) {
				return true;
			}
		}
		return false;
	}
	
	// Method to click on the loan Tenure Year Button
	public void clickOnLoanTenureButton() {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click();",loanTenureYearButtonElement);
	}
	
	
}
