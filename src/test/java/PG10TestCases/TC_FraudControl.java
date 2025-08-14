
package PG10TestCases;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import PG10Base.PG10Base;
import Listeners.Listeners;

public class TC_FraudControl extends PG10Base {

	@Test(priority = 2)
	public void testBlackListCustomer() throws Exception {
		log.info("==== Starting FraudControl WhiteList Customer Test ====");
		Listeners.test.log(Status.INFO, "Navigating to Fraud Control Module");
		Listeners.test.log(Status.INFO, "click On Fraud Control & Go to Blacklist Customer");
		Listeners.test.log(Status.INFO, "click On choose file & Upload the Excel File ");
		Listeners.test.log(Status.INFO, "click On Import So that Import the Excel File ");
		Listeners.test.log(Status.INFO, "Search Black List Customer data by Search By Name - Akash");
		Listeners.test.log(Status.INFO, "clear the Black List Customer Data ");
		Listeners.test.log(Status.INFO, " click & Export the Excel File ");
		Listeners.test.log(Status.INFO, "capture the FullPageScreenshot ");
		Listeners.test.log(Status.INFO, "Search Black List Customer data by Search By Email - pankaj@gmail.com");
		Listeners.test.log(Status.INFO, "click On Delete - Delete the Imported Excel File ");
		Listeners.test.log(Status.INFO, "click On Add BlackList Customer to add By Manual way BlackList Customer ");
		Listeners.test.log(Status.INFO, " Enter the Name in BlackList Customer ");
		Listeners.test.log(Status.INFO, " Enter the Email in BlackList Customer ");
		Listeners.test.log(Status.INFO, " Enter the Mobile No. in BlackList Customer ");
		Listeners.test.log(Status.INFO, " Enter the IP address in BlackList Customer");
		Listeners.test.log(Status.INFO, " Click on save )");
		Listeners.test.log(Status.INFO, " search BlackList Customer by Email Id ");

		fraudControlPage.interactWithfraudControlblackList();

		Listeners.test.log(Status.INFO, "Blacklist Customer Test Completed");
		log.info("==== FraudControl WhiteList Customer Test Completed ====");
		Thread.sleep(3000);
	}

	@Test(priority = 3)
	public void testWhiteListCustomer() throws Exception {
		Listeners.test.log(Status.INFO, "Navigating to Fraud Control Module ");

		log.info("==== Starting FraudControl WhiteList Customer Test ====");

		Listeners.test.log(Status.INFO, "click on White List Customer");

		Listeners.test.log(Status.INFO, "click on click here Sample Excel File Download");
		Listeners.test.log(Status.INFO, "click on Add White List Customer");
		Listeners.test.log(Status.INFO, "Select the Master Merchan	t Dropdown -Test-Acs-01");
		Listeners.test.log(Status.INFO, "Enter the First Name");
		Listeners.test.log(Status.INFO, "Enter the Last Name");
		Listeners.test.log(Status.INFO, "Enter the Email Id");
		Listeners.test.log(Status.INFO, "Enter the Phone No");
		Listeners.test.log(Status.INFO, "Enter the IP Address");
		Listeners.test.log(Status.INFO, "Click On Sumbit ");
		Listeners.test.log(Status.INFO, "Capture the Full Page Screenshot ");
		Listeners.test.log(Status.INFO, "Scroll To Top");
		Listeners.test.log(Status.INFO, "Enter the Search by Email Id");
		Listeners.test.log(Status.INFO, "click on Filter");
		Listeners.test.log(Status.INFO, "clear the Search Field");
		Listeners.test.log(Status.INFO, "Enter the Search by Mobile No.");
		Listeners.test.log(Status.INFO, "click on Filter");
		Listeners.test.log(Status.INFO, "Clear the Search Field");
		Listeners.test.log(Status.INFO, "Enter the Search by Last Name");
		Listeners.test.log(Status.INFO, "click on Filter");
		Listeners.test.log(Status.INFO, "Click On White List Customer Id(+) icon");
		Listeners.test.log(Status.INFO, "Click On Delete Record ");

		fraudControlPage.interactWithfraudControl_whiteListCustomer();

		Listeners.test.log(Status.INFO, "Whitelist Customer Test Completed");

		Listeners.test.log(Status.INFO, "Whitelist Merchant Ip Test Started");

		log.info("==== FraudControl WhiteList Customer Test Completed ====");

	}

}
