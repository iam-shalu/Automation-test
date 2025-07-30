
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
	        Listeners.test.log(Status.INFO, "Select the Master Merchant Dropdown - Test-acs-01");
	        Listeners.test.log(Status.INFO, "click On choose file & Upload the Excel File");
	        Listeners.test.log(Status.INFO, "click On Import So that Import the Excel File ");
	        Listeners.test.log(Status.INFO, "click On Add BlackList Customer to add By Manual way BlackList Customer ");
	        Listeners.test.log(Status.INFO, " Select the Master Merchant Dropdown -Test-acs-01 ");
	        Listeners.test.log(Status.INFO, " Enter the Account No ");
	        Listeners.test.log(Status.INFO, " Enter the IFSC Code  ");
	        Listeners.test.log(Status.INFO, " Click on save )");
	        Listeners.test.log(Status.INFO, " Select the Master Merchant By Name  -Test-acs-01");
	        Listeners.test.log(Status.INFO, " Click on Filter )");
	        Listeners.test.log(Status.INFO, " Export the Excel Data)");
	        Listeners.test.log(Status.INFO, "capture the FullPageScreenshot ");
	        Listeners.test.log(Status.INFO, "click On Delete - Delete the Record Excel File ");
	     
	        payoutBlackListCustomerPage.interactWithfraudControlPayoutblackListCust();

	        Listeners.test.log(Status.INFO, "Blacklist Customer Test Completed");
	        log.info("==== FraudControl Payout BlackList Customer Test Completed ====");
	        Thread.sleep(3000);
	    }
	

}



