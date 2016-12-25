package pages.base;

import org.openqa.selenium.WebDriver;

public class PageObject {
	protected WebDriver driver;
	
	public PageObject(WebDriver driver){
		this.driver = driver;
	}
	
	public void sleep(int time){
		try{
			Thread.sleep(time*1000);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
