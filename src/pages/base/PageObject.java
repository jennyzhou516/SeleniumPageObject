package pages.base;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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

	// Explicit wait time
	// Wait time
	private WebDriverWait initWait(int time) {
		return new WebDriverWait(driver, time);
	}

	public void waitVisibleElement(WebElement we, int time) {
		WebDriverWait wa = initWait(time);
		wa.until(ExpectedConditions.visibilityOf(we));
	}

	public void waitInvisibleElement(By by, int time) {
		WebDriverWait wa = initWait(time);
		wa.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	public void waitVisibleListElements(ArrayList<WebElement> we, int time) {
		WebDriverWait wa = initWait(time);
		wa.until(ExpectedConditions.visibilityOfAllElements(we));
	}

	// Focus a element
	public void focus(WebElement wel){
		if("input".equals(wel.getTagName())){
			wel.sendKeys("");
		} 
		else{
			new Actions(driver).moveToElement(wel).perform();

		}
	}

	// Drop down list support
	public void dropDownListSelectText(WebElement select,String text){
		Select sl= new Select(select);
		sl.selectByIndex(2);
		List<WebElement> options = sl.getOptions();
		for (WebElement option : options) {
		    System.out.println("Option name: " + option.getText());
		}
	}
	
	public void dropDownListSelectIndex(WebElement select,int index){
		Select sl= new Select(select);
		sl.selectByIndex(index);
	}

	public void dropDownListSelectValue(WebElement select,String value){
		Select sl= new Select(select);
		sl.selectByValue(value);
	}

	public String dropDownListDivWithName(WebElement openList,List<WebElement> list,String name){
		openList.click();
		for(int i=0; i<list.size();i++){
			if(list.get(i).getText().equals(name)){
				list.get(i).click();
				break;
			}
		}
		return openList.getText();
	}

}
