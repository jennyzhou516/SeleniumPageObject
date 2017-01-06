package page.test;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.LogStatus;

import page.object.Cart;
import pages.base.PageTest;

public class CartTest extends PageTest {
	private Cart cart;
	
	public void initialPageObject(){
		cart = new Cart();
	}
	
	@BeforeMethod
	public void beforeMethod(){
		initialPageObject();
	}
	
	public CartTest(){
		initialPageObject();
	}
	
	public void verifyProductOrder(List<HashMap<String,String>> productInfo){
		List<WebElement> listProducName = cart.listProductName();
		if(productInfo.size()==listProducName.size()){
			test.log(LogStatus.PASS, "Number of products display corretcly.");
		}else{
			test.log(LogStatus.FAIL, "Number of products DO NOT display corretcly");
		}
		
		for(int i=0; i<productInfo.size(); i++){
			if(cart.productQuantity(productInfo.get(i).get("ProductName")).getAttribute("value").equals(productInfo.get(i).get("Quantity"))){
				test.log(LogStatus.PASS, "Verify product name and product quantity for product \"" 
						+ productInfo.get(i).get("ProductName") + "\" is PASSED");
			}else{
				test.log(LogStatus.FAIL, "Verify product name and product quantity for product \"" 
						+ productInfo.get(i).get("ProductName") + "\" is FAILED");
			}
		}
		cart.focus(cart.productQuantity(productInfo.get(0).get("ProductName")));
		test.log(LogStatus.INFO, "list product order" + screenShoot());
		
	}
}
