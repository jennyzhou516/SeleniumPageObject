package page.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import page.object.SignUp;
import pages.base.PageTest;

public class SignUpTest extends PageTest {
	private WebDriver driver;
	private SignUp signUPPage ;
	
	@BeforeMethod
	public void setUp() {
		driver=getDriver();
		driver.navigate().to("https://accounts.google.com/SignUp?continue=https%3A%2F%2Fwww.google.com.vn%2F%3Fgfe_rd%3Dcr%26ei%3DxdhfWIvfIZCB4AKY0JDYCQ%26gws_rd%3Dssl&hl=vi");
		signUPPage=new SignUp(driver);
	}
	
	@Test
	public void demoExcelTestData(){
		test.log(LogStatus.PASS, "Test case is passed");
		signUPPage.langChooser("English (United States)");
		
	}
	

}
