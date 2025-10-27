
package PG10TestCases;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import PG10Base.PG10Base;
import Listeners.Listeners;

public class TC_WhiteListMechantIP extends PG10Base {

	@Test(priority = 9)
	public void WhiteListMerchantIP() throws Exception {
		log.info("==== Starting WhiteList  Merchant IP Test ====");
		Listeners.test.log(Status.INFO, "Navigating to Fraud Control Module");
		Listeners.test.log(Status.INFO, "click On Fraud Control & Go to WhiteList Merchant IP ");
		Listeners.test.log(Status.INFO, "Select the Master Merchant Dropdown -Test-Acs-01 ");
		Listeners.test.log(Status.INFO, "Click On Choose File & Upload the File");
		Listeners.test.log(Status.INFO, "Click On Import & Import the Excel File");
		Listeners.test.log(Status.INFO, "Click On Add Merchant Ip - Manual Way");
		Listeners.test.log(Status.INFO, "Select the Master Merchant - Test-Acs-01");
		Listeners.test.log(Status.INFO, "Enter the IP");
		Listeners.test.log(Status.INFO, "check the Checkbox Active");
		Listeners.test.log(Status.INFO, "Click On Save");
		Listeners.test.log(Status.INFO, "Select the Master Merchant Dropdown -Test-Acs-01 ");
		Listeners.test.log(Status.INFO, "Click on Filter ");
		Listeners.test.log(Status.INFO, "capture the captureFullPageScreenshot  ");
		Listeners.test.log(Status.INFO, "Export the Excel File  ");
		Listeners.test.log(Status.INFO, "Search by IP - 1.5.7.8 ");
		Listeners.test.log(Status.INFO, "Click On Filter Button");
		Listeners.test.log(Status.INFO, "Delete the Record by IP - 1.5.7.8");
		Listeners.test.log(Status.INFO, "Clear the Data");
		Listeners.test.log(Status.INFO, "Search by IP - 8.7.6.5 ");
		Listeners.test.log(Status.INFO, "Delete the Record by IP - 8.7.6.5");

		whiteListMerchantIPPage.interactWithfraudControlwhiteListMerchIP();

		Listeners.test.log(Status.INFO, "WhiteList  Merchant IP Test Completed");
		log.info("==== FraudControl WhiteList  Merchant IP Completed ====");
		Thread.sleep(3000);
	}
}
