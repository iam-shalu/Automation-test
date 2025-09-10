package PG10TestCases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Listeners.Listeners;
import PG10Base.PG10Base;

public class TC_SearchTxhist extends PG10Base {

	@Test(priority = 2)
	public void depositTransactions() throws Exception {

		log.info("==== Starting Search Tx History Transactions Test ====");

		searchTxhistoryPage.interactWithtransactionsSearchTxHist();

		log.info("==== Deposit Search Tx History  Test Completed ====");

		Thread.sleep(3000);

	}

}



