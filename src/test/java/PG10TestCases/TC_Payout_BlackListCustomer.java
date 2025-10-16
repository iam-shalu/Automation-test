
package PG10TestCases;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import PG10Base.PG10Base;
import PG10PageObject.Payout_BlackListCustomer;
import Listeners.Listeners;

public class TC_Payout_BlackListCustomer extends PG10Base {

	@Test(priority = 10)
	public void payoutBlackListCustomer() {
		try {
			Payout_BlackListCustomer payoutBlackListCustomerPage = new Payout_BlackListCustomer(driver);
			
			log.info("==== Starting FraudControl  Blacklist Payout Customer Test ====");
			Listeners.test.log(Status.INFO, "Navigating to Fraud Control Module");
			Listeners.test.log(Status.INFO, "Click on 'Fraud Control' menu");
			Listeners.test.log(Status.INFO, "Select 'Payout BlackList Customer' option");
			Listeners.test.log(Status.INFO, "Select Master Merchant - Test-acs-01");
			Listeners.test.log(Status.INFO, "Upload Blacklist Excel File");
			Listeners.test.log(Status.INFO, "Click on Import Button");
			Listeners.test.log(Status.INFO, "Click on Add Payout BlackList (Manual)");
			Listeners.test.log(Status.INFO, "Fill Master Merchant, Account Number & IFSC");
			Listeners.test.log(Status.INFO, "Save Manual Entry");
			Listeners.test.log(Status.INFO, "Search Records By Account No");
			Listeners.test.log(Status.INFO, "Click on Filter");
			Listeners.test.log(Status.INFO, "Export Record to Excel");
			Listeners.test.log(Status.INFO, "Capture Full Page Screenshot");
			Listeners.test.log(Status.INFO, "Delete Records #1 by Account No");

			payoutBlackListCustomerPage.interactWithfraudControlPayoutblackListCust();

			Thread.sleep(3000);

			Listeners.test.log(Status.PASS, "Payout BlackList Customer Test Completed Successfully");
			
			log.info("==== FraudControl  BlackList Payout Customer Test Completed ====");
		} catch (Exception e) {
			Listeners.test.log(Status.FAIL, "Test Failed due to: " + e.getMessage());
			log.error("Error occurred during Payout BlackList Customer test", e);
		}
	}
}



