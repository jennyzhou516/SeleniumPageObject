package page.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.object.Home;
import page.object.Home1;
import pages.base.PageTest;

public class HomeTest1 extends PageTest {
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
	public void verifyTitle(){
		System.out.println("Verify title of home page");
		Assert.assertEquals(driver.getTitle(), "Google");
	}
	
	@Test
	public void searchWord(){
		homeObject.search_txt.sendKeys("Tho Hip");
		home1Object.search_btn.click();
	}

}
