package PG10TestCases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Listeners.Listeners;
import PG10Base.PG10Base;

public class TC_GatewayList extends PG10Base{
	
	@Test(priority = 16)
	public void gatewayList() throws Exception {
		Listeners.test.log(Status.INFO, "Navigating to Fraud Control Module ");

		log.info("==== Starting List Control Gateway List  Test ====");
		Listeners.test.log(Status.INFO, "Navigating to List Control  Module");
		Listeners.test.log(Status.INFO, "Click On List Control & Go to Gateway List");
		Listeners.test.log(Status.INFO, "Click On Descriptor ");
		Listeners.test.log(Status.INFO, "Click On Create Descriptor ");
		Listeners.test.log(Status.INFO, "Terminal ID Enter");
		Listeners.test.log(Status.INFO, "Authentication 1 Enter");
		Listeners.test.log(Status.INFO, "Authentication 2 Enter");
		Listeners.test.log(Status.INFO, "Authentication 3 Enter");
		Listeners.test.log(Status.INFO, "Authentication 4 Enter");
		Listeners.test.log(Status.INFO, "Authentication 5 Enter");
		Listeners.test.log(Status.INFO, "Authentication 6 Enter");
		Listeners.test.log(Status.INFO, "Authentication 7 Enter");
		Listeners.test.log(Status.INFO, "Authentication 8 Enter");
		Listeners.test.log(Status.INFO, "Authentication 9 Enter");
		Listeners.test.log(Status.INFO, "Authentication 10 Enter");
		Listeners.test.log(Status.INFO, "Authentication 11 Enter");
		Listeners.test.log(Status.INFO, "AC Name Enter");
		Listeners.test.log(Status.INFO, " Phone Number Enter");
		Listeners.test.log(Status.INFO, " Select the Currency List");
		Listeners.test.log(Status.INFO, " Select the Country  List");
		Listeners.test.log(Status.INFO, " Select the Payment Type");
		Listeners.test.log(Status.INFO, " Select the Descriptor Type");
		Listeners.test.log(Status.INFO, " Enter the Deposit Cost (%)");
		Listeners.test.log(Status.INFO, " GST on Deposit Cost (%) ");
		Listeners.test.log(Status.INFO, " Select the Mode - Test");
		Listeners.test.log(Status.INFO, " Enter the Notes ");
		Listeners.test.log(Status.INFO, " Click ON Allow Withdrawal ");
		Listeners.test.log(Status.INFO, " Click ON Is Active ? ");
		Listeners.test.log(Status.INFO, " Click ON Assign To Supplier ? ");
		Listeners.test.log(Status.INFO, " Click ON Submit ");
		Listeners.test.log(Status.INFO, " Capture the Full Page Screenshot ");
		Listeners.test.log(Status.INFO, " Click ON Submit ");
		Listeners.test.log(Status.INFO, " Capture the Full Page Screenshot ");
		
		gatewaylistpage.interactWithgatewayList();
		
		log.info("==== List Control Gateway List Completed ====");

		Thread.sleep(3000);
		
	}

}
