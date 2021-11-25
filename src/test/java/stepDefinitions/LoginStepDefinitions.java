package stepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.Base;

public class LoginStepDefinitions extends Base {
	
	LandingPage landingPage;
	LoginPage loginPage; 
	private static Logger log =  LogManager.getLogger(Base.class.getName());
	
	@Given("I am on Langing page")
	public void i_am_on_langing_page() throws IOException {
		driver =  initializeDriver();
		log.info("Driver initsialized"); 
		driver.get(prop.getProperty("url"));
		landingPage = new LandingPage(driver);
		if (landingPage.getPopUpButtons().size()>0) {
			landingPage.getPopUpButtons().get(0).click();
		}
	}
	
	@When("I click on Login")
	public void i_click_on_login() {
		 loginPage = landingPage.clickOnLoginElement(driver);    
	}
	
	@When("I enter {string} and {string}")
	public void i_enter_and(String user, String password) {
		// important note: when we click on Login button on the landing page - the method clickOnLoginElement() clicks on the button and returns an object Login page 
	    // it is code improvement. Instead to have Login element on Landing page and separate click , then to create an object of type Login page 
		// we can combine 3 steps in one method that belongs to Landing page (find element Login button, click on it and return an object of type Login page)
		loginPage.getEmailField().sendKeys(user);
		loginPage.getPasswordField().sendKeys(password);
		loginPage.getLoginButton().click();
	}
	
	@Then("I am successfully logged {string}")
	public void i_am_successfully_logged(String string) {
	    Assert.assertTrue(!loginPage.getWrongCredentials().isDisplayed());
	}

	


}
