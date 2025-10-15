package PG10TestCases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Listeners.Listeners;
import PG10Base.PG10Base;

public class TC_PayoutTransaction extends PG10Base {

	@Test(priority = 4)
	public void payoutTransactions() throws Exception {

		log.info("==== Starting Payout Transactions Test ====");
		Listeners.test.log(Status.INFO, "Navigating to Transaction Module");
		Listeners.test.log(Status.INFO, "Click On Transaction & BNIB -----> Payout Transaction");
		Listeners.test.log(Status.INFO, "Select the Date Range - Last 7 days");
		Listeners.test.log(Status.INFO, "Click On Filter");
		Listeners.test.log(Status.INFO, "Click On Transaction Id");
		Listeners.test.log(Status.INFO, "Capture the FullPageScreenshot");
		
		
		
		Listeners.test.log(Status.INFO, "Scroll To Top ");
		Listeners.test.log(Status.INFO, "Scroll To Top ");
		Listeners.test.log(Status.INFO, "Click On Action Button");
		Listeners.test.log(Status.INFO, "Go to View Tx Details ");
		Listeners.test.log(Status.INFO, "Capture the FullPageScreenshot");
		
		
		Listeners.test.log(Status.INFO, "Control Back To Original Page");
		Listeners.test.log(Status.INFO, "Search Field - IPayinfo");
		Listeners.test.log(Status.INFO, "Filter Type - Equals");
		Listeners.test.log(Status.INFO, "Enter the Value - gomzi001@axl");
		Listeners.test.log(Status.INFO, "Click On Filter");

	//	payoutTransactionPage.interactWithtransactionPayoutTxs();
		
		payoutTransactionPage.interactWithtransactionPayoutTxs();
		
		
		log.info("==== Capture Full Page Screenshot ====");

		log.info("==== Payout Transactions Test Completed ====");

		Thread.sleep(3000);

	}
}
