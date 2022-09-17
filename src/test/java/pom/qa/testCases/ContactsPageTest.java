package pom.qa.testCases;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import pom.base.BaseTest;

public class ContactsPageTest extends BaseTest {
	@Test
	public void actions() {
		Actions act= new Actions(driver);
		act.moveToElement(null);
	}

}
