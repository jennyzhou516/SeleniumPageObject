package page.object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.base.PageObject;

public class Home1 extends PageObject {
	// Define elements ****************************************************
		@FindBy(name="btnG")
		public WebElement search_btn;
		
		
		public Home1(WebDriver driver){
			super(driver);
			PageFactory.initElements(driver, this);
		}
}
