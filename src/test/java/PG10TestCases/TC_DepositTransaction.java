
package PG10TestCases;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import Listeners.Listeners;
import PG10Base.PG10Base;

public class TC_DepositTransaction extends PG10Base {

	@Test(priority = 3)
	public void depositTransactions() throws Exception {

		log.info("==== Starting Deposit Transactions Test ====");

		Listeners.test.log(Status.INFO, "Navigating to Transaction Module");
		Listeners.test.log(Status.INFO, "click On Transaction & BNIB -----> Deposit Transaction");
		Listeners.test.log(Status.INFO, "Select the Date Range - Yesterday");
		Listeners.test.log(Status.INFO, "Click On Filter");
		Listeners.test.log(Status.INFO, "Click On Transaction Id");
		Listeners.test.log(Status.INFO, "Capture the FullPageScreenshot");
		log.info("==== Capture Full Page Screenshot ====");
		Listeners.test.log(Status.INFO, "Scroll To Top ");
		Listeners.test.log(Status.INFO, "Scroll To Top ");
		Listeners.test.log(Status.INFO, "Click On Action Button");
		Listeners.test.log(Status.INFO, "Go to View Tx Details ");
		Listeners.test.log(Status.INFO, "Capture the FullPageScreenshot");
		log.info("==== Capture Full Page Screenshot ====");
		Listeners.test.log(Status.INFO, "Control Back To Original Page");
		Listeners.test.log(Status.INFO, "Search Field - IPayinfo");
		Listeners.test.log(Status.INFO, "Filter Type - Equals");
		Listeners.test.log(Status.INFO, "Enter the Value - gomzi001@axl");
		Listeners.test.log(Status.INFO, "Click On Filter");

		depositTransactionPage.interactWithDepositTransactions();

		log.info("==== Deposit Transactions Test Completed ====");

		Thread.sleep(3000);
		
		
	}
	

}
