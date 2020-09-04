package TestCases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageClasses.LandingPage;
import PageClasses.UsedCarsInChennaiPage;
import baseClasses.BaseTestClass;
import baseClasses.PageBaseClass;

public class PopularModelsInChennai extends BaseTestClass{
	LandingPage landingPage;
	UsedCarsInChennaiPage carsChennai;
	
	@Parameters("browser")
	@Test(groups= {"regressionTest"})
	public void popularModelsTestUsingMinMax(String browser) throws InterruptedException{
		logger = report.createTest("Popular Models In Chennai by selecting min max");
		display=1;
		invokeBrowser(browser);
		PageBaseClass pageBase = new PageBaseClass(driver, logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication();
		landingPage.getTitle("ZigWheels - New Cars, Used Cars, Bikes Prices, News, Reviews, Forum");
		landingPage.clickUsedCars();
		carsChennai = landingPage.clickUsedCarsInChennai();
		carsChennai.selectMinmaxvalues();
		carsChennai.getTitle("Used Cars for Sale, Buy Second Hand Cars in India @ Zigwheels");
		carsChennai.extractCarData();
		driver.close();
	}
	
	@Parameters("browser")
	@Test(groups= {"regressionTest"})
	public void popularModelsTestUsingRadioButton(String browser) throws InterruptedException{
		logger = report.createTest("Popular Models In Chennai by clicking the radio button");
		display=1;
		invokeBrowser(browser);
		PageBaseClass pageBase = new PageBaseClass(driver, logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication();
		landingPage.getTitle("ZigWheels - New Cars, Used Cars, Bikes Prices, News, Reviews, Forum");
		landingPage.clickUsedCars();
		carsChennai = landingPage.clickUsedCarsInChennai();
		carsChennai.priceFromRadioButton();
		carsChennai.getTitle("Used cars Over 20 Lakhs in Chennai, Second Hand Cars Above 20 Lakhs @ ZigWheels");
		carsChennai.extractCarData();
	}
	
	@Parameters("browser")
	@Test(groups= {"smokeTest"})
	public void popularModelsTestSmoke(String browser) throws InterruptedException{
		logger = report.createTest("Popular Models In Chennai by selecting min max");
		display=1;
		invokeBrowser(browser);
		PageBaseClass pageBase = new PageBaseClass(driver, logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication();
		landingPage.getTitle("ZigWheels - New Cars, Used Cars, Bikes Prices, News, Reviews, Forum");
		landingPage.clickUsedCars();
		carsChennai = landingPage.clickUsedCarsInChennai();
	}
}
