package PG10Base;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import PG10PageObject.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PG10Base {
	public static Logger log = Logger.getLogger(PG10Base.class.getName());
	public static WebDriver driver;
	public static Login loginPage;
	public static Transactions transactionPage;
	public static BlackListCustomer blackListCustomerPage;
	public static Logout logoutPage;
	public static WhiteListCustomer whiteListCustomerPage;
	public static WhiteList_MechantIP whiteListMerchantIPPage;
	public static Payout_BlackListCustomer payoutBlackListCustomerPage;
	public static FTDWhiteListUser ftdwhiteListPage;
	public static VPABlackList vpablackListPage;
	public static StateBlackList stateblackListPage;
	public static CityBlackList cityblackListPage;
	public static FraudControl fraudControlPage;
	public static DepositTransaction depositTransactionPage;
	public static PayoutTransaction payoutTransactionPage;
	public static Dashboard dashboardPage;
	public static ChargebackTxReport ChargebackTxPage;
	public static MasterMerchant masterMerchantpage;
	public static GatewayList gatewaylistpage;
	public static Settings_DepositAssignProcessor settingsDepositAssignPage;
	public static SearchTxHistory searchTxhistoryPage;
	
	@BeforeSuite
	public void setUpSuite() {
		try {
			DOMConfigurator.configure("log4j.xml");
			WebDriverManager.chromedriver().setup();
			String downloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";
			ChromeOptions options = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("download.default_directory", downloadDir);
			prefs.put("download.prompt_for_download", false);
			prefs.put("safebrowsing.enabled", true);
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			log.info("Browser launched and maximized");
			driver.get("https://test.paygate10.com/Login");
			log.info("Navigated to PG10 login page");
			loginPage = new Login(driver);
			transactionPage = new Transactions(driver);
			blackListCustomerPage = new BlackListCustomer(driver);
			logoutPage = new Logout(driver);
			whiteListCustomerPage = new WhiteListCustomer(driver);
			whiteListMerchantIPPage = new WhiteList_MechantIP(driver);
			ftdwhiteListPage = new FTDWhiteListUser(driver);
			vpablackListPage = new VPABlackList(driver);
			stateblackListPage = new StateBlackList(driver);
			cityblackListPage = new CityBlackList(driver);
			fraudControlPage = new FraudControl(driver);
			depositTransactionPage = new DepositTransaction(driver);
			payoutTransactionPage = new PayoutTransaction(driver);
			dashboardPage = new Dashboard(driver);
			masterMerchantpage = new MasterMerchant(driver);
			gatewaylistpage = new GatewayList(driver);
			settingsDepositAssignPage = new Settings_DepositAssignProcessor(driver);
			searchTxhistoryPage = new SearchTxHistory(driver);
			
			
			ChargebackTxPage = new ChargebackTxReport(driver);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Setup failed: " + e.getMessage());
			throw new RuntimeException("Driver setup or page object creation failed");
		}
	}

	@AfterSuite
	public void tearDownSuite() {
		if (driver != null) {
			driver.quit();
			log.info("Browser closed after suite completion");
		}
	}
}
