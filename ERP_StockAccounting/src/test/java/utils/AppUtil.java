package utils;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.pageClasses.AdminLogin;
import com.pageClasses.AdminLogout;

public class AppUtil {
public static WebDriver driver;
public static Properties conpro;
@BeforeMethod
public static void setUp()throws Throwable
{
	conpro= new Properties();
	//load property file
	conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
	if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(conpro.getProperty("Url"));
		AdminLogin login = PageFactory.initElements(driver, AdminLogin.class);
		login.adminLogin("admin", "master");
	}
	else if(conpro.getProperty("Browser").equalsIgnoreCase("firefox"))
	{
		driver = new FirefoxDriver();
		driver.get(conpro.getProperty("Url"));
		AdminLogin login = PageFactory.initElements(driver, AdminLogin.class);
		login.adminLogin("admin", "master");
	}
	else 
	{
		try {
			throw new IllegalArgumentException("Browser Value is Not matching");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
@AfterMethod
public static void tearDown()
{
	AdminLogout logout = PageFactory.initElements(driver, AdminLogout.class);
	logout.adminLogout();
	driver.quit();
}
}
