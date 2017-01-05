package pages.base;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilites.DateTime;
import utilites.FolderFile;


public class PageTest {
	public static  WebDriver driver;
	private static String driverPath = "./web-driver";
	private static String appURL = "http://tranminhthanh.info/training/selenium/VirtualMart/";

	private static ExtentReports extent;
	public static ExtentTest test;

	private static String reportFolder="";

	final String reportPath = "./Extent.html";

	public WebDriver getDriver() {
		return driver;
	}

	private void setDriver(String browserType){
		switch(browserType){
		case "chrome":
			driver=initChromeDriver(appURL);
			break;
		case "firefox":
			driver = initFirefoxDriver(appURL);
			break;
		default:
			System.out.println("browser : " + browserType
					+ " is invalid, Launching Firefox as browser of choice..");
			driver = initFirefoxDriver(appURL);
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	private static WebDriver initChromeDriver(String appURL){

		System.out.println("Launching google chrome with new profile..");
		System.setProperty("webdriver.chrome.driver", driverPath + "/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}

	private static WebDriver initFirefoxDriver(String appURL){
		System.out.println("Launching Firefox browser..");
		System.setProperty("webdriver.firefox.marionette", driverPath + "/geckodriver_win64.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		return driver;
	}

	@BeforeSuite
	protected void beforeSuite() {
		reportFolder = DateTime.getCurrentTime("MM-dd-yyyy_HHmmss");
		String reportPath = "test-reports/" + reportFolder + "/images";
		FolderFile.createMutilFolder(reportPath);
		extent = ExtentManager.getReporter("test-reports/" + reportFolder + "/ExtentReport.html");
	}


	@BeforeClass
	protected synchronized void beforeClass() {
	} 

	@Parameters({"browserType"})
	@BeforeMethod
	protected synchronized void beforeMethod(String browserType,Method method){
		System.out.println("*********************** Start :" + method.getName() +" ***********************");
		try {
			setDriver(browserType);

		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
		driver.navigate().to(appURL);
		test = extent.startTest(method.getName())
				.assignCategory(getClass().getSimpleName());
	}

	@AfterMethod
	protected synchronized void afterMethodd(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, result.getThrowable());
			test.log(LogStatus.FAIL, result.getThrowable() + screenShoot());
		} 

		extent.endTest(test);        
		extent.flush();
		driver.quit();
	}

	@AfterClass
	protected void afterClass() {
		//			driver.quit();
	}

	@AfterSuite
	protected void afterSuite() {
		extent.close();
	}
	
	//Methods for test report ***********************************************
	
	public String screenShoot(){
		String imgPath ="";
		String imgName = DateTime.getCurrentTime("MM-dd-yyyy_HHmmss") + ".png";
		try {
			File scrnsht = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File path=new File("").getAbsoluteFile();
			String pathfile = path.toString() + "\\test-reports\\" + reportFolder + "\\images\\" + imgName;
			FileUtils.copyFile(scrnsht, new File(pathfile));
		} catch (Exception e) {
			e.printStackTrace();
		}
		imgPath = test.addScreenCapture("./images/" + imgName);
		return imgPath;
	}
	
	public void elementTextEqual(String dec,WebElement wel,String text){
		if(wel.getText().equals(text)){
			test.log(LogStatus.PASS, dec + " " + " was verify passed");
		}else{
			test.log(LogStatus.FAIL, dec + " " + " was verify failed. Expected: "+ text+ " - Actual: "+ wel.getText() + screenShoot());
		}
	}
	
	public void elementTextContain(String dec,WebElement wel,String text){
		if(wel.getText().contains(text)){
			test.log(LogStatus.PASS, dec + " " + " was verify passed");
		}else{
			test.log(LogStatus.FAIL, dec + " " + " was verify failed. Expected: "+ text+ " - Actual: "+ wel.getText() + screenShoot());
		}
	}
	
	public void elementTextMatches(String dec,WebElement wel,String regex){
		if(wel.getText().matches(regex)){
			test.log(LogStatus.PASS, dec + " " + " was verify passed");
		}else{
			test.log(LogStatus.FAIL, dec + " " + " was verify failed. Expected: "+ regex+ " - Actual: "+ wel.getText() + screenShoot());
		}
	}
}
