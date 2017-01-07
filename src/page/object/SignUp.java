package page.object;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.base.PageObject;

public class SignUp extends PageObject {
	
	public SignUp(){
		PageFactory.initElements(driver, this);
	}
	
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
	
	@FindBy(xpath=".//*[@id='BirthMonth']//*[@class='goog-menuitem']")
	public List<WebElement> birthMonthList;

	@FindBy(id="BirthDay")
	public WebElement birthDay;

	@FindBy(id="BirthYear")
	public WebElement birthYear;

	@FindBy(xpath=".//*[@id='Gender']/div")
	public WebElement gender;
	
	@FindBy(xpath=".//*[@id='Gender']//*[@class='goog-menuitem']")
	public List<WebElement> genderList;

	@FindBy(id="RecoveryPhoneNumber")
	public WebElement recoveryPhoneNumber;
	
	@FindBy(id="RecoveryEmailAddress")
	public WebElement recoveryEmailAddress;

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


	// Define methods ****************************************************
	public void langChooser(String lang){
		dropDownListSelectValue(langChooser,"en");
		System.out.println("Select month: " + dropDownListDivWithName(birthMonth, birthMonthList, "January"));
		System.out.println("Select gender: " + dropDownListDivWithName(gender, genderList, "Female"));
		recoveryPhoneNumber.sendKeys("123456");
		nextStep.click();
		sleep(5);
	}

	public void inputData(HashMap<String,String> fieldData){
		lastName.sendKeys(fieldData.get("LastName"));
		gmailAddress.sendKeys(fieldData.get("UserName"));
		passwd.sendKeys(fieldData.get("Passwd"));
		passwdAgain.sendKeys(fieldData.get("PasswdConfirm"));
		if (fieldData.get("BirthMonth")!=""){
			dropDownListDivWithName(birthMonth, birthMonthList, fieldData.get("BirthMonth"));
		}
		birthDay.sendKeys(fieldData.get("BirthDay"));
		birthYear.sendKeys(fieldData.get("BirthYear"));
		if(fieldData.get("Gender")!=""){
			dropDownListDivWithName(gender, genderList, fieldData.get("Gender"));
		}
		recoveryPhoneNumber.sendKeys(fieldData.get("PhoneNumber"));
		recoveryEmailAddress.sendKeys(fieldData.get("CurrentEmail"));
		firstName.sendKeys(fieldData.get("FirstName"));
	}
}
