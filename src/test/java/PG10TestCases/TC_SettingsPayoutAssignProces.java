package PG10TestCases;
import org.testng.annotations.Test;
import PG10Base.PG10Base;

public class TC_SettingsPayoutAssignProces  extends PG10Base  {
	
	
	@Test(priority = 18)
	public void SettingsPayoutAssignProces() throws Exception {

		log.info("==== Starting Payout Processor Assign Transactions Test ====");
		
		settingAssignPayoutPage.interactWithsettingsPayoutProcessorAss();
		
		log.info("====  Payout Processor Assign Test Completed ====");

		Thread.sleep(3000);
	}

}
