package PG10TestCases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Listeners.Listeners;
import PG10Base.PG10Base;

public class TC_ChargebackTxs  extends PG10Base  {
	
	
	@Test(priority = 6)
	public void chargebackTransaction() throws Exception {

		log.info("==== Starting Transaction - ChargebackTxs Transactions Test ====");
		
		Listeners.test.log(Status.INFO, "Navigating to Transaction Module");
		
		log.info("==== Navigating to Transaction Module Test ====");
		Listeners.test.log(Status.INFO, "click On Transactions & Go to City Chargeback Tx Report");
		log.info("==== click On Transactions & Go to City Chargeback Tx Report ====");
		Listeners.test.log(Status.INFO, "Select the date Range");
		Listeners.test.log(Status.INFO, "Select the CB Open Date Range");
		Listeners.test.log(Status.INFO, "Select the CB Close Date Range");
		Listeners.test.log(Status.INFO, "Select the Master Merchant Test-Acs-01-MM");
		Listeners.test.log(Status.INFO, "Select the Merchant Test-Acs-01-MM"); 
		Listeners.test.log(Status.INFO, "Select the Sub Merchant Test-Acs-01-MM"); 
		Listeners.test.log(Status.INFO, "Select Is Closed - No"); 
		Listeners.test.log(Status.INFO, "Click Search");
		Listeners.test.log(Status.INFO, "Capture Full Page Screenshot");
		log.info("==== Capture Full Page Screenshot ====");
		Listeners.test.log(Status.INFO, "Scroll To Top");
		ChargebackTxPage.interactWithtransactionchargebackTX();
		
		log.info("==== Transactions - ChargebackTxs Test Completed ====");
		
		Thread.sleep(3000);
	}

}
