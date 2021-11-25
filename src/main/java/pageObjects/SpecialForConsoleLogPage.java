package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SpecialForConsoleLogPage {
	
	public WebDriver driver;
	public SpecialForConsoleLogPage(WebDriver driver)
		{
			this.driver = driver;
			driver.get("https://rahulshettyacademy.com/angularAppdemo/");
			PageFactory.initElements(driver, this);
		}
	
	
	
   
   @FindBy (linkText="Browse Products")
   private WebElement browseProductsButton;
   
   @FindBy (partialLinkText="Selenium")
   private WebElement seleniumLink;
   
   @FindBy (css=".add-to-cart")
   private WebElement addToCart;
   
   @FindBy (linkText="Cart")
   private WebElement cartLink;
   
   @FindBy (id="exampleInputEmail1")
   private WebElement email;
   
   
   
   public WebElement getBrowseProductsButton ()
   {
	   return browseProductsButton;
   }

   
   public WebElement getSeleniumLink ()
   {
	   return seleniumLink;
   }
   
   public WebElement getAddToCart ()
   {
	   return addToCart;
   }
   
   public WebElement getCartLink ()
   {
	   return cartLink;
   }
   
   public WebElement getEmailField ()
   {
	   return email;
   }


}
