package page.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import page.object.Home;
import page.object.SignUp;
import pages.base.PageTest;

public class HomeTest extends PageTest {
	private Home homeObject ;
	private SignUp signUPPage ;


	public void initialPageObject(){
		homeObject = new Home();
		signUPPage = new SignUp();
	}
	@BeforeMethod
	public void beforeMethod(){
		initialPageObject();
	}

	public HomeTest() {
		initialPageObject();
	}


	@Test
	public void demoPassedTestCase(){
		Assert.assertEquals(driver.getTitle(), "Google");
		test.log(LogStatus.PASS, "Home page title is correctly.");
		test.log(LogStatus.INFO, "Screen shoot" + shuttlePage());
		test.log(LogStatus.PASS, "Step 1 is passed");
		test.log(LogStatus.PASS, "Step 2 is passed");
		test.log(LogStatus.PASS, "Step 3 is passed");
	}

	@Test
	public void demoFailedTestCase(){
		test.log(LogStatus.PASS, "Step 1 is passed");
		test.log(LogStatus.PASS, "Step 2 is passed");
		test.log(LogStatus.PASS, "Step 3 is passed");
		Assert.assertTrue(false, "assert is failed");
	}

	@Test
	public void demoWarningTestCase(){
		test.log(LogStatus.PASS, "Step 1 is passed");
		test.log(LogStatus.WARNING, "Step 2 is warning");
		test.log(LogStatus.PASS, "Step 3 is passed");
	}

	@Test
	public void demoManyStepStatus(){
		homeObject.search_txt.sendKeys("Tho Hip");
		signUPPage.search_btn.click();
		homeObject.sleep(3);
		test.log(LogStatus.PASS, "Step 1 is passed");
		test.log(LogStatus.INFO, "Screen shoot" + shuttlePage());
		test.log(LogStatus.WARNING, "Step 2 is warning");
		test.log(LogStatus.FAIL, "Step 3 is failed");
	}

}
