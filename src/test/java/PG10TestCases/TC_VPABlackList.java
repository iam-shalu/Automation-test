package PG10TestCases;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import PG10Base.PG10Base;
import Listeners.Listeners;

public class TC_VPABlackList extends PG10Base {

	@Test(priority = 12)
	public void VPABlackListCustomer() throws Exception {
		log.info("==== Starting FraudControl VPA Blacklist Test ====");
		Listeners.test.log(Status.INFO, "Navigating to Fraud Control Module");
		Listeners.test.log(Status.INFO, "click On Fraud Control & Go to VPA Blacklist Customer");
		Listeners.test.log(Status.INFO, "click On Choose File & Upload the File");
		Listeners.test.log(Status.INFO, "click On Import File ");
		Listeners.test.log(Status.INFO, "Click On Add VPA BlackList User ");
		Listeners.test.log(Status.INFO, "Add VPA BlackList User - By Validation Type - Full ");
		Listeners.test.log(Status.INFO, "Add VPA BlackList - Enter the VPA");
		Listeners.test.log(Status.INFO, "Click On Submit button ");
		Listeners.test.log(Status.INFO, "Enter the VPA & Click Filter");
		Listeners.test.log(Status.INFO, "Capture the FullPageScreenshot");
		Listeners.test.log(Status.INFO, "Delete the Records");

		vpablackListPage.interactWithfraudControlVPABlackList();

		Listeners.test.log(Status.INFO, "VPA Blacklist Customer Test Completed");
		
		log.info("==== FraudControl VPA  BlackList Test Completed ====");

		Thread.sleep(3000);
	}

}
