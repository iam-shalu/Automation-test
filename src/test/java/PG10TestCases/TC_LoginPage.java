package PG10TestCases;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import PG10Base.PG10Base;
import Listeners.Listeners;

public class TC_LoginPage extends PG10Base {

	@Test(priority = 1)
	public void login() throws InterruptedException {

		Thread.sleep(3000);

		Listeners.test.log(Status.INFO, "Entering Username");
	//	loginPage.username("Akash_Lade");
		
		loginPage.username("miteshqa");
		log.info("Entered username in username field");

		Listeners.test.log(Status.INFO, "Entering Password");
	//	loginPage.enterpass("KJMYqzp37~Wug&");
	
		loginPage.enterpass("QEJm9~QBnfft7*");
		log.info("Entered password in password field");

		Listeners.test.log(Status.INFO, "Clicking on Login Button");
		loginPage.pclickonlogin();
		log.info("Clicked on the Login Button");

		Thread.sleep(1000);
		Listeners.test.log(Status.INFO, "Login successful");

		Thread.sleep(3000);

	}
}