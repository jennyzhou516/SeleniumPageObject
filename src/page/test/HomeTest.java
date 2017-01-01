package page.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import page.object.Home;
import page.object.PopupCart;
import pages.base.PageTest;
import utilites.Excel;

public class HomeTest extends PageTest {
	private WebDriver driver;
	private Home homeObject;
	private PopupCart popupCart;

	@BeforeMethod
	public void setUp(){
		driver=getDriver();
		homeObject= new Home(driver);
		popupCart = new PopupCart(driver);
	}

	/*
	 Verify order a product with guest user is successfully
	 */
	@Test
	public void orderProduct(){
		List<HashMap<String,String>> listData = new ArrayList<HashMap<String,String>>();
		listData= Excel.readXSLXFile("test-data/Product.xlsx", "AddProduct");

		for(int i=0; i<listData.size(); i++){
			selectCategory(listData.get(i).get("Category"));
			addProductAndVerify(listData.get(i));
			homeObject.clickUtilClickable(popupCart.closeWindow, 30);
			if(i<listData.size()-1) homeObject.clickUtilClickable(homeObject.home_link, 30);
		}
		homeObject.sleep(10);
	}

	public void selectCategory(String category){
		homeObject.waitElementVisible(homeObject.productCategory(category),30);
		homeObject.productCategory(category).click();
		homeObject.waitElementVisible(homeObject.categoryHeader(category), 30);
		homeObject.focus(homeObject.categoryHeader(category));
	}

	public void addProductAndVerify(HashMap<String,String> productData){
		homeObject.addProduct(productData);
		test.log(LogStatus.PASS, "Information of ordering-product: " + screenShoot());
		homeObject.addToCart_button(productData.get("ProductName")).click();
		vefiryProductInfoOnCartPopUp(productData.get("ProductName"),productData.get("Quantity"));
	}

	public void vefiryProductInfoOnCartPopUp(String productName,String qty){
		String productInfo = qty + " x " + productName + " was added to your cart.";
		popupCart.waitElementVisible(popupCart.continueShop_link, 30);
		String productInfoDisp = popupCart.productInfo_text.getText();
		popupCart.sleep(1);
		if(productInfo.equals(productInfoDisp)){
			test.log(LogStatus.PASS, "Product information on cart is correctly" + screenShoot());
		}else{
			test.log(LogStatus.FAIL, "Product information on cart is NOT correctly. " 
					+ "Expected: \"" + productInfo + "\" - Actual: \"" + productInfoDisp + "\"" + screenShoot());
		}
	}
}
