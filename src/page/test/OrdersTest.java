package page.test;

import java.util.HashMap;

import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.LogStatus;

import page.object.Orders;
import pages.base.PageTest;

public class OrdersTest extends PageTest{
	private Orders orders;
	
	public void initialPageObject(){
		orders = new Orders();
	}
	
	@BeforeMethod
	public void beforeMethod(){
		initialPageObject();
	}
	
	public OrdersTest(){
		initialPageObject();
	}
	
	public void verifyBillToAndShipToInfo(HashMap<String,String> info){
		orders.waitElementVisible(orders.billToInfo("E-Mail"), 30);
		test.log(LogStatus.INFO, "Verify Bill To information on order*********************");
		elementTextEqual("Email", orders.billToInfo("E-Mail"), info.get("Email"));
		elementTextEqual("Company Name", orders.billToInfo("Company Name"), info.get("CompanyName"));
		elementTextEqual("Title", orders.billToInfo("Title"), info.get("Title"));
		elementTextEqual("First Name", orders.billToInfo("First Name"), info.get("FirstName"));
		if (info.get("MiddleName")!=""){
			elementTextEqual("Middle Name", orders.billToInfo("Middle Name"), info.get("MiddleName"));
		}
		elementTextEqual("Last Name", orders.billToInfo("Last Name"), info.get("LastName"));
		elementTextEqual("Address 1", orders.billToInfo("Address 1"), info.get("Address1"));
		elementTextEqual("Address 2", orders.billToInfo("Address 2"), info.get("Address2"));
		elementTextEqual("Zip / Postal Code", orders.billToInfo("Zip / Postal Code"), info.get("ZipCode"));
		elementTextEqual("City", orders.billToInfo("City"), info.get("City"));
		elementTextEqual("Country", orders.billToInfo("Country"), info.get("Country"));
		elementTextEqual("Phone", orders.billToInfo("Phone"), info.get("Phone"));
		elementTextEqual("Mobile phone", orders.billToInfo("Mobile phone"), info.get("MobilePhone"));
		elementTextEqual("Fax", orders.billToInfo("Fax"), info.get("Fax"));
		
		test.log(LogStatus.INFO, "Verify Ship To information on order*********************");
		elementTextEqual("Company Name", orders.shipToInfo("Company Name"), info.get("CompanyName"));
		elementTextEqual("First Name", orders.shipToInfo("First Name"), info.get("FirstName"));
		if (info.get("MiddleName")!=""){
			elementTextEqual("Middle Name", orders.shipToInfo("Middle Name"), info.get("MiddleName"));
		}
		elementTextEqual("Last Name", orders.shipToInfo("Last Name"), info.get("LastName"));
		elementTextEqual("Address 1", orders.shipToInfo("Address 1"), info.get("Address1"));
		elementTextEqual("Address 2", orders.shipToInfo("Address 2"), info.get("Address2"));
		elementTextEqual("Zip / Postal Code", orders.shipToInfo("Zip / Postal Code"), info.get("ZipCode"));
		elementTextEqual("City", orders.shipToInfo("City"), info.get("City"));
		elementTextEqual("Country", orders.shipToInfo("Country"), info.get("Country"));
		elementTextEqual("Phone", orders.shipToInfo("Phone"), info.get("Phone"));
		elementTextEqual("Mobile phone", orders.shipToInfo("Mobile phone"), info.get("MobilePhone"));
		elementTextEqual("Fax", orders.shipToInfo("Fax"), info.get("Fax"));
		test.log(LogStatus.INFO, "Bill to and Ship to infomation" + screenShoot());
	}
}
