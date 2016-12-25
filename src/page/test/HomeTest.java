package page.test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import page.object.Home;
import page.object.Home1;
import pages.base.PageTest;

public class HomeTest extends PageTest {
	private WebDriver driver;
	private Home homeObject ;
	private Home1 home1Object ;
	
	@BeforeMethod
	public void setUp() {
		driver=getDriver();
		homeObject = new Home(driver);
		home1Object = new Home1(driver);
	}
	
	@Test
	public void verifyTitle() throws IOException{
		System.out.println("Verify title of home page");
		Assert.assertEquals(driver.getTitle(), "Google");
		test.log(LogStatus.PASS, "Pass");
        test.log(LogStatus.PASS, "Pass");
        test.log(LogStatus.PASS, "Pass");
        test.log(LogStatus.PASS, "Pass");
        test.log(LogStatus.INFO, "Capture a image! " + screenShoot());
        test.log(LogStatus.FAIL,"Failed......");
        test.log(LogStatus.FAIL,"Failed......");
		Assert.assertTrue(false, "hehehehehehe");
	}
	
	@Test
	public void searchWord(){
		homeObject.search_txt.sendKeys("Tho Hip");
		test.log(LogStatus.PASS, "Pass1");
        test.log(LogStatus.PASS, "Pass2");
        test.log(LogStatus.PASS, "Pass3");
        test.log(LogStatus.FAIL,"Failed...");
		home1Object.search_btn.click();
	}

}
