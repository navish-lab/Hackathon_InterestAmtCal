package TestBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class BaseClass {
	
	public static WebDriver driver;
	public Properties properties;
	public Logger logger;
	public SoftAssert softAssert;
	
	@BeforeClass(groups = {"Master","Sanity","Regression"})
	@Parameters({"os","browser"})
	public void setUp(String os,String browser) throws IOException {
		
		// SoftAssert Object Creation 
		softAssert = new SoftAssert();
		
		// Config file setup
		FileInputStream propertiesFile = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		properties = new Properties();
		properties.load(propertiesFile);
		
		// logger Object Creation 
		logger = LogManager.getLogger(Test.class);
		
		
		if(properties.getProperty("execution_env").equalsIgnoreCase("remote"))
	 	{	
		DesiredCapabilities capabilities=new DesiredCapabilities();
		//os
		if(os.equalsIgnoreCase("windows"))
		{
			capabilities.setPlatform(Platform.WIN11);
		}
		else if(os.equalsIgnoreCase("mac"))
		{
			capabilities.setPlatform(Platform.MAC);
		}
		else
		{
			System.out.println("No matching os..");
			return;
		}
		//browser
		switch(browser.toLowerCase())
		{
		case "chrome" : capabilities.setBrowserName("chrome"); break;
		case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
		default: System.out.println("No matching browser.."); return;
		}
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
	    }
	 //If execution_env is local then run in local system
	else if(properties.getProperty("execution_env").equalsIgnoreCase("local"))
	{
		//launching browser based on condition - locally
		switch(browser.toLowerCase())
		{
		case "chrome": driver=new ChromeDriver(); break;
		case "edge": driver=new EdgeDriver(); break;
		default: System.out.println("No matching browser..");
					return;
		}
	}
		
		String url = properties.getProperty("URL");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.get(url);
		
		driver.manage().window().maximize();
		
		driver.manage().deleteAllCookies();
		
	}
	
	@AfterClass(groups = {"Master","Sanity","Regression"})
	public void tearDown() {
		driver.quit();
	}

}
