

	package PG10TestCases;

	import org.testng.annotations.Test;


	import com.aventstack.extentreports.Status;

	import PG10Base.PG10Base;
	import Listeners.Listeners;

	public class TC_ChargebackTxs  extends PG10Base {
		

	    @Test(priority = 15)
	    public void ChargebackTxs() throws Exception {
	    	log.info("==== Starting FraudControl Blacklist Customer Test ====");
	        Listeners.test.log(Status.INFO, "Navigating to Transaction  Module");
	        Listeners.test.log(Status.INFO, "click On Transaction & Go to Chargeback Tx");
	       
	       
	        chargebackReportPage.interactWithtransactionchargebackTX();

	        Listeners.test.log(Status.INFO, "Transaction Test Completed");
	        log.info("==== Transaction Test Completed ====");
	        Thread.sleep(3000);
	        
	        
	        
	        
	        
	    }
	

}



