package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Base {
	
	public WebDriver driver;
	public Properties prop;
	
	public WebDriver initializeDriver() throws IOException
	{
		prop = new Properties();
		FileInputStream fis  = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
	
	    prop.load(fis);
	    // use the line below if project is not parametrized on Jenkins
	    String browserName = prop.getProperty("browser");
	    
	    // uncomment  the line below if the project is parametrized on Jenkins
	    //String browserName = System.getProperty("browser");
	    // It means that we  run tests using mvn test -Dbrowser=chrome
	    // to make this parametrization on Jenkins, click on the project , then on Configure, click on the check-box "This project is parameterized"
	    // Then click on the Add parameter --> Choice parameter
	    // Put  Name=browser , put in Choices in a separate rows chrome firefox chrome headless
	    // Then in the Build section instead of test write test -Dbrowser="$browser"
	    

	    
	   if(browserName.contains("chrome"))
	   {
		   String chromeDriverPath = System.getProperty("user.dir")+"\\src\\main\\java\\resources\\chromedriver.exe";
		   System.setProperty("webdriver.chrome.driver",chromeDriverPath);
		   ChromeOptions options = new ChromeOptions();
		   if (browserName.contains("headless"))
		   {
			   options.addArguments("headless");
		   }
		   driver = new ChromeDriver(options);
	   }
	   else if(browserName.equals("firefox"))
	   {
		   String geckoDriverPath = System.getProperty("user.dir")+"\\src\\main\\java\\resources\\geckodriver.exe";
		   System.setProperty("webdriver.gecko.driver",geckoDriverPath);
		   driver = new FirefoxDriver();
	   }
	   
	   driver.manage().window().maximize();
	   driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
	   
	   return driver;
	   
	}
	
	public String getScreenShotPath(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
		// add Apache commons io dependency in pom.xml 
		FileUtils.copyFile(source,new File(destinationFile));
		return destinationFile;

	}

}
