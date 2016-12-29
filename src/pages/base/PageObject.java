package pages.base;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

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
		System.out.println("Size of drop down list: " + list.size());
		for(int i=0; i<list.size();i++){
			System.out.println("item on list "+ i + ": " + list.get(i).getText());
		}
		for(int i=0; i<list.size();i++){
			if(list.get(i).getText().equals(name)){
				System.out.println("Select item: " + name);
				list.get(i).click();
				break;
			}
		}
		return openList.getText();
	}
}
