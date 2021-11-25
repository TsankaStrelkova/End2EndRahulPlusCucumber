package academy;

import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.Base;



public class HomePageTest extends Base {
	
	public WebDriver driver;
	// as we have more than one test using LangingPage , define landngPage here and give it value in each test
	public LandingPage landingPage;
	private static Logger log =  LogManager.getLogger(Base.class.getName());

	@BeforeTest
	public void beforeMet () throws IOException
	{
		driver =  initializeDriver();
		log.info("Driver initsialized"); 
		
	}
	
	
	@Test (dataProvider="credentials")
	public void HomePageLogin(String user, String password) throws IOException, InterruptedException
	{
		//System.out.println("Navigate to "+prop.getProperty("url"));
		driver.get(prop.getProperty("url"));
		landingPage = new LandingPage(driver);
		
		LoginPage loginPage = landingPage.clickOnLoginElement(driver);
	    // important note: wen we click on Login button on the landing page - the method clickOnLoginElement() clicks on the button and returns an object Login page 
		// it is code improvement. Instead to have Login element on Landing page and separate click , then to create an object of type Login page 
		// we can combine 3 steps in one method that belongs to Landing page (find element Login button, click on it and return an object of type Login page)
		loginPage.getEmailField().sendKeys(user);
		loginPage.getPasswordField().sendKeys(password);
		loginPage.getLoginButton().click();
		
	}
	
	@DataProvider (name ="credentials")
	public Object[][] getData ()
	{
		
		Object[][] data =new Object [2][2];
		data[0][0] = "user1@mail.com";
		data[0][1] = "password1";
		data[1][0] = "user2@mail.com";
		data[1][1] = "password2";
		
		return data;
	}
	
	@Test
	public void checkTitle() throws IOException
	{
		//System.out.println("Navigate to "+prop.getProperty("url"));
		driver.get(prop.getProperty("url"));
		
		landingPage = new LandingPage(driver);
		// This is an assert that will fail for sure, but we need it just to have some tests fail
		Assert.assertTrue(landingPage.driver.getTitle().equals("Rahul"), "Title is not correct");
		
	
	}
	
	@Test
	public void checkNavigationBar() throws IOException
	{
		//System.out.println("Navigate to "+prop.getProperty("url"));
		driver.get(prop.getProperty("url"));
		
		LandingPage lanPage = new LandingPage(driver);
		Assert.assertTrue(lanPage.navigationBar().isDisplayed(), "Navigation bar is not displayed!");
		log.info("Navigation bar is validated"); 
	
	}
	
	
	@AfterTest
    public void quitDriver() { 
	   driver.close();
    }
	
}
