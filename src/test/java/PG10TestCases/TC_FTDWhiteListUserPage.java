package PG10TestCases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Listeners.Listeners;
import PG10Base.PG10Base;

public class TC_FTDWhiteListUserPage extends PG10Base{
	
	   @Test(priority = 2)
	    public void testWhiteFTDListCustomer() throws Exception {
	        Listeners.test.log(Status.INFO, "Navigating to Fraud Control Module ");
	        
	        log.info("==== Starting FraudControl WhiteList Customer Test ====");

	        Listeners.test.log(Status.INFO, "click On Fraud Control & Go to FTD WhiteList Customer");
	        Listeners.test.log(Status.INFO, "Select the Master Merchant Dropdown -Test-Acs-01");
	       
	               
	        ftdwhiteListPage.interactWithfraudControl_FTDwhiteListUser();
	        
	        
	        Listeners.test.log(Status.INFO, "Whitelist Customer Test Completed");
	        
	        Listeners.test.log(Status.INFO, "Whitelist Merchant Ip Test Started");
	        
	        log.info("==== FraudControl WhiteList Customer Test Completed ====");

	    }
	    

}
