package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base.PageObject;

public class Home extends PageObject{
	// Define elements ****************************************************

	public Home(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public WebElement productCategory(String name){
		String xpt=".//*[contains(@class,'category floatleft width33')]//*[contains(text(),'" + name + "')]";
		return driver.findElement(By.xpath(xpt));
	}

	public WebElement categoryHeader(String name){
		String xpt=".//*[@class='browse-view']/h1[text()='" + name + "']";
		return driver.findElement(By.xpath(xpt));
	}
	
	public WebElement quantityProduct_input(String productName){
		String xpt=". //*[text()='" + productName + "']/ancestor::div[@class='spacer product-container']//*[@class='quantity-input js-recalculate']";
		return driver.findElement(By.xpath(xpt));
	}
	
	public WebElement addToCart_button(String productName){
		String xpt=". //*[text()='" + productName + "']/ancestor::div[@class='spacer product-container']//input[@class='addtocart-button']";
		return driver.findElement(By.xpath(xpt));
	}
	
	public WebElement sizeProduct_select(String productName){
		String xpt=". //*[text()='" + productName + "']/ancestor::div[@class='spacer product-container']//*[@class='product-field product-field-type-S'][1]//select[@class='vm-chzn-select chzn-done']";
		return driver.findElement(By.xpath(xpt));
	}
}
