package pom.qa.testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pom.base.BaseTest;
import pom.pages.LoginPage;

public class LoginPageTest extends BaseTest {
	LoginPage login;

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void startSetUp() {
		BrowserInitialize();
		login = new LoginPage();
	}

	@Test
	public void loginPageTitleTest(){
		Assert.assertEquals(login.getTitle(), prop.getProperty("title").toString());
	}
	
	//@Test
	public void crmLogoImageTest() {
		Assert.assertTrue(login.verifyLogo());
	}
	
	@Test
	public void verifyLogin() {
		login.gotoLogin();
		login.login(prop.getProperty("email"),prop.getProperty("password"));
	}
		
}
