package PG10TestCases;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import Listeners.Listeners;
import PG10Base.PG10Base;

public class TC_FTDWhiteListUser extends PG10Base {

	@Test(priority = 11)
	public void whiteListFTDListCustomer() throws Exception {
		Listeners.test.log(Status.INFO, " Started FraudControl FTD White List User Test ");

		log.info("==== Starting FraudControl FTD White List User Test ====");

		Listeners.test.log(Status.INFO, "click On Fraud Control & Go to FTD WhiteList Customer");
		Listeners.test.log(Status.INFO, "Select the Master Merchant Dropdown -Test-Acs-01");
		Listeners.test.log(Status.INFO, "click On choose file & Upload the Excel File");
		Listeners.test.log(Status.INFO, "click On Import So that Import the Excel File ");
		Listeners.test.log(Status.INFO, "click On Add FTD White List User");
		Listeners.test.log(Status.INFO, "Add FTD White List User - Select the Master Merchant Dropdown -Test-Acs-01");
		Listeners.test.log(Status.INFO, "Add FTD White List User - Enter First Name");
		Listeners.test.log(Status.INFO, "Add FTD White List User - Enter Last Name");
		Listeners.test.log(Status.INFO, "Add FTD White List User - Enter the  Email ");
		Listeners.test.log(Status.INFO, "Add FTD White List User - Enter the  Phone No.");
		Listeners.test.log(Status.INFO, "click On Sumbit Button");
		Listeners.test.log(Status.INFO, "Select the Master Merchant Dropdown -Test-Acs-01");
		Listeners.test.log(Status.INFO, "Enter the Phone No ");
		Listeners.test.log(Status.INFO, "click On Filter ");
		Listeners.test.log(Status.INFO, "capture the FullPageScreenshot ");
		Listeners.test.log(Status.INFO, "delete the Records ");

		ftdwhiteListPage.interactWithfraudControl_FTDwhiteListUser();

		Listeners.test.log(Status.INFO, "FTD White List User Test Completed");
		
		Listeners.test.log(Status.INFO, "FraudControl FTD White List user Test Completed");

		log.info("==== FraudControl FTD White List Customer Test Completed ====");
		Thread.sleep(3000);
		
		
	}
}
