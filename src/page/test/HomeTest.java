package page.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import page.object.Cart;
import page.object.Home;
import page.object.PopupCart;
import pages.base.PageTest;
import utilites.Excel;

public class HomeTest extends PageTest {
	private WebDriver driver;
	private Home homeObject;
	private PopupCart popupCart;
	private Cart cart;

	@BeforeMethod
	public void setUp(){
		driver=getDriver();
		homeObject= new Home(driver);
		popupCart = new PopupCart(driver);
		cart = new Cart(driver);
 	}

	/*
	 Verify order a product with guest user is successfully
	 */
	@Test
	public void orderProduct(){
		List<HashMap<String,String>> listData = new ArrayList<HashMap<String,String>>();
		listData= Excel.readXSLXFile("test-data/Product.xlsx", "AddProduct");
		
		// Order products
		test.log(LogStatus.INFO, "Step 1 : Order products ***************************************");
		for(int i=0; i<listData.size(); i++){
			selectCategory(listData.get(i).get("Category"));
			addProductAndVerify(listData.get(i));
			homeObject.clickUtilClickable(popupCart.closeWindow, 30);
			if(i<listData.size()-1) homeObject.clickUtilClickable(homeObject.home_link, 30);
		}
		
		//Check out order
		homeObject.clickUtilClickable(homeObject.cart_link,30);
		cart.waitElementClickable(cart.checkOut_button, 30);
		
		test.log(LogStatus.INFO, "Step 2 : Verify products on cart ***************************************");
		cart.verifyProductOrder(listData,test);
//		CartTest cartTest = new CartTest();
//		cartTest.verifyProductOrder(listData,driver);
		test.log(LogStatus.INFO, "List product order" + screenShoot());
		test.log(LogStatus.INFO, "Step 2 : Check out cart with guest user ***************************************");
		cart.clickUtilClickable(cart.checkOut_button, 30);
		cart.waitElementVisible(cart.checkOutGuest, 30);
		List<HashMap<String,String>> listGuestInfo = Excel.readXSLXFile("test-data/Product.xlsx", "GuestUser");
		cart.inputGuestInfo(listGuestInfo.get(0));
		cart.clickUtilClickable(cart.checkOutGuest, 30);;
		cart.termsOfService.click();
		verifyBillToOfGuestUser(listGuestInfo.get(0));
		cart.sleep(10);
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
	
	public void verifyBillToOfGuestUser(HashMap<String,String> guestInfo){
		cart.waitElementVisible(cart.billEmail, 10);
		
		test.log(LogStatus.INFO, "Verify Bill To infomation.");
		elementTextEqual("Email", cart.billEmail, guestInfo.get("Email"));
		elementTextEqual("User Name", cart.billUserName, guestInfo.get("UserName"));
		elementTextEqual("Displayed Name", cart.billDisplayedName, guestInfo.get("DisplayedName"));
		elementTextEqual("Company", cart.billCompanyName, guestInfo.get("CompanyName"));
		elementTextEqual("Title", cart.billTitle, guestInfo.get("Title"));
		elementTextEqual("First Name", cart.billFirstName, guestInfo.get("FirstName"));
//		elementTextEqual("Middle Name", cart.billMiddleName, guestInfo.get("MiddleName"));
		elementTextEqual("Last Name", cart.billLastName, guestInfo.get("LastName"));
		elementTextEqual("Address1", cart.billAddress1, guestInfo.get("Address1"));
		elementTextEqual("Address2", cart.billAddress2, guestInfo.get("Address2"));
		elementTextEqual("Zip Code", cart.billZipCode, guestInfo.get("ZipCode"));
		elementTextEqual("Country", cart.billCountry, guestInfo.get("Country"));
		elementTextEqual("Phone", cart.billPhone, guestInfo.get("Phone"));
		elementTextEqual("Mobile Phone", cart.billMobilePhone, guestInfo.get("MobilePhone"));
		elementTextEqual("Fax", cart.billFax, guestInfo.get("Fax"));
		
	}
}
