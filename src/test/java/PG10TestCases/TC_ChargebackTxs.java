package PG10TestCases;

import org.testng.annotations.Test;
import PG10Base.PG10Base;

public class TC_ChargebackTxs  extends PG10Base  {
	
	
	@Test(priority = 2)
	public void chargebackTransaction() throws Exception {

		log.info("==== Starting ChargebackTxs  Transactions Test ====");
		
		ChargebackTxPage.interactWithtransactionchargebackTX();
		

		log.info("==== ChargebackTxs Test Completed ====");

		Thread.sleep(3000);
	}

}
