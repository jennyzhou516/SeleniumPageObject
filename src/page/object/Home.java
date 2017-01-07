package page.object;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base.PageObject;

public class Home extends PageObject{
	
	public Home(){
		PageFactory.initElements(driver, this);
	}
	// Define elements ****************************************************
	@FindBy(xpath = ".//*[@id='lst-ib']")
	public WebElement search_txt;
	
	@FindBy(xpath = ".//*[@id='gb_70' and @class='gb_Te gb_Ha gb_wb']")
	public WebElement signUp_btn;
}
