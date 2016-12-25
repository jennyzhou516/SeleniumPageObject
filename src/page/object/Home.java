package page.object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base.PageObject;

public class Home extends PageObject{
	// Define elements ****************************************************
	@FindBy(xpath = ".//*[@id='lst-ib']")
	public WebElement search_txt;
	
	@FindBy(xpath = ".//*[@id='gb_70' and @class='gb_Te gb_Ha gb_wb']")
	public WebElement signUp_btn;
	
	
	public Home(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}
}
