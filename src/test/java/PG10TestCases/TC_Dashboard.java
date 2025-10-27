package PG10TestCases;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import Listeners.Listeners;
import PG10Base.PG10Base;

public class TC_Dashboard extends PG10Base {

	@Test(priority = 2)
	public void verifyDashboardButtons() throws Exception {
		log.info("==== Starting Dashboard Test ====");
		Listeners.test.log(Status.INFO, "Navigating to Dashboard");
		Listeners.test.log(Status.INFO, "Close if Limit Bar is Present");
		Listeners.test.log(Status.INFO, "Select the Date Range - Yesterday");
		Listeners.test.log(Status.INFO, "Click On Filter Button");
		Listeners.test.log(Status.INFO, " Select the Payout Tx Details");
		Listeners.test.log(Status.INFO, "Click oN filter");
		Listeners.test.log(Status.INFO, "Click Multiple Merchant Current Volumes Comparison (Line Chart)");
		Listeners.test.log(Status.INFO, "Click Multiple Merchant Current Volumes Comparison (Line Chart)");
		Listeners.test.log(Status.INFO, " Multiple Merchant Current Volumes Comparison (Column Chart)");
		Listeners.test.log(Status.INFO, " Decline Per Merchant (Line Chart)");
		Listeners.test.log(Status.INFO, " Processor Wise Limit Details (Column Chart)");
		Listeners.test.log(Status.INFO, " Single Merchant Status Ratio (Column Chart)");
		Listeners.test.log(Status.INFO, " Scroll tO Top");
		Listeners.test.log(Status.INFO, " Capture the FullPageScreenshot");
		Listeners.test.log(Status.INFO, " Scroll To Top");
		Listeners.test.log(Status.INFO, " Dashboard Test  Completed");

		dashboardPage.interactWithDashboard();

		log.info("Full-page screenshot captured: Dashboard > After_Button_Clicks");
		log.info("==== Dashboard Test Completed ====");

		Thread.sleep(3000);
	}
}

 
