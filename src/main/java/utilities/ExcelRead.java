package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {
	public String eid(int r,int c) throws IOException {
		FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir")+"\\TestData\\zigwheels.xlsx"));
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		XSSFRow row = sheet.getRow(r);
		XSSFCell cell = row.getCell(c);
		String eid = cell.getStringCellValue();
		return eid;
		}

}

