package baseClasses;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
//import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utilities.ExtentReportManager;

public class BaseTestClass {
	public WebDriver driver;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;
	public Properties prop;
	public int display;

	/****************** Invoke Browser ***********************/
	public void invokeBrowser(String browserName) {

		try {

			if (browserName.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/drivers/chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				// options.addArguments("--no-sandbox");
				// options.addArguments("--disable-setuid-sandbox");
				options.addArguments("--disable-notifications");
				driver = new ChromeDriver(options);
				//System.out.println(driver.manage().window().getSize().getHeight());
				//System.out.println(driver.manage().window().getSize().getWidth());
			} else if (browserName.equalsIgnoreCase("Mozila")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "/drivers/geckodriver.exe");
				FirefoxProfile profile = new FirefoxProfile();
			    profile.setPreference("browser.cache.disk.enable", false);
			    profile.setPreference("browser.cache.memory.enable", false);
			    profile.setPreference("browser.cache.offline.enable", false);
			    profile.setPreference("network.http.use-cache", false);
			    FirefoxOptions options = new FirefoxOptions().setProfile(profile);
				//FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--disable-notifications");
				driver = new FirefoxDriver(options);
				driver.manage().window().setSize(new Dimension(1050,660));
			} else if (browserName.equalsIgnoreCase("IEbrowser")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "/drivers/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
		} catch (Exception e) {
			// reportFail(e.getMessage());
			System.out.println(e.getMessage());
		}

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		if (display == 1)
			driver.manage().window().maximize();
	}
	
	@AfterTest(groups = { "regressionTest", "smokeTest" })
	public void closeBrowser() {
		driver.quit();
	}
	//@Parameters("browser")
	@AfterSuite(groups = { "regressionTest", "smokeTest" })
	public void flushReports() {
		//report.setSystemInfo("Browser", browser);
		report.flush();
		//driver.close();
		driver.quit();
	}
}
