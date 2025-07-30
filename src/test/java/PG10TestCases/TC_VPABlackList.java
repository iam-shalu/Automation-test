
	package PG10TestCases;

	import org.testng.annotations.Test;


	import com.aventstack.extentreports.Status;

	import PG10Base.PG10Base;
	import Listeners.Listeners;

	public class TC_VPABlackList  extends PG10Base {

	    @Test(priority = 2)
	    public void testVPABlackListCustomer() throws Exception {
	    	log.info("==== Starting FraudControl Blacklist Customer Test ====");
	        Listeners.test.log(Status.INFO, "Navigating to Fraud Control Module");
	        Listeners.test.log(Status.INFO, "click On Fraud Control & Go to VPA Blacklist Customer");
	    
	       
	        blackListCustomerPage.interactWithfraudControlblackList();

	        Listeners.test.log(Status.INFO, "Blacklist Customer Test Completed");
	        log.info("==== FraudControl BlackList Customer Test Completed ====");
	        Thread.sleep(3000);
	    }
	

}



