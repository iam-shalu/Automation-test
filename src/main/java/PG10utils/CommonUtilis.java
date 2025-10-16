package PG10utils;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import PG10Base.PG10Base;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class CommonUtilis {
	public static Logger log = Logger.getLogger(PG10Base.class.getName());

	public static void takeScreenshot(WebDriver driver, String moduleName, String label) throws IOException {
		String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String timestamp = new SimpleDateFormat("HH-mm-ss").format(new Date());

		File baseDir = new File("screenshots" + File.separator + dateFolder + File.separator + moduleName);
		if (!baseDir.exists())
			baseDir.mkdirs();

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File outputFile = new File(baseDir, label + "_" + timestamp + ".png");
		Files.copy(screenshot.toPath(), outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

		System.out.println("Screenshot saved: " + outputFile.getAbsolutePath());
	}

	
	public static void captureFullPageScreenshot(WebDriver driver, String folderName, String baseFileName)
			throws IOException {
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Path destDir = Paths.get(System.getProperty("user.dir"), "screenshots", date, folderName);
		Files.createDirectories(destDir);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Scroll to bottom to load lazy content and wait until height stabilizes
		long lastHeight = (long) js.executeScript("return document.body.scrollHeight");
		for (int i = 0; i < 10; i++) { // max 10 scroll attempts
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			try {
				Thread.sleep(300); // short pause for render (better than long sleep)
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			long newHeight = (long) js.executeScript("return document.body.scrollHeight");
			if (newHeight == lastHeight)
				break;
			lastHeight = newHeight;
		}
		// Zoom out to capture more in frame (optional)
		js.executeScript("document.body.style.zoom='100%'");
		// Small visual stability wait using DOM polling
		new WebDriverWait(driver, Duration.ofSeconds(5))
				.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
		Screenshot screenshot = new AShot()
				.shootingStrategy(ShootingStrategies.viewportPasting(ShootingStrategies.scaling(1.0f), 1000))
				.coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver);
		BufferedImage image = screenshot.getImage();
		String timestamp = new SimpleDateFormat("HH-mm-ss").format(new Date());
		String fullFileName = baseFileName + "_" + timestamp + ".png";
		File outputFile = destDir.resolve(fullFileName).toFile();
		ImageIO.write(image, "PNG", outputFile);
		System.out.println("Full-page screenshot saved at: " + outputFile.getAbsolutePath());
	}
	


	// Rename downloaded file
	public static void renameDownloadedFile(File file, String label) throws IOException {
		String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

		Path targetDir = Paths.get("downloads", dateFolder, label);
		Files.createDirectories(targetDir);

		String newFileName = label + "_" + timestamp + ".xlsx";
		Path targetPath = targetDir.resolve(newFileName);

		Files.move(file.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
		System.out.println("File moved to: " + targetPath);
	}

	// Scroll to top
	public static void scrollToTop(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
		System.out.println("Scrolled to top.");
	}

	// Switch to new browser tab
	public static void switchToNewTab(WebDriver driver) {
		String originalTab = driver.getWindowHandle();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(d -> driver.getWindowHandles().size() > 1);

		Set<String> allTabs = driver.getWindowHandles();
		for (String tab : allTabs) {
			if (!tab.equals(originalTab)) {
				driver.switchTo().window(tab);
				break;
			}
		}
	}

	public static boolean waitForFileDownload(String downloadDir, String fileExtension, int timeoutSeconds) {
		File dir = new File(downloadDir);
		int waited = 0;
		while (waited < timeoutSeconds) {
			File[] xlsxFiles = dir.listFiles((d, name) -> name.toLowerCase().endsWith(fileExtension));
			File[] crdownloadFiles = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".crdownload"));
			if (xlsxFiles != null && xlsxFiles.length > 0 && (crdownloadFiles == null || crdownloadFiles.length == 0)) {
				return true;
			}
			try {
				Thread.sleep(7000);
			} catch (InterruptedException e) {
				return false;
			}
			waited++;
		}

		return false;
	}
	
	public static void moveDownloadedFileToDatedFolder(String moduleName, String dateFolder) throws IOException {
	    String baseDir = "D:\\Automation\\pg10-automation\\ExcelFile";

	    // Create folder structure: baseDir\dateFolder\moduleName\Download
	    File targetDir = new File(baseDir, dateFolder + "\\" + moduleName);
	    File downloadDir = new File(targetDir, "Download");

	    if (!downloadDir.exists()) downloadDir.mkdirs();

	    // Find the latest downloaded .xlsx file in baseDir
	    File folder = new File(baseDir);
	    File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".xlsx"));

	    if (files != null && files.length > 0) {
	        File latestFile = Arrays.stream(files)
	                                .max(Comparator.comparingLong(File::lastModified))
	                                .orElse(null);

	        if (latestFile != null) {
	            // Move into Download folder
	            Path movedPath = Paths.get(downloadDir.getAbsolutePath(), latestFile.getName());
	            Files.move(latestFile.toPath(), movedPath, StandardCopyOption.REPLACE_EXISTING);
	            System.out.println("File moved to Download folder: " + movedPath);
	        }
	    } else {
	        System.err.println("No .xlsx file found in download folder.");
	    }
	}
	

	// Generic calendar date picker
	public static void selectCalendarDate(WebDriver driver, WebElement field, WebElement dateOption) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(field)).click();
		wait.until(ExpectedConditions.elementToBeClickable(dateOption)).click();
	}
}
