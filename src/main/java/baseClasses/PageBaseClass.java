package baseClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import PageClasses.LandingPage;
import utilities.DateUtil;

public class PageBaseClass extends BaseTestClass{
	public ExtentTest logger;
	public Properties prop;
	
	public PageBaseClass(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		if (prop == null) {
			prop = new Properties();
			try {
				FileInputStream file = new FileInputStream(System.getProperty("user.dir")
						+ "\\ObjectRepository\\projectConfig.Properties");
				prop.load(file);
			} catch (Exception e) {
				reportFail(e.getMessage());	
			}
		}
	}

	/****************** OpenApplication ***********************/
	public LandingPage OpenApplication() {
		logger.log(Status.INFO, "Opening the WebSite");
		driver.get(prop.getProperty("websiteURL"));
		logger.log(Status.PASS, "Successfully Opened the "+ prop.getProperty("websiteURL"));
		LandingPage landingPage = new LandingPage(driver, logger);
		PageFactory.initElements(driver, landingPage);
		return landingPage;
	}
	/****************** Get Page Title ***********************/
	public void getTitle(String expectedTitle) {
		try {
			Assert.assertEquals(driver.getTitle(), expectedTitle);
			reportPass("Actual Title : " + driver.getTitle() + " - equals to Expected Title : " + expectedTitle);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}
	/****************** Reporting Functions ***********************/
	public void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		takeScreenShotOnFailure();
		Assert.fail(reportString);
	}

	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}

	/****************** Capture Screen Shot ***********************/
	public void takeScreenShotOnFailure() {
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);

		File destFile = new File(System.getProperty("user.dir") + "/ScreenShots/" + DateUtil.getTimeStamp() + ".png");
		try {
			FileUtils.copyFile(sourceFile, destFile);
			logger.addScreenCaptureFromPath(
					System.getProperty("user.dir") + "/ScreenShots/" + DateUtil.getTimeStamp() + ".png");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	// To Identify the pageElement
		public WebElement getElement(String locatorKey) {
			WebElement element = null;
			try {
				if (locatorKey.endsWith("_Id")) {
					element = driver.findElement(By.id(prop.getProperty(locatorKey)));
					logger.log(Status.INFO,"Locator Identified :" +locatorKey);
				} else if (locatorKey.endsWith("_Xpath")) {
					element = driver.findElement(By.xpath(prop.getProperty(locatorKey)));
					logger.log(Status.INFO,"Locator Identified :" +locatorKey);
				} else if (locatorKey.endsWith("_CSS")) {
					element = driver.findElement(By.cssSelector(prop.getProperty(locatorKey)));
					logger.log(Status.INFO,"Locator Identified :" +locatorKey);
				} else if (locatorKey.endsWith("_LinkText")) {
					element = driver.findElement(By.linkText(prop.getProperty(locatorKey)));
					logger.log(Status.INFO,"Locator Identified :" +locatorKey);
				} else if (locatorKey.endsWith("_PartialLinkText")) {
					element = driver.findElement(By.partialLinkText(prop.getProperty(locatorKey)));
					logger.log(Status.INFO,"Locator Identified :" +locatorKey);
				} else if (locatorKey.endsWith("_Name")) {
					element = driver.findElement(By.name(prop.getProperty(locatorKey)));
					logger.log(Status.INFO,"Locator Identified :" +locatorKey);
				}else {
					reportFail("Failing the TestCase,Invalid Locator " +locatorKey);
					Assert.fail("Failing the TestCase,Invalid Locator " +locatorKey);
				}
			} catch (Exception e) {
				reportFail(e.getMessage());
				e.printStackTrace();
				Assert.fail("Failing the TestCase : " + e.getMessage());
			}
			return element;
		}
		//To Identify List of WebElements
		public List<WebElement> getListElement(String locatorKey) {
			List<WebElement> element = null;
			try {
				if (locatorKey.endsWith("_IdList")) {
					element = driver.findElements(By.id(prop.getProperty(locatorKey)));
					logger.log(Status.INFO,"Locator Identified :" +locatorKey);
				} else if (locatorKey.endsWith("_XpathList")) {
					element = driver.findElements(By.xpath(prop.getProperty(locatorKey)));
					logger.log(Status.INFO,"Locator Identified :" +locatorKey);
				} else if (locatorKey.endsWith("_CSSList")) {
					element = driver.findElements(By.cssSelector(prop.getProperty(locatorKey)));
					logger.log(Status.INFO,"Locator Identified :" +locatorKey);
				} else if (locatorKey.endsWith("_LinkTextList")) {
					element = driver.findElements(By.linkText(prop.getProperty(locatorKey)));
					logger.log(Status.INFO,"Locator Identified :" +locatorKey);
				} else if (locatorKey.endsWith("_PartialLinkTextList")) {
					element = driver.findElements(By.partialLinkText(prop.getProperty(locatorKey)));
					logger.log(Status.INFO,"Locator Identified :" +locatorKey);
				} else if (locatorKey.endsWith("_NameList")) {
					element = driver.findElements(By.name(prop.getProperty(locatorKey)));
					logger.log(Status.INFO,"Locator Identified :" +locatorKey);
				}else {
					reportFail("Failing the TestCase,Invalid Locator " +locatorKey);
					Assert.fail("Failing the TestCase,Invalid Locator " +locatorKey);
				}
			} catch (Exception e) {
				reportFail(e.getMessage());
				e.printStackTrace();
				Assert.fail("Failing the TestCase : " + e.getMessage());
			}
			return element;
		}
		//Verify Element
		public boolean isElementPresent(String locatorKey) {
			try {
				if(getElement(locatorKey).isDisplayed()) {
					reportPass(locatorKey +" : Element is displayed");
					return true;
				}
			}catch(Exception e) {
				reportFail(e.getMessage());
			}
			return false;
		}
		public boolean isElementSelected(String locatorKey) {
			try {
				if(getElement(locatorKey).isSelected()) {
					reportPass(locatorKey +" : Element is selected");
					return true;
				}
			}catch(Exception e) {
				reportFail(e.getMessage());
			}
			return false;
		}
		public boolean isElementEnabled(String locatorKey) {
			try {
				if(getElement(locatorKey).isEnabled()) {
					reportPass(locatorKey +" : Element is enabled");
					return true;
				}
			}catch(Exception e) {
				reportFail(e.getMessage());
			}
			return false;
		}
}
