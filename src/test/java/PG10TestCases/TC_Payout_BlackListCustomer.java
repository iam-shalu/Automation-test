
	package PG10TestCases;

	import org.testng.annotations.Test;


	import com.aventstack.extentreports.Status;

	import PG10Base.PG10Base;
	import Listeners.Listeners;

	public class TC_Payout_BlackListCustomer  extends PG10Base {

	    @Test(priority = 2)
	    public void testPayoutBlackListCustomer() throws Exception {
	    	log.info("==== Starting FraudControl Payout Blacklist Customer Test ====");
	        Listeners.test.log(Status.INFO, "Navigating to Fraud Control Module");
	        Listeners.test.log(Status.INFO, "click On Fraud Control & Go to Payout Blacklist Customer");
	     
	        
	        payoutBlackListCustomerPage.interactWithfraudControlPayoutblackListCust();

	        Listeners.test.log(Status.INFO, "Blacklist Customer Test Completed");
	        log.info("==== FraudControl Payout BlackList Customer Test Completed ====");
	        Thread.sleep(3000);
	    }
	

}



