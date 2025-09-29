package PG10TestCases;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Listeners.Listeners;
import PG10Base.PG10Base;

public class TC_SettingsDepositAssignProces  extends PG10Base  {
	
	
	@Test(priority = 17)
	public void SettingsDepositAssignProces() throws Exception {

		log.info("==== Starting Settings - Deposit Processor Assign Test ====");
		
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
		
		settingsDepositAssignPage.interactWithsettingsDepositProcessor();
		
		log.info("====  Settings - Deposit Processor Assign Test Completed ====");

		Thread.sleep(3000);
		
		
		
	}

}


