package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	// https://rahulshettyacademy.com/sign_in/
	// to achieve encapsulation define private variables and public methods 
	public WebDriver driver;
	public LoginPage(WebDriver driver)
		{
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
	
   @FindBy (id="user_email")
   private WebElement email;
   
   @FindBy (id="user_password")
   private WebElement password;
   
   @FindBy (name="commit")
   private WebElement submit;
   
   @FindBy (css=".alert")
   private WebElement wrongCredentials;
   
   public WebElement getEmailField ()
   {
	   return email;
   }

   
   public WebElement getPasswordField ()
   {
	   return password;
   }
   
   public WebElement getLoginButton ()
   {
	   return submit;
   }
   
   public WebElement getWrongCredentials ()
   {
	   return wrongCredentials;
   }
   
}
