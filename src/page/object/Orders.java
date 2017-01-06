package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.base.PageObject;

public class Orders extends PageObject {

	public Orders(){
		PageFactory.initElements(driver, this);
	}

	// Define elements ****************************************************
	
	@FindBy(xpath=".//*[@class='vm-orders-information']")
	public WebElement orderInfo_SS;
	
	public WebElement billToInfo(String infoName){
		String xpt =".//*[contains(text(),'Bill To')]/parent::td//*[contains(text(),'"+ infoName + "')]/following-sibling::td";
		return driver.findElement(By.xpath(xpt));
	}
	
	public WebElement shipToInfo(String infoName){
		String xpt =".//*[contains(text(),'Ship To')]/parent::td//*[contains(text(),'"+ infoName + "')]/following-sibling::td";
		return driver.findElement(By.xpath(xpt));
	}
}
