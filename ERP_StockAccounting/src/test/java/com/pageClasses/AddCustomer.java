package com.pageClasses;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class AddCustomer {
	WebDriver driver;
	//constructor for invoking methods
	public AddCustomer(WebDriver driver)
	{
		this.driver=driver;
	}
	//define Repository
	@FindBy(xpath = "(//a[starts-with(text(),'Customers')])[2]")
	WebElement click_Customers;
	@FindBy(xpath = "(//span[@data-caption='Add'])[1]")
	WebElement click_Addicon;
	@FindBy(name ="x_Customer_Number")
	WebElement capture_customernumber;
	@FindBy(name ="x_Customer_Name")
	WebElement enter_customername;
	@FindBy(name = "x_Address")
	WebElement enter_Address;
	@FindBy(name ="x_City")
	WebElement enter_City;
	@FindBy(name ="x_Country")
	WebElement Enter_Country;
	@FindBy(name ="x_Contact_Person")
	WebElement enter_contactperson;
	@FindBy(name = "x_Phone_Number")
	WebElement enter_phoneNumber;
	@FindBy( name = "x__Email")
	WebElement enter_Email;
	@FindBy( name = "x_Mobile_Number")
	WebElement enter_mobileNumber;
	@FindBy( name="x_Notes")
	WebElement enter_Notes;
	@FindBy(id ="btnAction")
	WebElement clickAddbtn;
	@FindBy(xpath = "//button[normalize-space()='OK!']")
	WebElement click_confirmOk;
	@FindBy(xpath = "(//button[starts-with(text(),'OK')])[6]")
	WebElement click_AlertOk;
	@FindBy(xpath = "//span[@data-caption='Search']")
	WebElement click_searchPanel;
	@FindBy(xpath = "//input[@id='psearch']")
	WebElement enter_searchTextbox;
	@FindBy(xpath = "//button[@id='btnsubmit']")
	WebElement click_SearchButton;
	@FindBy(xpath = "//table[@class='table ewTable']/tbody/tr[1]/td[5]/div/span/span")
	WebElement webtable;
	//method for validating supplier
	public boolean validate_customer(String customername,String Address,String City,String Country,
			String contactPerson,String phonenumber,String Email,String mobileNumber,String Notes) throws Throwable
	{
		Actions ac = new Actions(driver);
		WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
		ac.moveToElement(this.click_Customers).click().perform();
		mywait.until(ExpectedConditions.elementToBeClickable(this.click_Addicon));
		ac.moveToElement(this.click_Addicon).click().perform();
		mywait.until(ExpectedConditions.visibilityOf(this.capture_customernumber));
		String Exp_Data = this.capture_customernumber.getAttribute("value");
		this.enter_customername.sendKeys(customername);
		this.enter_Address.sendKeys(Address);
		this.enter_City.sendKeys(City);
		this.Enter_Country.sendKeys(Country);
		this.enter_contactperson.sendKeys(contactPerson);
		this.enter_phoneNumber.sendKeys(phonenumber);
		this.enter_Email.sendKeys(Email);
		this.enter_mobileNumber.sendKeys(mobileNumber);
		this.enter_Notes.sendKeys(Notes);
		this.clickAddbtn.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		this.click_confirmOk.click();
		Thread.sleep(2000);
		this.click_AlertOk.click();
		Thread.sleep(2000);
		if(!this.enter_searchTextbox.isDisplayed())
			this.click_searchPanel.click();
		Thread.sleep(1000);
		this.enter_searchTextbox.clear();
		this.enter_searchTextbox.sendKeys(Exp_Data);
		this.click_SearchButton.click();
		Thread.sleep(2000);
		String Act_Data= webtable.getText();
		if(Act_Data.equals(Exp_Data))
		{
			Reporter.log("Add customer is Success  "+Exp_Data+"---------------"+Act_Data,true);
			return true;
		}
		else
		{
			Reporter.log("Add customer is UnSuccess  "+Exp_Data+"---------------"+Act_Data,true);
			return false;
		}
}
}