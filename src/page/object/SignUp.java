package page.object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.base.PageObject;

public class SignUp extends PageObject {
	// Define elements ****************************************************
		@FindBy(name="btnG")
		public WebElement search_btn;
		
		
		public SignUp(WebDriver driver){
			super(driver);
			PageFactory.initElements(driver, this);
		}
}
