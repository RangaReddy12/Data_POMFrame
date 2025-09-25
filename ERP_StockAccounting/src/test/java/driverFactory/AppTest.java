package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.pageClasses.AddCustomer;
import com.pageClasses.AddSupplier;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utils.AppUtil;
import utils.ExcelFileUtil;

public class AppTest extends AppUtil {
	String fileInputpath ="./DataTables/ERPData.xlsx";
	String supplierResults ="./DataTables/Supplier.xlsx";
	String customerResults ="./DataTables/customer.xlsx";
	ExtentReports reports;
	ExtentTest logger;
	ExcelFileUtil xl ;
	@Test(priority = 0,enabled = true)
	public void verify_Supplier() throws Throwable
	{
		//generate Extent reports
		reports = new ExtentReports("./target/ExtentReports/supplier.html");
		xl = new ExcelFileUtil(fileInputpath);
		int rc = xl.rowCount("Supplierdata");
		Reporter.log("No of rows in supplier sheet "+rc,true);
		for(int i=1;i<=rc;i++)
		{
			logger = reports.startTest("Verify Supplier");
			logger.assignAuthor("Ranga");
			String sname =xl.getCelldata("Supplierdata", i, 0);
			String address =xl.getCelldata("Supplierdata", i, 1);
			String city =xl.getCelldata("Supplierdata", i, 2);
			String country =xl.getCelldata("Supplierdata", i, 3);
			String cperson =xl.getCelldata("Supplierdata", i, 4);
			String pnumber =xl.getCelldata("Supplierdata", i, 5);
			String email =xl.getCelldata("Supplierdata", i, 6);
			String mnumber =xl.getCelldata("Supplierdata", i, 7);
			String notes =xl.getCelldata("Supplierdata", i, 8);
			AddSupplier sup = PageFactory.initElements(driver, AddSupplier.class);
			boolean res = sup.validate_supplier(sname, address, city, country, cperson, pnumber, email, mnumber, notes);
			logger.log(LogStatus.INFO,sname+"   "+address+"  "+city+"   "+country+"   "+cperson+"   "+pnumber+"   "+email+"  "+mnumber+"   "+notes);
			if(res)
			{
				logger.log(LogStatus.PASS, "Add supplier Success");
				xl.setCelldata("Supplierdata", i, 9, "Pass", supplierResults);
			}
			else
			{
				logger.log(LogStatus.FAIL, "Add Supplier UnSuccess");
				xl.setCelldata("Supplierdata", i, 9, "Fail", supplierResults);
			}
			reports.endTest(logger);
			reports.flush();

		}
	}
	@Test(priority = 1,enabled = true)
	public void verifyCustomer() throws Throwable
	{
		//generate Extent reports
		reports = new ExtentReports("./target/ExtentReports/customer.html");

		xl = new ExcelFileUtil(fileInputpath);
		int rc = xl.rowCount("CustomerData");
		Reporter.log("No of rows in customer sheet  "+rc,true);
		for(int i=1;i<=rc;i++)
		{
			logger= reports.startTest("Verify Customer");
			logger.assignAuthor("Ranga");
			String cname = xl.getCelldata("CustomerData", i, 0);
			String address = xl.getCelldata("CustomerData", i, 1);
			String city = xl.getCelldata("CustomerData", i, 2);
			String country = xl.getCelldata("CustomerData", i, 3);
			String cperson = xl.getCelldata("CustomerData", i, 4);
			String pnumber = xl.getCelldata("CustomerData", i, 5);
			String email = xl.getCelldata("CustomerData", i, 6);
			String mnumber = xl.getCelldata("CustomerData", i, 7);
			String notes = xl.getCelldata("CustomerData", i, 8);
			AddCustomer cus = PageFactory.initElements(driver, AddCustomer.class);
			boolean res = cus.validate_customer(cname, address, city, country, cperson, pnumber, email, mnumber, notes);
			logger.log(LogStatus.INFO,cname+"   "+address+"  "+city+"   "+country+"   "+cperson+"   "+pnumber+"   "+email+"  "+mnumber+"   "+notes);
			if(res)
			{
				logger.log(LogStatus.PASS, "Add Customer success");
				xl.setCelldata("CustomerData", i, 9, "Pass", customerResults);
			}
			else
			{
				logger.log(LogStatus.FAIL, "Add Customer Unsuccess");
				xl.setCelldata("CustomerData", i, 9, "Fail", customerResults);
			}
			reports.endTest(logger);
			reports.flush();
		}
	}
}
















