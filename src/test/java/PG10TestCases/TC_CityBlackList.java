package PG10TestCases;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import PG10Base.PG10Base;
import Listeners.Listeners;

public class TC_CityBlackList extends PG10Base {

	@Test(priority = 13)
	public void cityBlackListCustomer() throws Exception {
		
		log.info("==== Starting FraudControl City  Blacklist Customer Test ====");
		
		Listeners.test.log(Status.INFO, "Navigating to City Fraud Control Module");
		Listeners.test.log(Status.INFO, "click On Fraud Control & Go to City Blacklist Customer");
		Listeners.test.log(Status.INFO, "Select Sub Merchant List - Test-acs-01");
		Listeners.test.log(Status.INFO, "Upload the Excel File Data");
		Listeners.test.log(Status.INFO, "Click On Import the excel Data ");
		Listeners.test.log(Status.INFO, "Click On Add the City BlackList");
		Listeners.test.log(Status.INFO, "select the Submerchant- Test-acs-01");
		Listeners.test.log(Status.INFO, "Enter the City");
		Listeners.test.log(Status.INFO, "click on Submit Button");
		Listeners.test.log(Status.INFO, "Select the Submerchant List -Test-acs-01");
		Listeners.test.log(Status.INFO, "click on Filter");
		Listeners.test.log(Status.INFO, "Export the Excel");
		Listeners.test.log(Status.INFO, "capture the Screenshot");
		Listeners.test.log(Status.INFO, "Select the Submerchant List -Test-acs-01");
		Listeners.test.log(Status.INFO, "Search by the City");
		Listeners.test.log(Status.INFO, "click on Filter");
		Listeners.test.log(Status.INFO, "Delete the Records");

		cityblackListPage.interactWithFraudControlBlackList();

		Listeners.test.log(Status.INFO, " City Blacklist Customer Test Completed");

		log.info("==== FraudControl City BlackList Customer Test Completed ====");

		Thread.sleep(3000);

	}

}
