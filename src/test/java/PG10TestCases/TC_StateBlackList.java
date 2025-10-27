
package PG10TestCases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import PG10Base.PG10Base;
import Listeners.Listeners;

public class TC_StateBlackList extends PG10Base {

	@Test(priority = 14)
	public void stateBlackListCustomer() throws Exception {
		log.info("==== Starting FraudControl State Blacklist Customer Test ====");
		Listeners.test.log(Status.INFO, "Navigating to City Fraud Control Module");
		Listeners.test.log(Status.INFO, "click On Fraud Control & Go to State Blacklist Customer");
		Listeners.test.log(Status.INFO, "select the submerchant - Test-acs-01");
		Listeners.test.log(Status.INFO, "choose & Upload the File");
		Listeners.test.log(Status.INFO, "Import the File");
		Listeners.test.log(Status.INFO, "Add State BlackList by Manual Way -select Submerchant -Test-acs-01");
		Listeners.test.log(Status.INFO, "Add State BlackList by Manual Way -Enter the State - Chattisgarh");
		Listeners.test.log(Status.INFO, "Select the Submerchant - Test-acs-01");
		Listeners.test.log(Status.INFO, "Click On Filter");
		Listeners.test.log(Status.INFO, "Capture the FullPageScreenshot");
		Listeners.test.log(Status.INFO, "Search by the State");
		Listeners.test.log(Status.INFO, "Click On the  Filter");
		Listeners.test.log(Status.INFO, "Delete the Records");

		stateblackListPage.interactWithfraudControlStateblackList();

		Listeners.test.log(Status.INFO, "State Blacklist Customer Test Completed");
		log.info("==== FraudControl State BlackList Customer Test Completed ====");
		Thread.sleep(3000);

	}

}
