package PG10TestCases;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Listeners.Listeners;
import PG10Base.PG10Base;

public class TC_SettingsPayoutAssignProces  extends PG10Base  {
	
	
	@Test(priority = 18)
	public void SettingsPayoutAssignProces() throws Exception {

		log.info("==== Starting Setting - Payout Processor Assign  Test ====");
		
		Listeners.test.log(Status.INFO, "Nevigating to Setting Module");
		Listeners.test.log(Status.INFO, "click On deposit Assign Processor");
		Listeners.test.log(Status.INFO, "Select Master Merchant -Test-acs-01-007");
		Listeners.test.log(Status.INFO, "Click On Get Processor");
		Listeners.test.log(Status.INFO, "Select Single Tx Max Amount");
		Listeners.test.log(Status.INFO, "Select Single Tx  Min Amount");
		Listeners.test.log(Status.INFO, "Select the gateway");
		Listeners.test.log(Status.INFO, "Select the Processor");
		Listeners.test.log(Status.INFO, "Select the Checkbox Processor IsActive");
		Listeners.test.log(Status.INFO, "Enter Limit");
		Listeners.test.log(Status.INFO, "Enter Order");
		Listeners.test.log(Status.INFO, "Click On Update Limit Amount");
		
		settingsPayoutAssignPage.interactWithsettingsPayoutProcessorAss();
		
		log.info("====  Settings - Payout Processor Assign Test Completed ====");

		Thread.sleep(3000);
	}

}