package PageClasses;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;

import baseClasses.PageBaseClass;


public class Pages extends PageBaseClass{
	public Pages(WebDriver driver, ExtentTest logger) {
		super(driver,logger);
	}
	//PaageFactory
	
	@FindBy(xpath="//*[@id='des_lIcon']")
	WebElement profile_btn;
	 @FindBy(xpath="//span[contains(text(),'Continue with Google')]")
	WebElement google_btn;
	 @FindBy(id="identifierId")
	WebElement email_id;
	 @FindBy(xpath="//*[@id='identifierNext']")
	WebElement next1_btn;
	 @FindBy(xpath="//input[@name='password']")
	WebElement pwd ;
	 @FindBy(xpath="//div[@id='passwordNext']")
	WebElement next2_btn;
	 @FindBy(xpath="//div[@class='o6cuMc']")
	WebElement Text1;
	 @FindBy(xpath="//span[contains(text(),'Wrong password. Try again or click Forgot password')]")
	WebElement Text2;
	 @FindBy(xpath="//span[contains(text(),'Enter a password')]")
	WebElement Text3 ;


	public void profile() {
		profile_btn.click();
	}

	public void google() throws InterruptedException {
		google_btn.click();
		Set<String> windows = driver.getWindowHandles();
		for (String newwindow : windows) {
			driver.switchTo().window(newwindow);
			Thread.sleep(3000);
		}
	}

	public void email(String eid) throws InterruptedException {
		email_id.sendKeys(eid);
	}

	public void next1() {
		next1_btn.click();
	}

	public void password(String pass) throws InterruptedException {
		pwd.sendKeys(pass);
	}

	public void next2() {
		next2_btn.click();
	}

	public void emailclear(String eid) throws InterruptedException {
		email_id.clear();
	}

	public void pswdclear(String pass) throws InterruptedException {
		pwd.clear();
	}

	public void navigateback() {
		driver.navigate().back();
	}

	public void Text1() {
		System.out.println(Text1.getText());
	}

	public void Text2() {
		System.out.println(Text2.getText());
	}

	public void Text3() {
		System.out.println(Text3.getText());
	}

}
