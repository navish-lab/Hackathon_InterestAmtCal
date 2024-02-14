package Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	static FileInputStream fileInputStream;
	static FileOutputStream fileOutputStream;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static XSSFRow row;
	static XSSFCell cell;
	static CellStyle style;
	
	public static String readExcelData(String sheetName,int rowNumber,int cellNumber) throws IOException {
		
		fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\test-data\\TestData_And_Validation.xlsx");
		
		workbook = new XSSFWorkbook(fileInputStream);
		
		sheet = workbook.getSheet(sheetName);
		
		row = sheet.getRow(rowNumber);
		
		cell = row.getCell(cellNumber);
		
		workbook.close();
		
		fileInputStream.close();
		
		return cell.toString();
		
	}
	
	public static void writeData(String sheetName,int rowNumber,int cellNumber,String data) throws IOException {
		
		fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\test-data\\TestData_And_Validation.xlsx");
		
		workbook = new XSSFWorkbook(fileInputStream);
		
		sheet = workbook.getSheet(sheetName);
		
		row = sheet.getRow(rowNumber);
		
		cell = row.getCell(cellNumber);
		
		cell.setCellValue(data);
		
		fileOutputStream = new FileOutputStream(System.getProperty("user.dir")+"\\test-data\\TestData_And_Validation.xlsx");
		
		workbook.write(fileOutputStream);
		
		fileOutputStream.close();
		
	}
	
	public static int getRowNumber(String sheetName) throws IOException {
		
		fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\test-data\\TestData_And_Validation.xlsx");
		
		workbook = new XSSFWorkbook(fileInputStream);
		
		sheet = workbook.getSheet(sheetName);
		
		int rowNumber = sheet.getPhysicalNumberOfRows();
		
		return rowNumber;
	}
	
	public static int getCellNumber(String sheetName) throws IOException {
		
		fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\test-data\\TestData_And_Validation.xlsx");
		
		workbook = new XSSFWorkbook(fileInputStream);
		
		sheet = workbook.getSheet(sheetName);
		
		row = sheet.getRow(1);
		
		int cellNumber = row.getLastCellNum();
		
		return cellNumber;
		
	}
	
	public static void fillGreenColor(String sheetName ,int rowNumber ,int cellNumber) throws IOException {
		
		fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\test-data\\TestData_And_Validation.xlsx");
		workbook = new XSSFWorkbook(fileInputStream);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNumber);
		cell = row.getCell(cellNumber);
		
		style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		cell.setCellValue("Test Passed");
		
		fileOutputStream = new FileOutputStream(System.getProperty("user.dir")+"\\test-data\\TestData_And_Validation.xlsx");
		workbook.write(fileOutputStream);
		
		workbook.close();
		fileInputStream.close();
		fileOutputStream.close();
	}
	
	public static void fillRedColor(String sheetName ,int rowNumber ,int cellNumber) throws IOException {
		
		fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\test-data\\TestData_And_Validation.xlsx");
		workbook = new XSSFWorkbook(fileInputStream);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNumber);
		cell = row.getCell(cellNumber);
		
		style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		cell.setCellValue("Test Failed");
		
		fileOutputStream = new FileOutputStream(System.getProperty("user.dir")+"\\test-data\\TestData_And_Validation.xlsx");
		workbook.write(fileOutputStream);
		
		workbook.close();
		fileInputStream.close();
		fileOutputStream.close();
		
	}
	

}
