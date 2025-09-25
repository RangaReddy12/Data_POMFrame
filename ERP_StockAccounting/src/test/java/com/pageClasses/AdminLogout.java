package com.pageClasses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLogout {
@FindBy(xpath = "(//*[text()=' Logout'])[2]")
WebElement objlogout;
public void adminLogout()
{
	objlogout.click();
}
}
