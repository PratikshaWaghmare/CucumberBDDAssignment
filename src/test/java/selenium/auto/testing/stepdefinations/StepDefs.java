package selenium.auto.testing.stepdefinations;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import selenium.auto.testing.core.DriverFactory;
import selenium.auto.testing.pageobjectmodel.CmnPgObj;
import selenium.auto.testing.pageobjectmodel.TwitterPgObj;


public class StepDefs {
	
	private static final Logger logger = LogManager.getLogger(StepDefs.class);

	WebDriver driver;
    String base_url = "https://automationpractice.com";
    int implicit_wait_timeout_in_sec = 20;
    Scenario scn;
    
    CmnPgObj cmnPgObj;
    TwitterPgObj  twitterPgObj;
    
   
    @Before
    public void setUp(Scenario scn) throws Exception{
    	this.scn = scn;
        String firefox = DriverFactory.getBrowserName();
        driver = DriverFactory.getWebDriverForBrowser(firefox);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(implicit_wait_timeout_in_sec, TimeUnit.SECONDS);
        logger.info("Browser invoked.");
        
        cmnPgObj = new CmnPgObj(driver, scn);  
        twitterPgObj = new TwitterPgObj(driver, scn);  

       
    }
    
    @After(order=1)
    public void cleanUp(){
       DriverFactory.quitDriver();
        scn.log("Browser Closed");
        logger.info("Browser Closed.");
    }
    
    @After(order=2)
    public void takeScreenShot(Scenario s) {
        if (s.isFailed()) {
            TakesScreenshot scrnShot = (TakesScreenshot)driver;
            byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
            scn.attach(data, "image/png","Failed Step Name: " + s.getName());
        }else{
            scn.log("Test case is passed, no screen shot captured");
        }
    }
    
//--User navigated to the home application url
    
    @Given("User navigated to the home application url")
    public void user_navigated_to_the_home_application_url() {   
    	 DriverFactory.navigateToTheUrl(base_url);
       scn.log("Browser navigated to URL: " + base_url);
       logger.info("Browser navigated to URL: " + base_url);
    }
    
//--When User validate application home page url--    
    
    @When("User validate application home page url")
    public void user_validate_application_home_page_url() {
    	 DriverFactory.navigateToTheUrl(base_url);
         scn.log("Browser navigated to URL: " + base_url);

         String expected = "My Store";
         cmnPgObj.validatePageTitleMatch(expected);
    }
    
//--User should be redirected to "http://automationpractice.com/index.php" 
    
    @Then("User should be redirected to {string}")
    public void user_should_be_redirected_to(String  expectedUrl) {
    	cmnPgObj.PageRedirection(expectedUrl);
  
    }
    
//--User validate application logo visibility--    
    
    @When("User validate application logo visibility")
    public void user_validate_application_logo_visibility() {
        cmnPgObj.validateAppLogo();
        scn.log("Logo is validated ");
    }
    
//--User validate Height & Width of logo "99" and "350"--	
    
    @Then("User validate Height & Width of logo {string} and {string}")
    public void user_validate_height_width_of_logo_and(String Height, String width) {
    	cmnPgObj.assertingLogoSize(Height, width);
    }

//--user fetch product category list--
    
	@When("user fetch product category list")
	public void user_fetch_product_category_list(io.cucumber.datatable.DataTable dataTable) {
	    cmnPgObj.CategoriesOfProduct();
	}

//--User validate count of the product categories should be "3"--
	
	@Then("User validate count of the product categories should be {string}")
	public void user_validate_count_of_the_product_categories_should_be(String Count) {
	    cmnPgObj.CountOfProductCategories();
	}
	
//--User entered text in searchbox "T-shirt"--		
	
	@When("User entered text in searchbox {string}")
	public void user_entered_text_in_searchbox(String productName) {
		 cmnPgObj.SetSearchTextBox(productName);
	}
	
//--User validate autosuggestion text "T-shirts > Faded Short Sleeve T-shirts"--		
	
	@Then("User validate autosuggestion text {string}")
	public void user_validate_autosuggestion_text(String string) {
	    cmnPgObj.ValidateAutosuggestionText();
		
	}

//--User clicked on twitter logo--		

	@When("User clicked on twitter logo")
	public void user_clicked_on_twitter_logo() {
	   twitterPgObj.SocialMediaHandle();
	}
    
//--User Validate the url opened on a new tab contains "seleniumfrmwrk"--	
	
	@When("User Validate the url opened on a new tab contains {string}")
	public void user_validate_the_url_opened_on_a_new_tab_contains(String expectedTitle) throws InterruptedException {
		
		twitterPgObj.validateTwitterPageTitle(expectedTitle);
    }

//--Validate the twiiter accont name is "Selenium Framework"--	
	
	@Then("Validate the twiiter accont name is {string}")
	public void validate_the_twiiter_accont_name_is(String TwitterAccName) throws InterruptedException {
	      
		    twitterPgObj.SocialMediaHandleName(TwitterAccName);
	}

}





	
	
	
	
	

