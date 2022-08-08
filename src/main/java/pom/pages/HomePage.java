package pom.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pom.base.BaseTest;

public class HomePage extends BaseTest {
	
	public HomePage(){
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//i[@class='home icon']")
	WebElement homeBtn;
	
	@FindBy(xpath="//span[@class='user-display']")
	WebElement userNameDisp;
	
	@FindBy(xpath="//span[contains(text(),'Contacts')]")	
	WebElement contactsLink;
	
	public boolean verifyHomePage() {
		return homeBtn.isDisplayed();
	}
	public String getUserName() {
		return userNameDisp.getText();
	}
	public ContactPage clickContact() {
		contactsLink.click();		
		return new ContactPage();
	}
}
