package PageClasses;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.PageBaseClass;

public class HondaBikesPage extends PageBaseClass{
	public HondaBikesPage(WebDriver driver, ExtentTest logger) {
		super(driver,logger);
	}
	public void extractUpcomingHondaBikeData() {
		logger.log(Status.INFO, "Extracting upcoming Honda Bike data");
		WebElement scrollElement = getElement("scrollElement_Xpath");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", scrollElement);
		List<WebElement> upcomingBikeCount = getListElement("upcomingHondaBikes_XpathList");
		//System.out.println(upcomingBikeCount.size());
		int count = upcomingBikeCount.size();
		WebDriverWait wait = new WebDriverWait(driver,10);
		for(int i=1;i<=count;i++) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='upcoming']/descendant::li["+i+"]/a")));
			WebElement bikeName = driver.findElement(By.xpath("//div[@id='upcoming']/descendant::li["+i+"]/a"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='upcoming']/descendant::li["+i+"]/div[1]")));
			WebElement bikePrice = driver.findElement(By.xpath("//div[@id='upcoming']/descendant::li["+i+"]/div[1]"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='upcoming']/descendant::li["+i+"]/div/span/..")));
			WebElement bikeDate = driver.findElement(By.xpath("//div[@id='upcoming']/descendant::li["+i+"]/div/span/.."));
			String price1 = bikePrice.getText();
			String price2 =price1.replace("Rs.", "");
			boolean isLakhs = price1.contains("Lakh");
			if(isLakhs) {
				String price3= price2.replaceAll("[^0-9.0-9]", "");
				float price4 = Float.parseFloat(price3) * 100000;
				int price5 = (int)price4;
				//System.out.println(price5);
				if(price5<400000) {
					System.out.print(bikeName.getAttribute("title")+"\t");
					System.out.print(bikePrice.getText()+"\t");
					System.out.print(bikeDate.getText()+"\n");
				}
			}else {
				System.out.print(bikeName.getAttribute("title")+"\t");
				System.out.print(bikePrice.getText()+"\t");
				System.out.print(bikeDate.getText()+"\n");
			}
			if(i >2 && i<count) {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("uNextArrow_Xpath"))));
				WebElement uNextArrow = getElement("uNextArrow_Xpath");
				uNextArrow.click();
			}catch(Exception e) {
				break;
			} }
		}
		logger.log(Status.PASS, "Extracted upcoming Honda Bike data and displayed in Console");
	}
}
