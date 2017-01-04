package page.object;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base.PageObject;

public class Home extends PageObject{
	// Define elements ****************************************************

	@FindBy(xpath=".//*[@id='menuwrapper']//a[text()='Home']")
	public WebElement home_link;

	@FindBy(xpath=".//*[@id='menuwrapper']//a[text()='Cart']")
	public WebElement cart_link;

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

	public WebElement sizeProduct_a(String productName){
		String xpt=". //*[text()='" + productName + "']/ancestor::div[@class='spacer product-container']//*[@class='product-field product-field-type-S'][1]//a[@class='chzn-single']";
		return driver.findElement(By.xpath(xpt));
	}

	public WebElement sizeProduct_select(String productName,String size){
		String xpt=". //*[text()='" + productName + "']/ancestor::div[@class='spacer product-container']//ul[@class='chzn-results']/li[text()='" + size + "']";
		return driver.findElement(By.xpath(xpt));
	}

	// Define methods ****************************************************

	public void inputQuantityProduct(String productName,String qty){
		quantityProduct_input(productName).clear();
		quantityProduct_input(productName).sendKeys(qty);
	}

	public void selectSizeProduct(String productName,String size){
		sizeProduct_a(productName).click();
		sizeProduct_select(productName,size).click();
	}

	public void addProduct(HashMap<String,String> hsm){
		inputQuantityProduct(hsm.get("ProductName"), hsm.get("Quantity"));
		try {
			selectSizeProduct(hsm.get("ProductName"), hsm.get("Size"));
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}
}
