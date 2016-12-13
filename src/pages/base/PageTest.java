package pages.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class PageTest {
	private WebDriver driver;
	static String driverPath = "./web-driver";
	static String appURL = "https://www.google.com.vn/";

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
		driver.navigate().to(appURL);
		return driver;
	}

	private static WebDriver initFirefoxDriver(String appURL){
		System.out.println("Launching Firefox browser..");
		System.setProperty("webdriver.firefox.marionette", driverPath + "geckodriver_win64.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	@Parameters({"browserType"})
	@BeforeClass
	public void initializeTestBaseSetup(String browserType) {
		try {
			setDriver(browserType);

		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	}

	@BeforeMethod
	public void startBeforeMethod(){
		driver.navigate().to(appURL);
	}

	@AfterMethod
	public void tearDownAfterMethod(ITestResult result) {
		if(ITestResult.FAILURE==result.getStatus()){
			try{
				System.out.println("Capture iamge and attach to report...");
			}
			catch (Exception e) {
				System.out.println("Exception while taking screenshot "+e.getMessage());
			}
		}
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
