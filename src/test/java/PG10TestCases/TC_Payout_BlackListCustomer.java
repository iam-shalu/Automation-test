//
//	package PG10TestCases;
//
//	import org.testng.annotations.Test;
//
//
//	import com.aventstack.extentreports.Status;
//
//	import PG10Base.PG10Base;
//	import Listeners.Listeners;
//
//	public class TC_Payout_BlackListCustomer  extends PG10Base {
//
//	    @Test(priority = 2)
//	    public void PayoutBlackListCustomer() throws Exception {
//	    	log.info("==== Starting FraudControl Payout Blacklist Customer Test ====");
//	        Listeners.test.log(Status.INFO, "Navigating to Fraud Control Module");
//	        Listeners.test.log(Status.INFO, "click On Fraud Control & Go to Payout Blacklist Customer");	  
//	        Listeners.test.log(Status.INFO, "Select the Master Merchant Dropdown - Test-acs-01");
//	        Listeners.test.log(Status.INFO, "click On choose file & Upload the Excel File");
//	        Listeners.test.log(Status.INFO, "click On Import So that Import the Excel File ");
//	        Listeners.test.log(Status.INFO, "click On Add BlackList Payout Customer to add By Manual way BlackList Payout Customer ");
//	        Listeners.test.log(Status.INFO, " Select the Master Merchant Dropdown -Test-acs-01 ");
//	        Listeners.test.log(Status.INFO, " Enter the Account No ");
//	        Listeners.test.log(Status.INFO, " Enter the IFSC Code ");
//	        Listeners.test.log(Status.INFO, " Click on save )");
//	        Listeners.test.log(Status.INFO, " Select the Master Merchant Dropdown -Test-acs-01");
//	        Listeners.test.log(Status.INFO, " Search By Account No.");
//	        Listeners.test.log(Status.INFO, " Click on Filter )");
//	        Listeners.test.log(Status.INFO, " Export the Data)");
//	        Listeners.test.log(Status.INFO, "capture the FullPageScreenshot ");
//	        Listeners.test.log(Status.INFO, "click On Delete - Delete the Record ");
//	     
//	        payoutBlackListCustomerPage.interactWithfraudControlPayoutblackListCust();
//	        
//	
//
//	        Listeners.test.log(Status.INFO, "Blacklist Customer Test Completed");
//	        log.info("==== FraudControl Payout BlackList Customer Test Completed ====");
//	        Thread.sleep(3000);
//	    }
//	
//
//}
//
//
//


package PG10TestCases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import PG10Base.PG10Base;
import PG10PageObject.Payout_BlackListCustomer;
import Listeners.Listeners;

public class TC_Payout_BlackListCustomer extends PG10Base {

    @Test(priority = 6)
    public void PayoutBlackListCustomer() {
        try {
            // ✅ Initialize the Page Object
            Payout_BlackListCustomer payoutBlackListCustomerPage = new Payout_BlackListCustomer(driver);

            log.info("==== Starting FraudControl Payout Blacklist Customer Test ====");
            Listeners.test.log(Status.INFO, "Navigating to Fraud Control Module");

            Listeners.test.log(Status.INFO, "Step 1: Click on 'Fraud Control' menu");
            Listeners.test.log(Status.INFO, "Step 2: Select 'Payout BlackList Customer' option");

            Listeners.test.log(Status.INFO, "Step 3: Select Master Merchant - Test-acs-01");
            Listeners.test.log(Status.INFO, "Step 4: Upload Blacklist Excel File");

            Listeners.test.log(Status.INFO, "Step 5: Click on Import Button");
            Listeners.test.log(Status.INFO, "Step 6: Click on Add Payout BlackList (Manual)");

            Listeners.test.log(Status.INFO, "Step 7: Fill Master Merchant, Account Number & IFSC");
            Listeners.test.log(Status.INFO, "Step 8: Save Manual Entry");

            Listeners.test.log(Status.INFO, "Step 9: Search Records By Account No");
            Listeners.test.log(Status.INFO, "Step 10: Click on Filter");

            Listeners.test.log(Status.INFO, "Step 11: Export Record to Excel");
            Listeners.test.log(Status.INFO, "Step 12: Capture Full Page Screenshot");

            Listeners.test.log(Status.INFO, "Step 13: Delete Record #1 by Account No");
            Listeners.test.log(Status.INFO, "Step 14: Delete Record #2 by Account No");

            payoutBlackListCustomerPage.interactWithfraudControlPayoutblackListCust();
            
            Thread.sleep(3000);

            Listeners.test.log(Status.PASS, "✅ Payout BlackList Customer Test Completed Successfully");
            log.info("==== FraudControl Payout BlackList Customer Test Completed ====");
        } catch (Exception e) {
            Listeners.test.log(Status.FAIL, "❌ Test Failed due to: " + e.getMessage());
            log.error("Error occurred during Payout BlackList Customer test", e);
        }
    }
}
