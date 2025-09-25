package com.pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class AdminLogin {
	WebDriver driver;
public AdminLogin(WebDriver driver)
{
	this.driver=driver;
}
//define OR
	@FindBy(name ="username")
	WebElement objuser;
	@FindBy(xpath = "//input[@id='password']")
	WebElement objpass;
	@FindBy(id="btnsubmit")
	WebElement objloginbtn;
	public void adminLogin(String user,String pass) throws Throwable
	{
		Thread.sleep(1000);
		objuser.clear();
		objuser.sendKeys(user);
		Thread.sleep(1000);
		objpass.clear();
		objpass.sendKeys(pass);
		objloginbtn.click();
		String Expected ="dashboard";
		String Actual = driver.getCurrentUrl();
		if(Actual.contains(Expected))
		{
			Reporter.log("Login Success",true);
		}
		else
		{
			Reporter.log("Login UnSuccess",true);
		}
	}
}
