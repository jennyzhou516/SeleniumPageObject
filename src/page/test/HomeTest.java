package page.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import page.object.Home;
import pages.base.PageTest;

public class HomeTest extends PageTest {
	private WebDriver driver;
	private Home homeObject ;
	
	@BeforeClass
	public void setUp() {
		driver=getDriver();
	}
	
	@Test
	public void verifyTitle(){
		System.out.println("Verify title of home page");
		Assert.assertEquals(driver.getTitle(), "Google");
	}
	
	@Test
	public void searchWord(){
		homeObject = new Home(driver);
		homeObject.search_txt.sendKeys("Tho Hip");
	}

}
