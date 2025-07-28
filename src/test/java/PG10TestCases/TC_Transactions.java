package PG10TestCases;

import org.testng.annotations.Test;
import PG10Base.PG10Base;

public class TC_Transactions extends PG10Base {
	
	  @Test(priority = 2)
	    public void depositTransactions() throws Exception {
	        log.info("==== Starting Deposit Transactions Test ====");
	        transactionPage.interactWithtransactionsDepositTxs();
	        log.info("==== Deposit Transactions Test Completed ====");
	    }
	  

	    @Test(priority = 3)
	    public void payoutTransactions() throws Exception {
	        log.info("==== Starting Payout Transactions Test ====");
	        transactionPage.interactWithtransactionPayoutTxs();
	        log.info("==== Payout Transactions Test Completed ====");
	        
	    }
	
	
}
	


//This is the Screenshot Folder Save the Files 
//C:\Users\india\eclipse-workspace\PG10_Selenium_Test\screenshots\2025-07-01\Transactions


//C:\Users\india\eclipse-workspace\PG10_Selenium_Test\downloads