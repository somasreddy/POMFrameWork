package pom.qa.testCases;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.base.BaseTest;
import pom.pages.ContactPage;
import pom.pages.HomePage;
import pom.pages.LoginPage;
import pom.util.BaseUtil;

public class ContactsPageTest extends BaseTest {

    LoginPage loginPage;
    HomePage homePage;
    BaseUtil testUtil;
    ContactPage contactsPage;

    String sheetName = "contacts";

    public ContactsPageTest() {
	super();

    }

    @BeforeMethod
    public void setUp() throws InterruptedException {

	BrowserInitialize();
	testUtil = new BaseUtil();
	contactsPage = new ContactPage();
	loginPage = new LoginPage();
	homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

	testUtil.switchToFrame();
	contactsPage = homePage.clickContact();
    }

    @Test
    public void actions() {
	Actions act = new Actions(driver);
	act.moveToElement(null);
    }

    @DataProvider
    public Object[][] getCRMTestData() {
	Object data[][] = BaseUtil.getTestDataFromXl("contacts");
	return data;
    }

    @Test(priority = 4, dataProvider = "getCRMTestData")
    public void validateCreateNewContact(String fn, String ln, String status, String country, String mobileNo) {
	homePage.clickContact();
	// contactsPage.createNewContact("Mr.", "Tom", "Peter", "Google");
	contactsPage.createContact(fn, ln, status, country, mobileNo);

    }
}
