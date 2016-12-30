package page.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
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
			test.log(LogStatus.INFO, "**********************************************************************************");
			test.log(LogStatus.INFO, "Verify for case : " + listData.get(i).get("Description"));
			System.out.println("Verify for case : " + listData.get(i).get("Description"));
			signUPPage.inputData(listData.get(i));
			test.log(LogStatus.INFO, "Screent Shoot after input data" + screenShoot());
			//			signUPPage.nextStep.click();
			signUPPage.sleep(3);
			verifyMessageError(listData.get(i));
			driver.navigate().to("https://accounts.google.com/SignUp");
		}

	}
	
	@Test(dataProvider="demoExcelDataproviderGetData")
	public void demoExcelDataprovider(HashMap<String,String> dataRow ){
		test.log(LogStatus.INFO, "**********************************************************************************");
		test.log(LogStatus.INFO, "Verify for case : " + dataRow.get("Description"));
		System.out.println("Verify for case : " + dataRow.get("Description"));
		signUPPage.inputData(dataRow);
		test.log(LogStatus.INFO, "Screent Shoot after input data" + screenShoot());
		signUPPage.sleep(3);
		verifyMessageError(dataRow);
	}
	
	@DataProvider
	public static Object[][] demoExcelDataproviderGetData(){
		Object[][] obj = Excel.readXSLXFileDataProvider("test-data/CreateAcount.xlsx", "InvalidData");
		return obj;
	}

	private void verifyMessageError(HashMap<String,String> fieldData){
		if(fieldData.get("LastNameMesEr")!=""){
			elementTextEqual("Last Name message error",signUPPage.errormsg_LastName,fieldData.get("LastNameMesEr"));
		}else{
			if(fieldData.get("FirstNameMesEr")!=""){
				elementTextEqual("First Name message error",signUPPage.errormsg_FirstName,fieldData.get("FirstNameMesEr"));
			}
		}
		if(fieldData.get("UserNameMesEr")!=""){
			elementTextEqual("User name message error", signUPPage.errormsg_GmailAddress, fieldData.get("UserNameMesEr"));
		}
	}


}
