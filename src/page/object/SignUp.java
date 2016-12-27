package page.object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import pages.base.PageObject;

public class SignUp extends PageObject {
	// Define elements ****************************************************
	@FindBy(name="btnG")
	public WebElement search_btn;
	
	@FindBy(id="lang-chooser")
	public WebElement langChooser;


	public SignUp(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// Define methods ****************************************************
	public void langChooser(String lang){
		Select lang_select = new Select(langChooser);
		lang_select.selectByValue("en");
		sleep(5);
		
	}
}
