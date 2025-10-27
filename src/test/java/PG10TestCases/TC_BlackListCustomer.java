
package PG10TestCases;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import PG10Base.PG10Base;
import Listeners.Listeners;

public class TC_BlackListCustomer extends PG10Base {

	@Test(priority = 7)
	public void blackListCustomer() throws Exception {
		log.info("==== Starting FraudControl Blacklist Customer Test ====");
		Listeners.test.log(Status.INFO, "Navigating to Fraud Control Module");
		Listeners.test.log(Status.INFO, "Click On Fraud Control & Go to Blacklist Customer");
		Listeners.test.log(Status.INFO, "Click On choose file & Upload the Excel File");
		Listeners.test.log(Status.INFO, "Click On Import So that Import the Excel File ");
		Listeners.test.log(Status.INFO, "Click On Add BlackList Customer to add By Manual way BlackList Customer ");
		Listeners.test.log(Status.INFO, " Enter the Name in BlackList Customer ");
		Listeners.test.log(Status.INFO, " Enter the Email in BlackList Customer ");
		Listeners.test.log(Status.INFO, " Enter the Mobile No. in BlackList Customer ");
		Listeners.test.log(Status.INFO, " Enter the IP address in BlackList Customer");
		Listeners.test.log(Status.INFO, " Click on save )");
		Listeners.test.log(Status.INFO, "Search Black List Customer data by Search By Name - Akash");
		Listeners.test.log(Status.INFO, "capture the FullPageScreenshot ");
		log.info("==== capture the FullPageScreenshot ====");
		Listeners.test.log(Status.INFO, "Click On Delete the Records");
		Listeners.test.log(Status.INFO, " Click & Export the Excel File ");

		blackListCustomerPage.interactWithfraudControlblackList();

		Listeners.test.log(Status.INFO, "FraudControl Blacklist Customer Test Completed");
		log.info("==== FraudControl BlackList Customer Test Completed ====");
		Thread.sleep(3000);
		
		
	}
}
