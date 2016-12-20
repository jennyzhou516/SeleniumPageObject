package pages.base;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class PageTest {
	private WebDriver driver;
	static String driverPath = "./web-driver";
	static String appURL = "https://www.google.com.vn/";
	
	private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal();

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
	}

	private static WebDriver initChromeDriver(String appURL){

		System.out.println("Launching google chrome with new profile..");
		System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver_win.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}

	private static WebDriver initFirefoxDriver(String appURL){
		System.out.println("Launching Firefox browser..");
		System.setProperty("webdriver.firefox.marionette", driverPath + "geckodriver_win64.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeSuite
	public void beforeSuite() {
		extent = ExtentManager.createInstance("extent.html");
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
		extent.attachReporter(htmlReporter);
	}
	

	@BeforeClass
	public synchronized void beforeClass() {
//		try {
//			setDriver(browserType);
//
//		} catch (Exception e) {
//			System.out.println("Error....." + e.getStackTrace());
//		}
		ExtentTest parent = extent.createTest(getClass().getName());
        parentTest.set(parent);
	} 

	@Parameters({"browserType"})
	@BeforeMethod
	public synchronized void beforeMethod(String browserType,Method method){
		System.out.println("*********************** Start :" + method.getName() +" ***********************");
		try {
			setDriver(browserType);

		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
		driver.navigate().to(appURL);
		ExtentTest child = parentTest.get().createNode(method.getName());
        test.set(child);
	}

	@AfterMethod
	public synchronized void afterMethodd(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE)
            test.get().fail(result.getThrowable());
//			test.fail("details").addScreenCaptureFromPath("screenshot.png");
        else if (result.getStatus() == ITestResult.SKIP)
            test.get().skip(result.getThrowable());
        else
            test.get().pass("Test passed");

        extent.flush();
		
		if(ITestResult.FAILURE==result.getStatus()){
			try{
				System.out.println("Capture iamge and attach to report...");
			}
			catch (Exception e) {
				System.out.println("Exception while taking screenshot "+e.getMessage());
			}
		}
		driver.quit();
	}

		@AfterClass
		public void tearDown() {
//			driver.quit();
		}

}
