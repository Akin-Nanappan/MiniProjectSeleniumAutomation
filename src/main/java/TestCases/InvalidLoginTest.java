package TestCases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import PageClasses.LandingPage;
import PageClasses.Pages;
import baseClasses.BaseTestClass;
import baseClasses.PageBaseClass;
import utilities.DateUtil;
import utilities.ExcelRead;

public class InvalidLoginTest extends BaseTestClass {
	LandingPage landingPage;
	Pages p;

	@Parameters("browser")
	@BeforeTest(groups = { "regressionTest", "smokeTest" })
	public void BeforeTest(String browser) {
		logger = report.createTest("Passing Control to new Page");
		display = 0;
		invokeBrowser(browser);
		PageBaseClass pageBase = new PageBaseClass(driver, logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication();
		p = landingPage.passingControl();
		logger.log(Status.INFO, "Passed Control to new Page");
	}

	@Test(groups = { "regressionTest", "smokeTest" })
	public void profile_btn_and_google() throws InterruptedException {
		p.profile();
		Thread.sleep(3000);
		p.google();
		Thread.sleep(3000);

	}

// invalid mail
	@Test(priority = 1, groups = { "regressionTest" })
	public void invalidmail() throws Exception {
		ExcelRead e = new ExcelRead();
		String eid = e.eid(0, 0);
		Thread.sleep(2000);
		logger = report.createTest("Invalid mail");
		// Thread.sleep(5000);
		p.email(eid);
		Thread.sleep(2000);
		p.next1();
		Thread.sleep(2000);
		logger.log(Status.INFO, "Capturing the error message");
		System.out.println("error message for entering mail id:");
		p.Text1();
		Thread.sleep(3000);
		p.takeScreenShotOnFailure();
		logger.addScreenCaptureFromPath(
				System.getProperty("user.dir") + "/ScreenShots/" + DateUtil.getTimeStamp() + ".png");
		logger.log(Status.PASS, "Error message Captured Successfully");
	}

// null mail
	@Test(priority = 2, groups = { "regressionTest" })
	public void nullmail() throws InterruptedException, IOException {
		String eid = null;
		logger = report.createTest("Null Mail");
		p.emailclear(eid);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		p.next1();
		Thread.sleep(5000);
		logger.log(Status.INFO, "Capturing the error message");
		System.out.println("error message for entering null mail:");
		p.Text1();
		Thread.sleep(5000);
		p.takeScreenShotOnFailure();
		logger.addScreenCaptureFromPath(
				System.getProperty("user.dir") + "/ScreenShots/" + DateUtil.getTimeStamp() + ".png");
		logger.log(Status.PASS, "Error message Captured Successfully");
	}

// invalid phone number
	@Test(priority = 3, groups = { "regressionTest" })
	public void invalidphn() throws Exception {
		ExcelRead e = new ExcelRead();
		String eid = e.eid(2, 0);
		logger = report.createTest("Invalid Phone Number");
		Thread.sleep(2000);
		p.emailclear(eid);
		p.email(eid);
		p.next1();
		Thread.sleep(5000);
		logger.log(Status.INFO, "Capturing the error message");
		System.out.println("error message for entering invalid phone number:");
		p.Text1();
		Thread.sleep(5000);
		p.takeScreenShotOnFailure();
		logger.addScreenCaptureFromPath(
				System.getProperty("user.dir") + "/ScreenShots/" + DateUtil.getTimeStamp() + ".png");
		logger.log(Status.PASS, "Error message Captured Successfully");
	}

// valid mail and invalid password
	@Test(priority = 4, groups = { "regressionTest" })
	public void v_mail_inv_pswd() throws Exception {
		ExcelRead e = new ExcelRead();
		String eid = e.eid(3, 0);
		String pass = e.eid(3, 1);
		logger = report.createTest("Vaild Mail and Invalid Password");
		p.emailclear(eid);
		p.email(eid);
		p.next1();
		Thread.sleep(2000);
		p.password(pass);
		p.next2();
		Thread.sleep(2000);
		logger.log(Status.INFO, "Capturing the error message");
		System.out.println("error message for entering valid mail and invalid password:");
		p.Text2();
		Thread.sleep(3000);
		p.takeScreenShotOnFailure();
		p.navigateback();
		logger.addScreenCaptureFromPath(
				System.getProperty("user.dir") + "/ScreenShots/" + DateUtil.getTimeStamp() + ".png");
		logger.log(Status.PASS, "Error message Captured Successfully");

	}

// valid phone number invalid password
	@Test(priority = 5, groups = { "regressionTest" })
	public void v_phn_inv_pswd() throws Exception {
		ExcelRead e = new ExcelRead();
		String eid = e.eid(4, 0);
		String pass = e.eid(4, 1);
		logger = report.createTest("Valid Phone and Invalid Password");
		p.navigateback();
		Thread.sleep(2000);
		p.emailclear(eid);
		p.email(eid);
		p.next1();
		Thread.sleep(2000);
		p.password(pass);
		p.next2();
		logger.log(Status.INFO, "Capturing the error message");
		System.out.print("error message for entering valid phone number and invalid password:");
		p.Text2();
		Thread.sleep(4000);
		p.takeScreenShotOnFailure();
		p.navigateback();
		logger.addScreenCaptureFromPath(
				System.getProperty("user.dir") + "/ScreenShots/" + DateUtil.getTimeStamp() + ".png");
		logger.log(Status.PASS, "Error message Captured Successfully");
		Thread.sleep(2000);

	}

// valid mail null password
	@Test(priority = 6, groups = { "regressionTest" })
	public void v_mail_null_pswd() throws Exception {
		ExcelRead e = new ExcelRead();
		String eid = e.eid(5, 0);
		logger = report.createTest("Valid Mail and Null password");
		Thread.sleep(2000);
		p.navigateback();
		p.emailclear(eid);
		p.email(eid);
		p.next1();
		Thread.sleep(2000);
		p.next2();
		Thread.sleep(5000);
		logger.log(Status.INFO, "Capturing the error message");
		System.out.println("error message for entering valid mail and null password:");
		p.Text3();
		Thread.sleep(5000);
		p.takeScreenShotOnFailure();
		logger.addScreenCaptureFromPath(
				System.getProperty("user.dir") + "/ScreenShots/" + DateUtil.getTimeStamp() + ".png");
		logger.log(Status.PASS, "Error message Captured Successfully");
	}

}
