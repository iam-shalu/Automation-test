package PG10TestCases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Listeners.Listeners;
import PG10Base.PG10Base;

public class TC_SearchTxhist extends PG10Base {

	@Test(priority = 5)
	public void SearchTransactionshist() throws Exception {

		log.info("==== Starting Transactions - Search Tx History Test ====");
		
		Listeners.test.log(Status.INFO, "Navigating to Transactions Module");
		Listeners.test.log(Status.INFO, "click On BNIB & Go to Search Tx History");
		Listeners.test.log(Status.INFO, "Select ChargeBack Tx Id");
		Listeners.test.log(Status.INFO, "Enter Value");
		Listeners.test.log(Status.INFO, "click Filter");
		Listeners.test.log(Status.INFO, "Export the Excel data");
		Listeners.test.log(Status.INFO, "Click On Tx Type - Chargeback ");
		Listeners.test.log(Status.INFO, "Capture Full Page Screenshot ");
		log.info("==== Capture Full Page Screenshot ====");
		
		searchTxhistoryPage.interactWithtransactionsSearchTxHist();

		log.info("==== Transactions - Search Tx History  Test Completed ====");

		Thread.sleep(3000);

	}

}



