package PG10Base;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public static Dashboard	dashboardPage;
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
            driver.get("https://paygate10.com/Login");
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
            searchTxhistoryPage = new SearchTxHistory(driver);
            
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Setup failed: " + e.getMessage());
            throw new RuntimeException("Driver setup or page object creation failed");
        }
    }
    
    public boolean waitForFileDownload(String downloadDir, String fileExtension, int timeoutSeconds) {
        File dir = new File(downloadDir);
        int waited = 0;

        while (waited < timeoutSeconds) {
            File[] xlsxFiles = dir.listFiles((d, name) -> name.toLowerCase().endsWith(fileExtension));
            File[] crdownloadFiles = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".crdownload"));

            if (xlsxFiles != null && xlsxFiles.length > 0 && (crdownloadFiles == null || crdownloadFiles.length == 0)) {
                return true; 
            }

            try {
                Thread.sleep(7000); // wait 1 sec
            } catch (InterruptedException e) {
                return false;
            }

            waited++;
        }

        return false; 
    }

    public void moveDownloadedFileToDatedFolder(String moduleName, String dateStr) {
        // Base download directory
        String baseDownloadDir = "D:\\Automation\\pg10-automation\\ExcelFile";
        File downloadFolder = new File(baseDownloadDir);

        // List all fully downloaded .xlsx files
        File[] xlsxFiles = downloadFolder.listFiles((dir, name) -> 
            name.toLowerCase().endsWith(".xlsx") && !name.toLowerCase().endsWith(".crdownload"));

        if (xlsxFiles == null || xlsxFiles.length == 0) {
            log.warn("No downloaded Excel file found to move.");
            return;
        }

        // Identify the most recently downloaded file
        File latestFile = xlsxFiles[0];
        for (File f : xlsxFiles) {
            if (f.lastModified() > latestFile.lastModified()) {
                latestFile = f;
            }
        }
        // Prepare final target directory: D:\Automation\pg10-automation\ExcelFile\2025-08-04\depositTransactions
        File targetDir = new File(baseDownloadDir + File.separator + dateStr + File.separator + moduleName);
        if (!targetDir.exists()) {
            if (targetDir.mkdirs()) {
                log.info("Created directory: " + targetDir.getAbsolutePath());
            } else {
                log.error("Failed to create target directory: " + targetDir.getAbsolutePath());
                return;
            }
        }

        // Generate timestamped destination file
        String timestamp = new SimpleDateFormat("HHmmss").format(new Date());
        File destinationFile = new File(targetDir, "Export_" + timestamp + ".xlsx");

        // Move file
        if (latestFile.renameTo(destinationFile)) {
            log.info("Exported file moved to: " + destinationFile.getAbsolutePath());
        } else {
            log.error("Failed to move file: " + latestFile.getAbsolutePath());
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
