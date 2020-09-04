package TestCases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageClasses.HondaBikesPage;
import PageClasses.LandingPage;
import PageClasses.NewBikesPage;
import baseClasses.BaseTestClass;
import baseClasses.PageBaseClass;

public class UpcomingHondaBikes extends BaseTestClass {
	LandingPage landingPage;
	NewBikesPage newBikesPage;
	HondaBikesPage hondaBikesPage;
	
	@Parameters("browser")
	@Test(groups= {"regressionTest"})
	public void upcomingHondaBikesTest(String browser){
		logger = report.createTest("Upcoming Honda Bikes Less Than 4 Lakhs");
		display=1;
		invokeBrowser(browser);
		PageBaseClass pageBase = new PageBaseClass(driver, logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication();
		landingPage.getTitle("ZigWheels - New Cars, Used Cars, Bikes Prices, News, Reviews, Forum");
		newBikesPage = landingPage.clickNewBikesLink();
		newBikesPage.getTitle("Bikes in India, New Bikes in 2020, New Model Prices, Offers, Image @ ZigWheels");
		hondaBikesPage = newBikesPage.clickHondaBikesLink();
		hondaBikesPage.getTitle("Honda Bikes Price in India, New Honda Bike Models 2020, Reviews, News, Images, Specs @ ZigWheels");
		hondaBikesPage.extractUpcomingHondaBikeData();
	}
	
	@Parameters("browser")
	@Test(groups= {"smokeTest"})
	public void upcomingHondaBikesTestSmoke(String browser){
		logger = report.createTest("Upcoming Honda Bikes Less Than 4 Lakhs");
		display=1;
		invokeBrowser(browser);
		PageBaseClass pageBase = new PageBaseClass(driver, logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication();
		landingPage.getTitle("ZigWheels - New Cars, Used Cars, Bikes Prices, News, Reviews, Forum");
		newBikesPage = landingPage.clickNewBikesLink();
		newBikesPage.getTitle("Bikes in India, New Bikes in 2020, New Model Prices, Offers, Image @ ZigWheels");
		hondaBikesPage = newBikesPage.clickHondaBikesLink();
		hondaBikesPage.getTitle("Honda Bikes Price in India, New Honda Bike Models 2020, Reviews, News, Images, Specs @ ZigWheels");
	}
}

