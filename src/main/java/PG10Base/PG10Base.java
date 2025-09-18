/*
 * package PG10Base;
 * 
 * import java.util.HashMap; import java.util.Map; import
 * org.apache.log4j.Logger; import org.apache.log4j.xml.DOMConfigurator; import
 * org.openqa.selenium.WebDriver; import
 * org.openqa.selenium.chrome.ChromeDriver; import
 * org.openqa.selenium.chrome.ChromeOptions; import
 * org.testng.annotations.AfterSuite; import org.testng.annotations.BeforeSuite;
 * import PG10PageObject.*; import io.github.bonigarcia.wdm.WebDriverManager;
 * 
 * public class PG10Base { public static Logger log =
 * Logger.getLogger(PG10Base.class.getName()); public static WebDriver driver;
 * public static Login loginPage; public static Transactions transactionPage;
 * public static BlackListCustomer blackListCustomerPage; public static Logout
 * logoutPage; public static WhiteListCustomer whiteListCustomerPage; public
 * static WhiteList_MechantIP whiteListMerchantIPPage; public static
 * Payout_BlackListCustomer payoutBlackListCustomerPage; public static
 * FTDWhiteListUser ftdwhiteListPage; public static VPABlackList
 * vpablackListPage; public static StateBlackList stateblackListPage; public
 * static CityBlackList cityblackListPage; public static FraudControl
 * fraudControlPage; public static DepositTransaction depositTransactionPage;
 * public static PayoutTransaction payoutTransactionPage; public static
 * Dashboard dashboardPage; public static ChargebackTxReport ChargebackTxPage;
 * public static MasterMerchant masterMerchantpage; public static GatewayList
 * gatewaylistpage; public static Settings_DepositAssignProcessor
 * settingsDepositAssignPage; public static Settings_PayoutProcessorAss
 * settingsPayoutAssignPage; public static SearchTxHistory searchTxhistoryPage;
 * 
 * @BeforeSuite public void setUpSuite() { try {
 * DOMConfigurator.configure("log4j.xml");
 * WebDriverManager.chromedriver().setup(); String downloadDir =
 * "D:\\Automation\\pg10-automation\\ExcelFile";
 * 
 * ChromeOptions options = new ChromeOptions(); Map<String, Object> prefs = new
 * HashMap<>(); prefs.put("download.default_directory", downloadDir);
 * prefs.put("download.prompt_for_download", false);
 * prefs.put("safebrowsing.enabled", true);
 * options.setExperimentalOption("prefs", prefs);
 * options.addArguments("--remote-allow-origins=*");
 * 
 * // ✅ Enable Headless Mode options.addArguments("--headless=new"); // modern
 * headless mode (Chrome 109+) options.addArguments("--disable-gpu"); // safe on
 * Windows options.addArguments("--window-size=1920,1080"); // set fixed window
 * size
 * 
 * driver = new ChromeDriver(options);
 * log.info("Browser launched in headless mode");
 * 
 * // UAT URL driver.get("https://uat.paygate10.com/Dashboard");
 * 
 * // Prod URL (uncomment if needed) //
 * driver.get("https://paygate10.com/Dashboard");
 * 
 * log.info("Navigated to PG10 login page");
 * 
 * // Page object initialization loginPage = new Login(driver); dashboardPage =
 * new Dashboard(driver); transactionPage = new Transactions(driver);
 * depositTransactionPage = new DepositTransaction(driver);
 * payoutTransactionPage = new PayoutTransaction(driver); blackListCustomerPage
 * = new BlackListCustomer(driver); whiteListCustomerPage = new
 * WhiteListCustomer(driver); whiteListMerchantIPPage = new
 * WhiteList_MechantIP(driver); ftdwhiteListPage = new FTDWhiteListUser(driver);
 * vpablackListPage = new VPABlackList(driver); stateblackListPage = new
 * StateBlackList(driver); cityblackListPage = new CityBlackList(driver);
 * fraudControlPage = new FraudControl(driver); masterMerchantpage = new
 * MasterMerchant(driver); gatewaylistpage = new GatewayList(driver);
 * settingsDepositAssignPage = new Settings_DepositAssignProcessor(driver);
 * settingsPayoutAssignPage = new Settings_PayoutProcessorAss(driver);
 * searchTxhistoryPage = new SearchTxHistory(driver); ChargebackTxPage = new
 * ChargebackTxReport(driver); logoutPage = new Logout(driver);
 * 
 * } catch (Exception e) { e.printStackTrace(); log.error("Setup failed: " +
 * e.getMessage()); throw new
 * RuntimeException("Driver setup or page object creation failed"); } }
 * 
 * @AfterSuite public void tearDownSuite() { if (driver != null) {
 * driver.quit(); log.info("Browser closed after suite completion"); } } }
 */


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
    public static Settings_PayoutProcessorAss settingsPayoutAssignPage;
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

            // 🔀 Switch between headless and normal based on system property
            boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
            if (headless) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
                log.info("Browser launched in headless mode");
            } else {
                log.info("Browser launched in normal mode");
            }

            driver = new ChromeDriver(options);

            // UAT URL
            driver.get("https://uat.paygate10.com/Dashboard");

            // Prod URL (uncomment if needed)
            // driver.get("https://paygate10.com/Dashboard");

            log.info("Navigated to PG10 login page");

            // Page object initialization
            loginPage = new Login(driver);
            dashboardPage = new Dashboard(driver);
            depositTransactionPage = new DepositTransaction(driver);
            payoutTransactionPage = new PayoutTransaction(driver);
            blackListCustomerPage = new BlackListCustomer(driver);
            whiteListCustomerPage = new WhiteListCustomer(driver);
            whiteListMerchantIPPage = new WhiteList_MechantIP(driver);
            ftdwhiteListPage = new FTDWhiteListUser(driver);
            vpablackListPage = new VPABlackList(driver);
            stateblackListPage = new StateBlackList(driver);
            cityblackListPage = new CityBlackList(driver);
            fraudControlPage = new FraudControl(driver);
            masterMerchantpage = new MasterMerchant(driver);
            gatewaylistpage = new GatewayList(driver);
            settingsDepositAssignPage = new Settings_DepositAssignProcessor(driver);
            settingsPayoutAssignPage = new Settings_PayoutProcessorAss(driver);
            searchTxhistoryPage = new SearchTxHistory(driver);
            ChargebackTxPage = new ChargebackTxReport(driver);
            logoutPage = new Logout(driver);

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







