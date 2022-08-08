package pom.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pom.base.BaseTest;

public class LoginPage extends BaseTest {

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[contains(@src,'freecrm_logo')]")
	WebElement crmLogo;

	@FindBy(xpath = "//span[contains(text(),'Log In')]")
	WebElement loginLink;

	@FindBy(name = "email") //// input[contains(@name,'email')]
	WebElement emailField;

	@FindBy(name = "password")
	WebElement passwordField;

	@FindBy(xpath = "//input[@name='password']/../../../div[.='Login']")
	WebElement loginBtn;

	@FindBy(xpath = "//a[contains(.,'Forgot your password?')]")
	WebElement forgotPwd;

	@FindBy(xpath = "//a[contains(text(),'Sign Up')]")
	WebElement signUp;

	public boolean verifyLogo() {
		return crmLogo.isDisplayed();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void gotoLogin() {
		loginLink.click();
	}

	public HomePage login(String email, String pwd) {
		emailField.sendKeys(email);
		passwordField.sendKeys(pwd);
		loginBtn.click();
		return new HomePage();
	}

}
