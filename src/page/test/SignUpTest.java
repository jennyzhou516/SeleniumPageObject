package page.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import page.object.SignUp;
import pages.base.PageTest;
import utilites.Excel;

public class SignUpTest extends PageTest {
	private WebDriver driver;
	private SignUp signUPPage ;
	
	@BeforeMethod
	public void setUp() {
		driver=getDriver();
		driver.navigate().to("https://accounts.google.com/SignUp");
		signUPPage=new SignUp(driver);
	}
	
	@Test
	public void demoExcelTestData(){
		test.log(LogStatus.INFO, "Verify create account with invalid data field");
//		signUPPage.langChooser("English (United States)");
		List<HashMap<String,String>> listData = new ArrayList<HashMap<String,String>>();
		listData =  Excel.readXSLXFile("test-data/CreateAcount.xlsx", "InvalidData");
		for(int i = 0; i< listData.size();i++){
			test.log(LogStatus.INFO, "Verify for case : " + listData.get(i).get("Description"));
			System.out.println("Verify for case : " + listData.get(i).get("Description"));
			signUPPage.inputData(listData.get(i));
			signUPPage.nextStep.click();
			signUPPage.sleep(5);
			test.log(LogStatus.INFO, "Screent Shoot after input data" + screenShoot());
			driver.navigate().to("https://accounts.google.com/SignUp");
		}
		
	}
	

}
