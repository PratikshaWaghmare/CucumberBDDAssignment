@ui @BddAssign

Feature: E-commerce Project Web Site 

 Background: Navigation to the URL
 	 Given User navigated to the home application url

@UrlValidation
  Scenario: User navigated to home page url and validate the home page url with given url
  # When User validate application home page url
   Then User should be redirected to "http://automationpractice.com/index.php"
   
@Applicationlogovisibility   
   Scenario: User navigated to home page url and validate apllication logo visibility with Height & Width
   When User validate application logo visibility
   Then User validate Height & Width of logo "99" and "350"
      
@Applicationproductmaincategorylistvalidation
   Scenario: Validation of Application product maincategory list
   When user fetch product category list
   | WOMEN 		|
   | DRESSES	|
   | T-SHIRTS |
   Then User validate count of the product categories should be "3"
  
@ApplicationSearchfunctionality  
   Scenario: User navigated to home page url and validate Search functionality
   When User entered text in searchbox "T-shirt"
   Then User validate autosuggestion text "T-shirts > Faded Short Sleeve T-shirts"
    
@Applicationsocialmediahandlesvalidation    
		Scenario: Application social media handles validation    
		When User clicked on twitter logo
		And User Validate the url opened on a new tab contains "seleniumfrmwrk"
	  Then Validate the twiiter accont name is "Selenium Framework"