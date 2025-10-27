
package PG10TestCases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import PG10Base.PG10Base;
import Listeners.Listeners;

public class TC_Logout extends PG10Base {

	@Test(priority = 19)
	public void logout() throws InterruptedException {
		Listeners.test.log(Status.INFO, "Clicking on Profile");
		logoutPage.profileclick();
		Thread.sleep(500);

		Listeners.test.log(Status.INFO, "Clicking on Logout");
		logoutPage.logoutclick();
		Thread.sleep(1000);

		Listeners.test.log(Status.INFO, "Logout Successful");
	}
}
