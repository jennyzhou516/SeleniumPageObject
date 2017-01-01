package page.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import page.object.Home;
import pages.base.PageTest;

public class HomeTest extends PageTest {
	private WebDriver driver;
	private Home homeObject;
	
	@BeforeMethod
	public void setUp(){
		driver=getDriver();
		homeObject= new Home(driver);
	}
	
	
	/*
	 Verify order a product with guest user is successfully
	 */
	@Test
	public void orderProduct(){
		homeObject.productCategory("Headpiece").click();
		homeObject.waitVisibleElement(homeObject.categoryHeader("Headpiece"), 30);
		homeObject.focus(homeObject.categoryHeader("Headpiece"));
		homeObject.quantityProduct_input("Cowboy Hat").clear();
		homeObject.quantityProduct_input("Cowboy Hat").sendKeys("10");
		homeObject.sleep(5);
//		homeObject.dropDownListSelectText(homeObject.sizeProduct_select("Cowboy Hat"), "Size: XL No additional charge");
		homeObject.dropDownListSelectIndex(homeObject.sizeProduct_select("Cowboy Hat"), 1);
		homeObject.sleep(5);
		test.log(LogStatus.PASS, "Order number of product:" + screenShoot());
	}
}
