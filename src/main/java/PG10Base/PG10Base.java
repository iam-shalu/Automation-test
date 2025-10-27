package PG10Base;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import PG10PageObject.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PG10Base {
    public static Logger log = Logger.getLogger(PG10Base.class.getName());
    public static WebDriver driver;
    public static WebDriverWait wait;

    // Page objects
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
    public static Settings_PayoutrAssignProcessor settingsPayoutAssignPage;
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

             boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));
            // boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

            if (headless) {
            	options.addArguments("--headless=new"); // simpler and more reliable
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080"); // full HD
                options.addArguments("--force-device-scale-factor=1");
                options.addArguments("--start-maximized"); 
                
                log.info("Browser launched in headless mode (full screen)");
            } else {
                log.info("Browser launched in normal mode");

            }
            
            driver = new ChromeDriver(options);
            wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));

            // UAT URL
       //    driver.get("https://uat.paygate10.com/Dashboard");
            
            driver.get("https://paygate10.com/");
            log.info("Navigated to PG10 login page");

            // ✅ Expand sidebar (important for headless)
            expandSidebarIfCollapsed();

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
            settingsPayoutAssignPage = new Settings_PayoutrAssignProcessor(driver);
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
    
    public static void expandSidebarIfCollapsed() {
        try {
            WebElement toggle = driver.findElement(By.xpath("//button[contains(@class,'menu-toggle')]"));
            if (toggle.isDisplayed()) {
                toggle.click();
                log.info("Sidebar expanded for headless mode");
            }
        } catch (Exception e) {
            log.info("Sidebar already expanded or toggle not found");
        }
    }
    
    //  Safe click (scroll + fallback JS click)
    public static void safeClick(By locator) {
        try {
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
            el.click();
        } catch (Exception e) {
            WebElement el = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
            log.info("Clicked using JS fallback: " + locator);
            
        }
    }
    
}
