package PageClasses;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.PageBaseClass;

public class LandingPage extends PageBaseClass {
	public LandingPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}

	@FindBy(xpath = "//ul[@class='h-d-nav fnt-16']//a[contains(text(),'New Bikes')]")
	public WebElement newBikesLink;
	
	@FindBy(partialLinkText="Used Cars in Chenn")
	public WebElement usedCarsInChennai;
	
	public NewBikesPage clickNewBikesLink() {
		logger.log(Status.INFO, "Clicking the New Bikes option present in the top bar");
		newBikesLink.click();
		logger.log(Status.PASS, "Clicked the New Bikes option");
		NewBikesPage newBikesPage = new NewBikesPage(driver, logger);
		PageFactory.initElements(driver, newBikesPage);
		return newBikesPage;
	}

	public Pages passingControl() {
		logger.log(Status.INFO, "Passing Control to Login Functionality");
		Pages p = new Pages(driver, logger);
		PageFactory.initElements(driver, p);
		return p;
	}

	public void clickUsedCars() {
		logger.log(Status.INFO, "Clicking the Used Cars option present in the top bar");
		Actions action = new Actions(driver);
		WebElement usedcars = getElement("usedCars_LinkText");
		action.moveToElement(usedcars).build().perform();
		logger.log(Status.PASS, "Clicked the Used Cars option");
	}

	public UsedCarsInChennaiPage clickUsedCarsInChennai() {
		logger.log(Status.INFO, "Clicking the Used Cars in Chennai from the dropdown");
		usedCarsInChennai.click();
		logger.log(Status.PASS, "Clicked the Used Cars in Chennai option");
		UsedCarsInChennaiPage carsChennai = new UsedCarsInChennaiPage(driver,logger);
		PageFactory.initElements(driver, carsChennai);
		return carsChennai;
	}

}
