package pom.qa.testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import pom.base.BaseTest;
import pom.pages.HomePage;
import pom.pages.LoginPage;

public class HomePageTest extends BaseTest {
	LoginPage login;
	HomePage home;

	@BeforeMethod
	public void startSetUp() {
		BrowserInitialize();
		login = new LoginPage();
		login.gotoLogin();
		home = login.login(prop.getProperty("email"), prop.getProperty("password"));
	}

	@Test
	public void VerifyHomepage() {
		extentTest = extentReports.createTest("Verify HomePage");
		extentTest.log(home.verifyHomePage() == true ? Status.PASS : Status.FAIL, "HomePage is not loaded");
		Assert.assertTrue(home.verifyHomePage(), "HomePage is not loaded");
	}

	@Test
	public void VerifyUserName() {
		extentTest = extentReports.createTest("Verify UserName on HomePage");
		extentTest.log(home.getUserName() == prop.getProperty("username") ? Status.PASS : Status.FAIL,
				"UserName is not valid");
//		Assert.assertEquals(home.getUserName(), prop.getProperty("username"),"UserName is not valid");
	}

}
