package page.object;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import pages.base.PageObject;

public class SignUp extends PageObject {
	// Define elements ****************************************************
	@FindBy(name="btnG")
	public WebElement search_btn;
	
	@FindBy(id="lang-chooser")
	public WebElement langChooser;
	
	@FindBy(id="FirstName")
	public WebElement firstName;
	
	@FindBy(id="LastName")
	public WebElement lastName;
	
	@FindBy(id="GmailAddress")
	public WebElement gmailAddress;
	
	@FindBy(id="Passwd")
	public WebElement passwd;
	
	@FindBy(id="PasswdAgain")
	public WebElement passwdAgain;
	
	@FindBy(xpath=".//*[@id='BirthMonth']/div")
	public WebElement birthMonth;
	
	@FindBy(id="BirthDay")
	public WebElement birthDay;
	
	@FindBy(id="BirthYear")
	public WebElement birthYear;
	
	@FindBy(xpath=".//*[@id='Gender']/div")
	public WebElement gender;
	
	@FindBy(id="RecoveryPhoneNumber")
	public WebElement recoveryPhoneNumber;
	
	@FindBy(xpath=".//*[@id='CountryCode']/div")
	public WebElement countryCode;
	
	@FindBy(id="errormsg_0_FirstName")
	public WebElement errormsg_FirstName;
	
	@FindBy(id="errormsg_0_LastName")
	public WebElement errormsg_LastName;
	
	@FindBy(id="errormsg_0_GmailAddress")
	public WebElement errormsg_GmailAddress;
	
	@FindBy(id="errormsg_0_Passwd")
	public WebElement errormsg_Passwd;
	
	@FindBy(id="errormsg_0_PasswdAgain")
	public WebElement errormsg_PasswdAgain;
	
	@FindBy(id="errormsg_0_BirthMonth")
	public WebElement errormsg_BirthMonth;
	
	@FindBy(id="errormsg_0_BirthDay")
	public WebElement errormsg_BirthDay;
	
	@FindBy(id="errormsg_0_BirthYear")
	public WebElement errormsg_BirthYear;
	
	@FindBy(id="errormsg_0_Genderr")
	public WebElement errormsg_Gender;
	
	@FindBy(id="submitbutton")
	public WebElement nextStep;
	
	


	public SignUp(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// Define methods ****************************************************
	public void langChooser(String lang){
		Select lang_select = new Select(langChooser);
		lang_select.selectByValue("en");
		sleep(5);
		
	}
	
	public void inputData(HashMap<String,String> fieldData){
		
	}
}
