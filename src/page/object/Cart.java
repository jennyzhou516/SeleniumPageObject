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

	public WebElement productQuantity(String productName){
		String xpt=".//*[@class='vm-cart-item-name']/a[text()='" + productName 
				+ "']/ancestor::tr[contains(@class,'sectiontableentry')]//input[@class='quantity-input js-recalculate']";
		return driver.findElement(By.xpath(xpt));
	}
	
	public List<WebElement> listProductName(){
		String xpt =".//*[@class='vm-cart-item-name']/a";
		return driver.findElements(By.xpath(xpt));
	}
	
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
}
