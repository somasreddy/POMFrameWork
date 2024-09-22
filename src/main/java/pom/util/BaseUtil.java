package pom.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import pom.base.BaseTest;

public class BaseUtil extends BaseTest {
    public static String xlPath = System.getProperty("user.dir") + "/src/main/java/pom/config/FreeCrmTestData.xlsx";

    static Workbook book;
    static Sheet sheet;

    public void switchToFrame() {
	driver.switchTo().frame("mainpanel");
    }

    public static Object[][] getTestDataFromXl(String sheetName) {
	FileInputStream fis = null;
	try {
	    fis = new FileInputStream(xlPath);
	} catch (FileNotFoundException fnfe) {
	}
	try {
	    book = WorkbookFactory.create(fis);
	} catch (IOException ioe) {
	}
	sheet = book.getSheet(sheetName);
	int lastRow = sheet.getLastRowNum();
	int lastCell = sheet.getRow(0).getLastCellNum();
	Object[][] data = new Object[lastRow][lastCell];
	for (int i = 0; i < lastRow; i++) {
	    for (int j = 0; j < lastCell; j++) {
		data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
	    }
	}
	return data;
    }

    public static void takeScreenshot() throws IOException {

	File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	try {
	    // save the screenshot taken in destination path
	    FileUtils.copyFile(srcfile,
		    new File(System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

}
