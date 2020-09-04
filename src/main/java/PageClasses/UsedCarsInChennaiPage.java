package PageClasses;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.PageBaseClass;

public class UsedCarsInChennaiPage extends PageBaseClass {
	WebDriverWait wait = new WebDriverWait(driver,30);
	public UsedCarsInChennaiPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}

	public void selectMinmaxvalues() throws InterruptedException {
		Thread.sleep(3000);
		System.out.println("Car names Between 9-10 Lakhs");
		Select min = new Select(getElement("min_Xpath"));
		logger.log(Status.INFO, "Selecting the 9 Lakh option from Min values dropdown");
		min.selectByIndex(9);
		logger.log(Status.PASS, "Selected the 9 Lakh option from Min values dropdown");
		Thread.sleep(3000);
		logger.log(Status.INFO, "Selecting the 10 Lakh option from Max values dropdown");
		Select max = new Select(getElement("max_Xpath"));
		max.selectByIndex(10);
		logger.log(Status.PASS, "Selected the 10 Lakh option from Max values dropdown");
		Thread.sleep(3000);
	}

	public void priceFromRadioButton() throws InterruptedException {
		Thread.sleep(3000);
		System.out.println("Cars above 20 Lakhs");
		logger.log(Status.INFO, "Selecting the above 20 lakh option from the radio button");
		WebElement price = getElement("price_Xpath");
		price.click();
		logger.log(Status.PASS, "Selected above 20 lakh option from the radio button");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("price_Marker"))));
		Thread.sleep(3000);
	}
	
	public void extractCarData() {
		logger.log(Status.INFO, "Extracting the Car Data");
		WebElement totalCountElement = getElement("totalCountElement_Xpath");
		int totalCount = Integer.parseInt(totalCountElement.getText());
		System.out.println("Toatal number of Cars: " +totalCount);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		do {
			js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			List<WebElement> carNames = driver.findElements(By.xpath(prop.getProperty("carNames_XpathList")));
			if (carNames.size() == totalCount) {
				break;
			}
		} while (ExpectedConditions.invisibilityOfElementLocated(By.xpath(prop.getProperty("loading_Image"))) != null);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("carNames_XpathList"))));
		List<WebElement> carNames = getListElement("carNames_XpathList");
		Iterator<WebElement> itr = carNames.iterator();
		int i = 1;
		while (itr.hasNext()) {
			System.out.print(i + " ");
			System.out.print(itr.next().getText());
			i++;
			System.out.print("\n");
		}
		logger.log(Status.PASS, "Extracted the Car data");
	}


}
