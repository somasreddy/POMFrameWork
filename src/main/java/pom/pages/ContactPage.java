package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import pom.base.BaseTest;

public class ContactPage extends BaseTest {
	String country = "India";

	public ContactPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//i[@class='edit icon']")
	WebElement createCont;

	@FindBy(xpath = "//input[@name='first_name']")
	WebElement firstName;

	@FindBy(xpath = "//input[@name='last_name']")
	WebElement lastName;

	@FindBy(xpath = "//div[@name='status']/i")
	WebElement statusDropDwn;

	@FindBy(xpath = "//button/i[@class='save icon']")
	WebElement saveBtn;

	@FindBy(xpath = "//button/i[@class='cancel icon']")
	WebElement cancelBtn;

	@FindBy(xpath = "//div/label[.='Phone']/..//div[@class='divider text']")
	WebElement phoneDropDwn;

	@FindBy(xpath = "//input[@placeholder='Number']")
	WebElement numberField;

	/*
	 * @FindBy(xpath="") WebElement ;
	 * 
	 * @FindBy(xpath="") WebElement ;
	 */

	public void createContact(String fn, String ln, String status, String country, String mobileNo) {
		createCont.click();
		firstName.sendKeys(fn);
		lastName.sendKeys(ln);
		Select select = new Select(statusDropDwn);
		select.selectByVisibleText(status);
		phoneDropDwn.sendKeys(country);
		driver.findElement(By.xpath("//div[2]/span[@class='text'][.='" + country + "']")).click();
		numberField.sendKeys(mobileNo);
		saveBtn.click();
	}

}
