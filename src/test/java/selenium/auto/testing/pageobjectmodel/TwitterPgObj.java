package selenium.auto.testing.pageobjectmodel;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;


public class TwitterPgObj {

	private static final Logger logger = LogManager.getLogger( CmnPgObj.class);
	WebDriver driver;
	Scenario scn;
	
	private By Twitter_Handle = By.xpath("//li[@class='twitter']");
	private By Twitter_Handle_Name = By.xpath("(//span[contains(text(),'Selenium Framework')])[2]");
	
	public TwitterPgObj(WebDriver driver, Scenario scn) {
		this.driver = driver;
		this.scn = scn;
     }

//--User clicked on twitter logo--
	
	 public void SocialMediaHandle() {
			driver.findElement(Twitter_Handle).click();
			logger.info("Clicked on Social Media Handle: twitter");
     }
		 
//--User Validate the url opened on a new tab contains "seleniumfrmwrk"--
	 
	 public void validateTwitterPageTitle(String expectedTitle) throws InterruptedException {
			 
			ArrayList<String> handles = new ArrayList<String> (driver.getWindowHandles());
			
			String parentWindowId = handles.get(0);
			String childWindowID1 = handles.get(1);
			
			driver.switchTo().window(childWindowID1);
			
			Thread.sleep(4000);

			logger.info("Child1 window title is - " + driver.getTitle());
			scn.log("Child1 window title is - " + driver.getTitle());
	  }			

//--Validate the twiiter accont name is "Selenium Framework"	 
	 
		public void SocialMediaHandleName(String text) {
			WebDriverWait wait = new WebDriverWait(driver, 20);
		    WebElement TwitterAccName = driver.findElement(Twitter_Handle_Name);
		    wait.until(ExpectedConditions.visibilityOfElementLocated(Twitter_Handle_Name));
		    
		    Assert.assertEquals(text,TwitterAccName.getText());
		    logger.info("Page Name matched:"+text,TwitterAccName.getText());
		    scn.log("Page Name matched:"+text);
	}

}

