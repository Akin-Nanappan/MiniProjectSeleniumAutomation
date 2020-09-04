package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.PageBaseClass;

public class NewBikesPage extends PageBaseClass{
	public NewBikesPage(WebDriver driver, ExtentTest logger) {
		super(driver,logger);
	}
	@FindBy(xpath="//a[@class='pt-15'][contains(text(),'Honda')]")
	public WebElement hondaBikesLink;
	
	public HondaBikesPage clickHondaBikesLink() {
		logger.log(Status.INFO, "Clicking the Honda Bikes icon");
		hondaBikesLink.click();
		logger.log(Status.PASS, "Clicked the New Honda Bikes icon");
		HondaBikesPage hondaBikesPage = new HondaBikesPage(driver, logger);
		PageFactory.initElements(driver, hondaBikesPage);
		return hondaBikesPage;
	}

}
