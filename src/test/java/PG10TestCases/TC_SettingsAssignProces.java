package PG10TestCases;
import org.testng.annotations.Test;
import PG10Base.PG10Base;

public class TC_SettingsAssignProces  extends PG10Base  {
	
	
	@Test(priority = 2)
	public void SettingsAssignProces() throws Exception {

		log.info("==== Starting Payout Processor Assign Transactions Test ====");
		
		settingAssignPayoutPage.interactWithsettingsPayoutProcessorAss();
		
		log.info("====  Payout Processor Assign Test Completed ====");

		Thread.sleep(3000);
	}

}
