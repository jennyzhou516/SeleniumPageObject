package page.object;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.base.PageObject;

public class PopupCart extends PageObject{
	public PopupCart(){
		PageFactory.initElements(driver, this);
	}

	// Define elements ****************************************************
	
	@FindBy(xpath=".//*[@class='continue_link']")
	public WebElement continueShop_link;
	
	@FindBy(xpath=".//a[@class='showcart floatright' or text()='Show Cart']")
	public WebElement showCart_link;
	
	@FindBy(xpath=".//*[@id='fancybox-content']/div/h4")
	public WebElement productInfo_text;
	
	@FindBy(xpath=".//*[@id='fancybox-close']")
	public WebElement closeWindow;
}
