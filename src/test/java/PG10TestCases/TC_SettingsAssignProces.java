package PG10TestCases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Listeners.Listeners;
import PG10Base.PG10Base;

public class TC_SettingsAssignProces extends PG10Base {
	
	@Test(priority = 2)
	public void settingsAssignProces() throws Exception {

		log.info("==== Starting  Settings Module Assign Processor Test ====");

		Listeners.test.log(Status.INFO, "Navigating to Settings deposit Assign Processor Module");
		
		settingsDepositAssignPage.interactWithsettingsDepositProcessor();

		log.info("==== Settings Module Deposit Assign Processor Completed ====");

		Thread.sleep(3000);
	}


}
