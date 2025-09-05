package PG10TestCases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Listeners.Listeners;
import PG10Base.PG10Base;

public class TC_MasterMerchant extends PG10Base {

	@Test(priority = 2)
	public void masterMerchantList() throws Exception {
		Listeners.test.log(Status.INFO, "Navigating to Fraud Control Module ");

		log.info("==== Starting List Control Master Merchant Test ====");

		masterMerchantpage.interactWithlistControl_masterMerchant();
		
		log.info("==== List Control Master Merchant Completed ====");

		Thread.sleep(3000);

	}
}
