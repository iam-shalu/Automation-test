package PG10TestCases;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import Listeners.Listeners;
import PG10Base.PG10Base;

public class TC_WhiteListCustomer extends PG10Base {

	@Test(priority = 8)
	public void whiteListCustomer() throws Exception {
		Listeners.test.log(Status.INFO, "Navigating to Fraud Control Module ");

		log.info("==== Starting FraudControl WhiteList Customer Test ====");

		Listeners.test.log(Status.INFO, "click On Fraud Control & Go to WhiteList Customer");
		Listeners.test.log(Status.INFO, "Select the Master Merchant Dropdown -Test-Acs-01");
		Listeners.test.log(Status.INFO, "Click On Choose File & Upload the File");
		Listeners.test.log(Status.INFO, "Click On Import & Import the Excel File");
		Listeners.test.log(Status.INFO, "click on Add White List Customer -Manual ");
		Listeners.test.log(Status.INFO, "Select Master Merchant -Test-Acs-01");
		Listeners.test.log(Status.INFO, "Enter the First Name");
		Listeners.test.log(Status.INFO, "Enter the Last Name");
		Listeners.test.log(Status.INFO, "Enter the Email Id");
		Listeners.test.log(Status.INFO, "Enter the Phone No");
		Listeners.test.log(Status.INFO, "Enter the IP Address");
		Listeners.test.log(Status.INFO, "check the Active Button checkbox");
		Listeners.test.log(Status.INFO, "Click On Sumbit ");
		Listeners.test.log(Status.INFO, "WhiteList Customer - Select Master Merchant -Test-Acs-01");
		Listeners.test.log(Status.INFO, "Enter the Search by FirstName - Akash");
		Listeners.test.log(Status.INFO, "click on Filter");
		Listeners.test.log(Status.INFO, "click on Export So Export the Excel Sheet");
		Listeners.test.log(Status.INFO, "Capture the Full Page Screenshot ");
		log.info("==== Capture the Full Page Screenshot ====");
		Listeners.test.log(Status.INFO, "Click On Delete Records ");

		whiteListCustomerPage.interactWithfraudControl_whiteListCustomer();

		Listeners.test.log(Status.INFO, "Whitelist Customer Test Completed");

		log.info("==== FraudControl WhiteList Customer Test Completed ====");

		Thread.sleep(3000);

	}
}
