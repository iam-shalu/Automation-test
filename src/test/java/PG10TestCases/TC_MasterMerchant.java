package PG10TestCases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Listeners.Listeners;
import PG10Base.PG10Base;

public class TC_MasterMerchant extends PG10Base {

	@Test(priority = 2)
	public void masterMerchantList() throws Exception {
		
		log.info("==== Starting List Control Master Merchant Test ====");
		Listeners.test.log(Status.INFO, "Navigating to List Control Module");
		Listeners.test.log(Status.INFO, "Click On List Control  & Go to Merchant List");
		Listeners.test.log(Status.INFO, "Click On Master Merchant List");
		Listeners.test.log(Status.INFO, "Click On Create Master Merchant List");
		Listeners.test.log(Status.INFO, "Enter the Name");
		Listeners.test.log(Status.INFO, "Enter the Title");
		Listeners.test.log(Status.INFO, "Select the Currency");
		Listeners.test.log(Status.INFO, "Enter the HubSpot Id");
		Listeners.test.log(Status.INFO, "Enter the Notes");
		Listeners.test.log(Status.INFO, "click Select the  Active/Inactive"); 
	    Listeners.test.log(Status.INFO, "click Select the Is Production Live");
	    Listeners.test.log(Status.INFO, "click Select the Check Real Name on Random Enable/Disable");
	    Listeners.test.log(Status.INFO, "click Select the Balance Check When Payout Enable/Disable");
	    Listeners.test.log(Status.INFO, "click Select the Settlement Report Generate Enable/Disable");
	    Listeners.test.log(Status.INFO, "click Select the BNIB Payout Generate Enable/Disable");
	    Listeners.test.log(Status.INFO, "click Select the BNIB Generate Random Details Enable/Disable");
	    Listeners.test.log(Status.INFO, "click Select the Is Allow Chargeback Original Tx ");
	    Listeners.test.log(Status.INFO, "click Select the Is Allow Refund Original Tx");
	    Listeners.test.log(Status.INFO, "click On Submit button");
	    Listeners.test.log(Status.INFO, "click On Create Merchant");
	    Listeners.test.log(Status.INFO, "Enter Merchant Name");
	    Listeners.test.log(Status.INFO, "Enter Alias Name");
	    Listeners.test.log(Status.INFO, "Enter Margin Percentage (as %)");
	    Listeners.test.log(Status.INFO, "Enter Email");
	    Listeners.test.log(Status.INFO, "Enter HubSpot Id");
	    Listeners.test.log(Status.INFO, "Enter Notes");
	    Listeners.test.log(Status.INFO, "click On  Active/Inactive ?");
	    Listeners.test.log(Status.INFO, "click On  Enable Running Balance Report ?");
	    Listeners.test.log(Status.INFO, "click On  Enable Batch Payout Report ?");
	    Listeners.test.log(Status.INFO, "click On  Enable Instant Payout Report ?");
	    Listeners.test.log(Status.INFO, "click On  Enable KYC verification check Report ?");
	    Listeners.test.log(Status.INFO, "click On  Enable KYC verification Ratio Report ?");
	    Listeners.test.log(Status.INFO, "click On  Submit");
	    
		masterMerchantpage.interactWithlistControl_masterMerchant();
		
		log.info("==== List Control Sub Merchant Test Completed ====");

		Thread.sleep(3000);

	}
}
