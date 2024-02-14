package PageObject;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;


public class CarLoanPage extends BasePage {

	Actions actions;
	
	public CarLoanPage(WebDriver driver) {
		super(driver);
	}
	
	// Web Elements
	
	// carLoanAmount Label element 
	@FindBy(xpath = "//label[@for='loanamount']")
	WebElement carLoanAmountLabelElement;
	
	// loan amount slider
	@FindBy(xpath = "//div[@id='loanamountslider']")
	WebElement carLoanAmountSliderElement;
	
	// loan amount slider
	@FindBy(xpath = "//div[@id='loaninterestslider']")
	WebElement carLoanInterestSliderElement;
	
	// loan tenure slider
	@FindBy(xpath = "//div[@id='loantermslider']")
	WebElement carLoanTenureSliderElement;
	
	// year element 
	@FindBy(xpath = "//td[@id='year2024']")
	WebElement yearElement;
	
	// one month interest amount and principal amount
	@FindBy(xpath = "//tr[@class='row no-margin'][1]/td")
	List<WebElement> listOfMonthInterestAndPrincipalAmountElements;
	
	
	// Action Methods

	// method to validate the car loan page 
	public boolean carLoanAmountElement() {
		return carLoanAmountLabelElement.isDisplayed();
	}
	
	// method to set car loan amount 
	public void setCarLoanAmount(String amount) {
		
		actions = new Actions(driver);
		
		int sliderWidth = carLoanAmountSliderElement.getSize().width;
		int pixels = sliderToMove(carLoanAmountSliderElement, Float.parseFloat(amount), 2000000, 0);
		
		actions.clickAndHold(carLoanAmountSliderElement).moveByOffset(-sliderWidth/2, 0).moveByOffset(pixels, 0).release().perform();
		
	}
	
	// method to set interest rate
	public void setInterestRate(String interest) {
		
		int sliderWidth = carLoanInterestSliderElement.getSize().width;
		int pixels = sliderToMove(carLoanInterestSliderElement, Float.parseFloat(interest), 20, 5);
		
		actions.clickAndHold(carLoanInterestSliderElement).moveByOffset(-sliderWidth/2, 0).moveByOffset(pixels, 0).release().perform();
	}
	
	// method to set the loan tenure 
	public void setLoanTenure(String tenure) {
		
		int sliderWidth = carLoanTenureSliderElement.getSize().width;
		int pixels = sliderToMove(carLoanTenureSliderElement, Float.parseFloat(tenure), 7, 0);
		
		actions.clickAndHold(carLoanTenureSliderElement).moveByOffset(-sliderWidth/2, 0).moveByOffset(pixels, 0).release().perform();
	}
	
	
	// method to move the slider based on the value 	
	public int sliderToMove(WebElement sliderElement, float value, float sliderMax, float sliderMin){
		
		int pixels = 0;
		float tempPixels = sliderElement.getSize().width;
		tempPixels = tempPixels/(sliderMax-sliderMin);
		tempPixels = tempPixels*((value)-sliderMin);
		pixels = Math.round(tempPixels);
		return pixels;
		
	}
	
	// method to click on the year 
	public void clickOnYear() {
		scrollIntoView(yearElement);
		yearElement.click();
	}
	
	// method to scroll 
	public void scrollIntoView(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();", element);
	}
	
	// method to read the interest amount and principal amount for one month
	public void readData() {
		System.out.println("Car Loan");
		System.out.println("Month"+"   "+"Interest Amount"+"   "+"Principal Amount");
		int i=1;
		for(WebElement element:listOfMonthInterestAndPrincipalAmountElements) {
			if(i<4) {
				System.out.print(element.getText()+"\t");
				i++;
			}
		}
		System.out.println();
	}
	
	
	
}
