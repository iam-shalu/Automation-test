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
import java.util.Date;
import java.util.Set;

import javax.imageio.ImageIO;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class CommonUtilis {

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

	// Wait and return latest download
	public static File waitForNewDownload(File dir, int timeoutInSeconds) throws InterruptedException {
		long end = System.currentTimeMillis() + (timeoutInSeconds * 1000L);
		while (System.currentTimeMillis() < end) {
			File[] files = dir.listFiles((d, name) -> name.endsWith(".csv") || name.endsWith(".xlsx"));
			if (files != null && files.length > 0) {
				for (File f : files) {
					if (!f.getName().endsWith(".crdownload") && f.canRead())
						return f;
				}
			}
			Thread.sleep(2000);
		}
		return null;
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

	// Generic calendar date picker
	public static void selectCalendarDate(WebDriver driver, WebElement field, WebElement dateOption) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(field)).click();
		wait.until(ExpectedConditions.elementToBeClickable(dateOption)).click();
	}
}
