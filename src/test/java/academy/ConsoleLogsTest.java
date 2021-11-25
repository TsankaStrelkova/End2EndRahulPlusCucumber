package academy;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.SpecialForConsoleLogPage;
import resources.Base;

public class ConsoleLogsTest extends Base {
	
	public WebDriver driver;
	// as we have more than one test using LangingPage , define landngPage here and give it value in each test
	public SpecialForConsoleLogPage landingPage;
	private static Logger log =  LogManager.getLogger(Base.class.getName());

	@BeforeTest
	public void beforeMet () throws IOException
	{
		driver =  initializeDriver();
		log.info("Driver initsialized"); 
		
	}
	
	@Test 
	public void SeleniumDoubleTest()
	{
		//System.out.println("Navigate to "+prop.getProperty("url"));
		driver.get(prop.getProperty("url"));
		landingPage = new SpecialForConsoleLogPage(driver);
		
		landingPage.getBrowseProductsButton().click();
		landingPage.getSeleniumLink().click();
		landingPage.getAddToCart().click();
		landingPage.getCartLink().click();
		
		landingPage.getEmailField().clear();
		landingPage.getEmailField().sendKeys("2");
		
		Assert.fail();;
	
	}


	
	@AfterTest
    public void quitDriver() { 
	   driver.close();
    }
	
}


