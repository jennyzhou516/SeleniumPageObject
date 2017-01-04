package page.object;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pages.base.PageObject;

public class Cart extends PageObject{
	public Cart(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// Define elements ****************************************************
	@FindBy(xpath=".//*[@id='checkoutFormSubmit']")
	public WebElement checkOut_button;
	
	@FindBy(xpath=".//*[@class='terms-of-service required']")
	public WebElement termsOfService;

	public WebElement productQuantity(String productName){
		String xpt=".//*[@class='vm-cart-item-name']/a[text()='" + productName 
				+ "']/ancestor::tr[contains(@class,'sectiontableentry')]//input[@class='quantity-input js-recalculate']";
		return driver.findElement(By.xpath(xpt));
	}
	
	public List<WebElement> listProductName(){
		String xpt =".//*[@class='vm-cart-item-name']/a";
		return driver.findElements(By.xpath(xpt));
	}
	
	@FindBy(xpath=".//*[@id='userForm']/div/button[text()='Checkout as Guest']")
	public WebElement checkOutGuest;
	
	@FindBy(id="email_field")
	public WebElement email;
	
	@FindBy(id="username_field")
	public WebElement userName;
	
	@FindBy(id="name_field")
	public WebElement displayName;
	
	@FindBy(id="password_field")
	public WebElement passwd;
	
	@FindBy(id="password2_field")
	public WebElement confirmPasswd;
	
	@FindBy(id="company_field")
	public WebElement companyName;
	
	@FindBy(xpath=".//*[@id='title_chzn']/a[@class='chzn-single']")
	public WebElement title_a;
	
	public WebElement title_select(String name){
		String xpt =".//*[@id='title_chzn']//li[text()='"+ name + "']";
		return driver.findElement(By.xpath(xpt));
	}
	
	@FindBy(id="first_name_field")
	public WebElement firstName;
	
	@FindBy(id="middle_name_field")
	public WebElement middleName;
	
	@FindBy(id="last_name_field")
	public WebElement lastName;
	
	@FindBy(id="address_1_field")
	public WebElement address1;
	
	@FindBy(id="address_2_field")
	public WebElement address2;
	
	@FindBy(id="zip_field")
	public WebElement zipCode;
	
	@FindBy(id="city_field")
	public WebElement city;
	
	@FindBy(xpath=".//*[@id='virtuemart_country_id_field_chzn']/a[@class='chzn-single']")
	public WebElement country_a;
	
	public WebElement country_select(String name){
		String xpt = ".//*[@id='virtuemart_country_id_field_chzn']//li[text()='" + name + "']";
		return driver.findElement(By.xpath(xpt));
	}
	
	@FindBy(id="phone_1_field")
	public WebElement phone;
	
	@FindBy(id="phone_2_field")
	public WebElement mobilePhone;
	
	@FindBy(id="fax_field")
	public WebElement fax;
	
	@FindBy(css=".values.vm2-email")
	public WebElement billEmail;
	
	@FindBy(css=".values.vm2-username")
	public WebElement billUserName;
	
	@FindBy(css=".values.vm2-name")
	public WebElement billDisplayedName;
	
	@FindBy(css=".values.vm2-company")
	public WebElement billCompanyName;
	
	@FindBy(css=".values.vm2-title")
	public WebElement billTitle;
	
	@FindBy(css=".values.vm2-first_name")
	public WebElement billFirstName;
	
	@FindBy(css=".values.vm2-middle_name")
	public WebElement billMiddleName;
	
	@FindBy(css=".values.vm2-last_name")
	public WebElement billLastName;
	
	@FindBy(css=".values.vm2-address_1")
	public WebElement billAddress1;
	
	@FindBy(css=".values.vm2-address_1")
	public WebElement billAddress2;
	
	@FindBy(css=".values.vm2-zip")
	public WebElement billZipCode;
	
	@FindBy(css=".values.vm2-city")
	public WebElement billCity;
	
	@FindBy(css=".values.vm2-virtuemart_country_id")
	public WebElement billCountry;
	
	@FindBy(css=".values.vm2-phone_1")
	public WebElement billPhone;
	
	@FindBy(css=".values.vm2-phone_2")
	public WebElement billMobilePhone;
	
	@FindBy(css=".values.vm2-fax")
	public WebElement billFax;
	
	
	
	// Define methods ****************************************************
	public void verifyProductOrder(List<HashMap<String,String>> productInfo,ExtentTest test){
		List<WebElement> listProducName = listProductName();
		if(productInfo.size()==listProducName.size()){
			test.log(LogStatus.PASS, "Number of products display corretcly.");
		}else{
			test.log(LogStatus.FAIL, "Number of products DO NOT display corretcly");
		}
		
		for(int i=0; i<productInfo.size(); i++){
			System.out.println("Data excel: " + productInfo.get(i).get("Quantity"));
			System.out.println("Data display: " + productQuantity(productInfo.get(i).get("ProductName")).getAttribute("value"));
			if(productQuantity(productInfo.get(i).get("ProductName")).getAttribute("value").equals(productInfo.get(i).get("Quantity"))){
				test.log(LogStatus.PASS, "Verify product name and product quantity for product \"" 
						+ productInfo.get(i).get("ProductName") + "\" is PASSED");
			}else{
				test.log(LogStatus.FAIL, "Verify product name and product quantity for product \"" 
						+ productInfo.get(i).get("ProductName") + "\" is FAILED");
			}
		}
		focus(productQuantity(productInfo.get(0).get("ProductName")));
		
		
	}
	
	public void selectTitle(String title){
		title_a.click();
		title_select(title).click();
	}
	
	public void selectCountry(String country){
		country_a.click();
		country_select(country).click();
	}
	
	public void inputGuestInfo(HashMap<String,String> guestInfo){
		email.sendKeys(guestInfo.get("Email"));
		userName.sendKeys(guestInfo.get("UserName"));
		displayName.sendKeys(guestInfo.get("DisplayedName"));
		passwd.sendKeys(guestInfo.get("Passwd"));
		confirmPasswd.sendKeys(guestInfo.get("ConfirmPasswd"));
		companyName.sendKeys(guestInfo.get("CompanyName"));
		selectTitle(guestInfo.get("Title"));
		firstName.sendKeys(guestInfo.get("FirstName"));
		middleName.sendKeys(guestInfo.get("MiddleName"));
		lastName.sendKeys(guestInfo.get("LastName"));
		address1.sendKeys(guestInfo.get("Address1"));
		address2.sendKeys(guestInfo.get("Address2"));
		zipCode.sendKeys(guestInfo.get("ZipCode"));
		city.sendKeys(guestInfo.get("City"));
		selectCountry(guestInfo.get("Country"));
		phone.sendKeys(guestInfo.get("Phone"));
		mobilePhone.sendKeys(guestInfo.get("MobilePhone"));
		fax.sendKeys(guestInfo.get("Fax"));
	}
}
