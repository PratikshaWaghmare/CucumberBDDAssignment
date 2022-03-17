package selenium.auto.testing.pageobjectmodel;



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



public class CmnPgObj {
	
	private static final Logger logger = LogManager.getLogger( CmnPgObj.class);
	WebDriver driver;
	Scenario scn;
	
	
	private By search_text_box = By.xpath("//input[@id='search_query_top']");
	private By search_button = By.xpath("//button[@name='submit_search']");
	private By nav_link_logo =  By.xpath("//img[@class='logo img-responsive']");
	private By prod_cat_women = By.xpath("//a[@class='sf-with-ul' and @title='Women' ]");
	private By prod_cat_dresses = By.xpath("(//a[@class='sf-with-ul' and @title='Dresses'])[2]");
	private By prod_cat_tshirt = By.xpath("(//a[ @title='T-shirts'])[2]");
	private By Product_Cat = By.xpath("//div[@id='block_top_menu']/ul/li/a");
	private By Auto_Text = By.xpath("//li[contains(text(),'T-shirts > Faded Short Sleeve ')]");

	
	public CmnPgObj(WebDriver driver, Scenario scn) {
		this.driver = driver;
		this.scn = scn;
}
	
//--When User validate application home page url--
	
	public void validatePageTitleMatch(String expectedTitle) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Boolean b = wait.until(ExpectedConditions.titleContains(expectedTitle));
		Assert.assertEquals("Title Validation",true, b);
		
		logger.info("Page title matched: " + expectedTitle );
      }
	
	public void PageRedirection(String expectedUrl) {
		String RedirectedPgURL = driver.getCurrentUrl();
		
		logger.info("Browser navigated to URL: " + RedirectedPgURL);
    	scn.log("Browser navigated to URL: " + RedirectedPgURL);
		
	}
	
//--User validate application logo visibility--	

	public void validateAppLogo() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(nav_link_logo));
		boolean logoDisplayOrNOt = driver.findElement(nav_link_logo).isDisplayed();
		Assert.assertTrue("Logo is not displayed.", logoDisplayOrNOt);
		
		logger.info("Logo is Display :> " + logoDisplayOrNOt);
      }
		
//--User validate Height & Width of logo "99" and "350"--	
	
		public void assertingLogoSize(String Height, String width) {
	    WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(nav_link_logo));
		WebElement logoSize = driver.findElement(nav_link_logo);
		
		int Height_1 = Integer.parseInt(Height);
		int width_1 = Integer.parseInt(width);
		
		Assert.assertEquals("User failed to assert logo Height", Height_1, logoSize.getSize().getHeight());
		Assert.assertEquals("user failed to assert width of logo ", width_1, +logoSize.getSize().getWidth());

		logger.info("User successfully assert width and height "
				+ logoSize.getSize().getWidth() + " and " + logoSize.getSize().getHeight());

		logger.info("User successfully assert height and width " + logoSize.getSize().getHeight()
				+ " and " + logoSize.getSize().getWidth());
      }
	
//--user fetch product category list--
		
		public void CategoriesOfProduct() {
		List<WebElement> productList = driver.findElements(Product_Cat);
	
		logger.info("Product list: ");
		for (WebElement e : productList)
	    {
			logger.info( "         " + e.getText());
		}
      }

//--User validate count of the product categories should be "3"--
		
		 public void CountOfProductCategories() {
		 List<WebElement> CountOfCategories = driver.findElements(Product_Cat);
		 
		 logger.info("Count of Categories is:" + CountOfCategories.size());  
		 scn.log("Count of Categories is:" + CountOfCategories.size());
       }
		 
//--User entered text in searchbox "T-shirt"--		 
		 
		 public void SetSearchTextBox(String ProductName) {
		 WebDriverWait webDriverWait = new WebDriverWait(driver,20);
		 WebElement elementSearchBox = webDriverWait.until(ExpectedConditions.elementToBeClickable(search_text_box));
		 elementSearchBox.sendKeys(ProductName);
				
		  logger.info("Value entered in search box: " + ProductName);
        }
		 
//--User validate autosuggestion text "T-shirts > Faded Short Sleeve T-shirts"--	 
		 
		 public void ValidateAutosuggestionText() {
		 WebDriverWait wait = new WebDriverWait(driver, 20);
     	 wait.until(ExpectedConditions.visibilityOfElementLocated(Auto_Text));
		 String Autotext = driver.findElement(Auto_Text).getText();
		 Assert.assertEquals(Autotext.contains("T-shirt"),true);
				
		 logger.info("Autosuggestion text is:"+Autotext);
		 scn.log("Autosuggestion text is:"+Autotext);
        }
	

}


		
	


