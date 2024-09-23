package pom.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import pom.util.BaseUtil;

public class BaseTest {

    public static WebDriver driver;
    public static Properties prop;
    public static WebDriverWait wait;
    static ExtentSparkReporter extentSparkReporter;
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;

    public BaseTest() {
	try {
	    prop = new Properties();
	    FileInputStream fileIN = new FileInputStream(
		    System.getProperty("user.dir") + "/src/main/java/pom/config/config.properties");
	    prop.load(fileIN);
	} catch (FileNotFoundException fne) {
	} catch (IOException ioe) {
	}
    }

    public static void BrowserInitialize() {
	String browser = prop.getProperty("browser");
	if (browser.equalsIgnoreCase("chrome")) {
	    WebDriverManager.chromedriver().setup();
	    ChromeOptions opt = new ChromeOptions();
	    opt.addArguments("--incognito");
	    opt.setAcceptInsecureCerts(true);
	    driver = new ChromeDriver(opt);
	} else if (browser.equalsIgnoreCase("firefox")) {
	    WebDriverManager.firefoxdriver().setup();
	    FirefoxOptions opt = new FirefoxOptions();
	    driver = new FirefoxDriver(opt);
	} else if (browser.equalsIgnoreCase("edge")) {
	    WebDriverManager.edgedriver().setup();
	    EdgeOptions opt = new EdgeOptions();
	    driver = new EdgeDriver(opt);
	} else if (browser.equalsIgnoreCase("safari")) {
	    WebDriverManager.safaridriver().setup();
	    SafariOptions opt = new SafariOptions();
	    driver = new SafariDriver(opt);
	}
	try {
	    Thread.sleep(3000);
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	driver.get(prop.getProperty("url"));
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Long.parseLong(prop.getProperty("pltimeout"))));
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(prop.getProperty("impWait"))));
	wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(prop.getProperty("wait"))));

	extentReports = new ExtentReports();
	extentSparkReporter = new ExtentSparkReporter("test-output/extent/index.html");
	extentReports.attachReporter(extentSparkReporter);

    }

    @AfterMethod
    public void tearDownAndtakeScreenShotOnFailure(ITestResult testResult) throws IOException {
	if (testResult.getStatus() == ITestResult.FAILURE) {
	    File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(scrFile, new File("./errorScreenshots/" + testResult.getName() + "-"
		    + Arrays.toString(testResult.getParameters()) + ".jpg"));
	}
	driver.quit();
    }

    @AfterClass
    public void publishReport() {
	extentReports.flush();
    }

}
