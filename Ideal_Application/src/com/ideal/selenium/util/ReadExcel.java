package com.ideal.selenium.util;


import java.io.File;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Vector;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	
	private int testMethodCount;
	private int parmNotNullCount;
	private String pathExcel;
	private Workbook wb=null;
	private Vector<String> testMethod = new Vector<String>();
	
	private String testMethodName;
	
	
	public ReadExcel(String pathExcel) {
		
		this.parmNotNullCount = 0;
		this.testMethodCount = 0;
		this.pathExcel = pathExcel;
		try {
			this.wb = WorkbookFactory.create(new File(pathExcel));
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
			
		
	}
	
	
	public void getTestData() throws InvalidFormatException, IOException{
		
		//File inputTestData = new File(pathExcel);
		//pathExcel = System.getProperty("user.dir")+"\\src\\test\\TestData.xlsx";
		//wb = WorkbookFactory.create(new File(pathExcel));
		
		Sheet sheet = wb.getSheet("Data");
		
		System.out.println("Test Data Path: "+pathExcel);
		
		System.out.println("Referring to sheet: "+sheet.getSheetName());
		
		testMethodCount = sheet.getPhysicalNumberOfRows();
		System.out.println("Number of test methods in sheet: "+testMethodCount);
		//Sheet sheet1 = wb.getSheetAt(0);
		
		for(Row row:sheet){
			
			//Row row = sheet.getRow(rowInd);
			
			int rowInd = row.getRowNum();
			parmNotNullCount =  sheet.getRow(rowInd).getPhysicalNumberOfCells();
			System.out.println("Num of Parameters: "+parmNotNullCount);
		
			for (Cell cell : row) {
	            //CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
	            //System.out.print(cellRef.formatAsString());
	            //System.out.print(" - ");
	            //System.out.println(cell.getStringCellValue());
									
				if(cell.getColumnIndex()==0){
					testMethodName = cell.getStringCellValue();
					testMethod.add(testMethodName);
					System.out.println("test Method: "+ testMethodName);
					
				}
				else{
					 if (cell == null) {
						 
	                    //The spreadsheet is empty in this cell
	                	//System.out.println("empty in this cell");
	                }else{
	                	
	                	//paramData.add(cellValue);
						//System.out.print(cell.getStringCellValue()+"#");
						switch (cell.getCellType()) {
		                case Cell.CELL_TYPE_STRING:
		                    System.out.print(cell.getRichStringCellValue().getString()+"#");
		                    break;
		                case Cell.CELL_TYPE_NUMERIC:
		                    if (DateUtil.isCellDateFormatted(cell)) {
		                        System.out.print(cell.getDateCellValue()+"#");
		                    } else {
		                        System.out.print(cell.getNumericCellValue()+"#");
		                    }
		                    break;
		                case Cell.CELL_TYPE_BOOLEAN:
		                    System.out.print(cell.getBooleanCellValue()+"#");
		                    break;
		                case Cell.CELL_TYPE_FORMULA:
		                    System.out.print(cell.getCellFormula()+"#");
		                    break;
		                default:
		                    System.out.println();
		            }
						
					
	                }
						
				}
				
	       
				
			}
			System.out.println();
		}
						
		
	}
	
	public Vector <String> getTestMethodName(){
		return testMethod;
	}

	
	
	public int getColCount(String sheetName){
		if(!isSheetExists(sheetName))
			return -1;
		Sheet sheet = wb.getSheet(sheetName);
		int colCount = sheet.getRow(0).getLastCellNum();
		//System.out.println("Last Cell number in first row: "+colCount);
		return colCount;
		
	}
	public int getRowCount(String sheetName){
		
		if(!isSheetExists(sheetName))
			return -1;
		Sheet sheet = wb.getSheet(sheetName);
		//int rowCount = sheet.getLastRowNum();
		//System.out.println("Last row with data(excluding top row): "+rowCount);
		int rowCount = sheet.getLastRowNum()+1;
		return rowCount;		
		
	}
	
	public Vector<String> getRowData(String sheetName,int rowNum) {
		
		isSheetExists(sheetName);
		String cellText="";
		Sheet sheet = wb.getSheet(sheetName);
		Row row=sheet.getRow(rowNum-1);
		
		int colCount = row.getLastCellNum();
		int firstCellNum = row.getFirstCellNum();
		System.out.println("First Cell Num: "+firstCellNum);
		System.out.println("Col Count: "+colCount);
				
		Vector<String> rowData = new Vector<String>();
		for  (int i=0; i<colCount;i++){
			//cellText = sheet.getRow(rowNum).getCell(i).getStringCellValue();
			//cellText = row.getCell(i).getStringCellValue();
			cellText = getStringCellData(sheetName, rowNum, i);
			//System.out.println(cellText);
			
			rowData.add(cellText);
		}
		return rowData;
	}
	
	public Vector<String> getRowDataByRowName(String sheetName,String rowName) {
		
		isSheetExists(sheetName);
		String cellText="";
		Sheet sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getPhysicalNumberOfRows();
		System.out.println("Physical rows: "+rowCount);
		int lastRowWithData = sheet.getLastRowNum(); 
		System.out.println("Last row Num: "+lastRowWithData);
		
		int rowInd = 0;
		for(int j=0;j<rowCount;j++){
			String rowValFirstCol = getStringCellData(sheetName, j, 0);
			if (rowValFirstCol.contains(rowName)){
				rowInd = j;
				break;
			}
		}
		System.out.println("Row Index from getRowDataByRowName "+rowInd);
		Row row=sheet.getRow(rowInd-1);
		int colCount = row.getLastCellNum();
		if (colCount ==1){
			System.out.println("No Test data exists in row: "+rowName+",populate it with relevant data.");
		}
		
		Vector<String> rowData = new Vector<String>();
		for  (int i=1; i<colCount;i++){
			//cellText = sheet.getRow(rowNum).getCell(i).getStringCellValue();
			//cellText = row.getCell(i).getStringCellValue();
			cellText = getStringCellData(sheetName, rowInd, i);
			//System.out.println(cellText);
			rowData.add(cellText);
		}
		return rowData;
	}
	
	
	public boolean setCellData(String sheetName,int rowNum,String colName,String fillData){
		
		try {
			//System.out.println("In setCellData function");
			InputStream inp = new FileInputStream(pathExcel);
			OPCPackage pkg = OPCPackage.open(inp);
			XSSFWorkbook wb = new XSSFWorkbook(pkg);
			
			//Workbook workbook = WorkbookFactory.create(inp);
						
			Sheet sheet=null;
			if(isSheetExists(sheetName)){
				sheet = wb.getSheet(sheetName);
				int index = wb.getSheetIndex(sheetName);
				System.out.println("referring to sheet: "+wb.getSheetName(index));
			}else{
				System.out.println("Verify if sheet is listed: "+sheetName);
				return false;
			}

			Row row = sheet.getRow(0);
			int colInd = 0;
			for (int i = 0;i<getColCount(sheetName);i++){
				//if(row.getCell(i).getStringCellValue().compareToIgnoreCase(colName)==0){
				//--System.out.println(row.getCell(i).getStringCellValue().trim());
				if(row.getCell(i).getStringCellValue().trim().contains(colName)){
					colInd = row.getCell(i).getColumnIndex();
					//System.out.println("Column number: "+colInd);
					break;
				}
			}

			//--System.out.println("Column Index of column name: "+colName+"is:"+colInd);
			row = sheet.getRow(rowNum-1);

			CreationHelper createHelper = wb.getCreationHelper();
			if(row==null){
				sheet.createRow(rowNum-1);
			}
			//Cell cell = row.getCell(colInd);
			Cell cell = row.getCell(colInd, Row.RETURN_BLANK_AS_NULL);
			//System.out.println("Cell Type: "+cell.getCellType());
			//System.out.println(cell.getStringCellValue());
			if (cell ==null){
				//--System.out.println("this is empty cell");
				//row.createCell(colInd).setCellValue("###");
				row.createCell(colInd).setCellValue(createHelper.createRichTextString(fillData));
			}else{
				cell.setCellValue(createHelper.createRichTextString(fillData));
			}
			FileOutputStream fileOut = new FileOutputStream(pathExcel);
			wb.write(fileOut);
		
			fileOut.flush();
			fileOut.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} 


		return true;
	}
	

		
	public int getTestMethodCount(){
		return testMethodCount;
	}
	

	
	public String getStringCellData (String sheetName,int rowNum,int colNum){
		
		System.out.println("In getStringCellData by Column Name");
		Sheet sheet=null;
		String cellText="";
		
		if (isSheetExists(sheetName))
			sheet = wb.getSheet(sheetName);
		    //System.out.println(sheet.getSheetName());
		else{
			System.out.println("Verify the sheet name: "+sheetName);
			return "";
		}
		
		if(rowNum<=0){
			System.out.println("Row num cannot be <= zero");
			return "";			
		}
			
		Row row = sheet.getRow(rowNum-1);
				
		if(row==null){
			return "";
		}
		//RETURN_NULL_AND_BLANK: Missing cells are returned as null, Blank cells are returned as normal		
		//Cell cell = row.getCell(colNum, Row.RETURN_NULL_AND_BLANK);
		Cell cell = row.getCell(colNum, Row.RETURN_BLANK_AS_NULL);
        if (cell == null) {
            // The spreadsheet is empty in this cell
        	//--System.out.print("empty in this cell"+"#");
        	//cellText="EmptyCell";
        	
        	return cellText;
        	
        } 
			
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			cellText = cell.getRichStringCellValue().getString();
			break;
		case Cell.CELL_TYPE_FORMULA:
			
			cellText = String.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_NUMERIC:
			
			if (DateUtil.isCellDateFormatted(cell)) {
				double dt = cell.getNumericCellValue();
				Calendar cal = Calendar.getInstance();
				cal.setTime(DateUtil.getJavaDate(dt));
				cellText = String.valueOf(cal.get(Calendar.YEAR));
				cellText = cal.get(Calendar.MONTH) + 1 + "/"
						+ cal.get(Calendar.DAY_OF_MONTH) + "/" + cellText;
			
			} 
			cellText = String.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			
			cellText = String.valueOf(cell.getBooleanCellValue());
			break;
		default: //Cell.CELL_TYPE_ERROR
			return "";
		}
			
		return cellText;
		
	}
	
	public String getStringCellData (String sheetName,int rowNum,String colName){
				
		Sheet sheet=null;
		String cellText="";
		
		if (isSheetExists(sheetName))
			sheet = wb.getSheet(sheetName);
		    //System.out.println(sheet.getSheetName());
		else{
			System.out.println("Verify the sheet name: "+sheetName);
			return "";
		}
		
		if(rowNum<=0){
			System.out.println("Row num cannot be <= zero");
			return "";			                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
		}
		
		Row row = sheet.getRow(0);
		int colInd = 0;
		for (int i = 0;i<row.getLastCellNum();i++){
			if(row.getCell(i).getStringCellValue().compareToIgnoreCase(colName)==0){
				//if(row.getCell(i).getStringCellValue().trim().equals(colName.trim())){
				colInd = row.getCell(i).getColumnIndex();
				//System.out.println("Column number: "+colInd);
				break;
			}
		}

		//row = sheet.getRow(rowNum);
		row = sheet.getRow(rowNum-1);

		if(row == null){
			return "";
		}
		//RETURN_BLANK_AS_NULL: Missing cells and blank cells are returned as null 			
		//Cell c = row.getCell(colInd, Row.RETURN_BLANK_AS_NULL);
		//RETURN_NULL_AND_BLANK: Missing cells are returned as null, Blank cells are returned as normal
		Cell c = row.getCell(colInd, Row.RETURN_BLANK_AS_NULL);
		if (c == null) {
			// The spreadsheet is empty in this cell
			//System.out.print("empty in this cell"+"#");
			
			//cellText="EmptyCell";
			cellText="";
			return cellText;
			
		}

		else if (c.getCellType()==Cell.CELL_TYPE_STRING)
			return c.getStringCellValue();
		else if (c.getCellType()== Cell.CELL_TYPE_FORMULA )
			return String.valueOf(c.getNumericCellValue());
		else if(c.getCellType()==Cell.CELL_TYPE_NUMERIC){
			if (DateUtil.isCellDateFormatted(c)) {
				double dt = c.getNumericCellValue();
				Calendar cal =Calendar.getInstance();
				cal.setTime(DateUtil.getJavaDate(dt));
				cellText = String.valueOf(cal.get(Calendar.YEAR));
				cellText = cal.get(Calendar.MONTH) + 1 + "/"
				+ cal.get(Calendar.DAY_OF_MONTH) + "/" + cellText;

				//System.out.println("String rep of Dat:" + cellText);
			}else{
				cellText = String.valueOf(c.getNumericCellValue());
			}
			return cellText;
		}else if (c.getCellType()== Cell.CELL_TYPE_BOOLEAN){
			return String.valueOf(c.getBooleanCellValue()); 
		}else
			//Cell.CELL_TYPE_ERROR
			return "";
		
			
			


	}

		
	
	
	public void getTestCaseID(){
		
		//Workbook wb = WorkbookFactory.create(new File(pathExcel));
		Sheet sheet = wb.getSheetAt(1);
		System.out.println(sheet.getSheetName());
		int num_rows = sheet.getPhysicalNumberOfRows();
		System.out.println("Number of rows in sheet: "+num_rows);
	    int num_cols =  sheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println("Number of columns in a row: "+num_cols);
		System.out.println("######");
		
		for(Row row:sheet){
			//System.out.println(row.getCell(0));
			for (Cell cell:row){
				if (cell.getStringCellValue().contains("Yes")){
					int row_num = row.getRowNum();
					System.out.println("Row Number is: "+row_num);
					System.out.println(sheet.getRow(row_num).getCell(0).getStringCellValue());
					
					}
				
				}

		}
		
		
	}
	
	public void executeTestMethods(int rowStart,int rowEnd) throws InvalidFormatException, IOException{
		
		
		Sheet sheet = wb.getSheet("Data");
		
		System.out.println(pathExcel);
		System.out.println("Referring to sheet: "+sheet.getSheetName());
		
		int countTestMethods = sheet.getPhysicalNumberOfRows();
		System.out.println("Number of test methods in sheet: "+countTestMethods);
	    
		//int num_cols =  sheet.getRow(0).getPhysicalNumberOfCells();
		//System.out.println("Number of columns in a row: "+num_cols);
		
		int last_rownum = sheet.getLastRowNum();
		System.out.println("Last row Number: "+last_rownum);
	
		String cellValue ="";
		for (int rowNum = rowStart; rowNum<rowEnd; rowNum++ ){
         	Row r = sheet.getRow(rowNum);
            //CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
            int rowLastParam = r.getLastCellNum();
           
         	for (int cn = 0; cn < rowLastParam; cn++) {
              
                //Cell c = r.getCell(cn, Row.RETURN_BLANK_AS_NULL);
         		Cell c = r.getCell(cn, Row.RETURN_BLANK_AS_NULL);
                if (c == null) {
                   // The spreadsheet is empty in this cell
                	//System.out.print("empty in this cell"+"#");
                	cellValue=" ";
                	System.out.print(cellValue+"#");
                } else {
                	
             	  // Do something useful with the cell's contents
                cellValue = c.getStringCellValue();
                System.out.print(cellValue+"#");
                }
                
             }
         	System.out.println();
         }
	     
	   
		
	}


	public Boolean isSheetExists(String sheetName){
		int index = wb.getSheetIndex(sheetName);
		if (index == -1){
			return false;
		}else{
			return true;
		
		}
	}
	
	
	/**
	 * @param args
	 * @throws InvalidFormatException
	 * @throws IOException
	 */

	/*	
	public static void main(String[] args) throws InvalidFormatException, IOException {
		
		//String pathExcel = "C:\\Users\\15625\\Documents\\GitHub\\QA-TestNG\\TestFunctions\\src\\testfunctions\\TestData.xlsx";
		
		//String pathExcel = System.getProperty("user.dir")+"\\src\\testfunctions\\TestData.xlsx";
		
		String pathExcel = System.getProperty("user.dir")+"\\src\\com\\rpx\\cp\\selenium\\util\\TestCase.xlsx";
		
		System.out.println(pathExcel);
		
		
		ReadExcel newExcel = new ReadExcel(pathExcel);
		System.out.println("Reading from xls at: "+pathExcel);
		System.out.println("Row Count from Data: "+newExcel.getRowCount("SearchData"));	
		System.out.println("Column count from Data: "+newExcel.getColCount("SearchData"));
		
		System.out.println("4TH ROW:"+newExcel.getStringCellData("SearchData", 4, "patnum"));
		System.out.println("7TH ROW:"+newExcel.getStringCellData("SearchData", 7, "patnum"));
		System.out.println("6TH ROW:"+newExcel.getStringCellData("SearchData", 6, "patnum"));
		System.out.println("3rd Row diff col:"+newExcel.getStringCellData("SearchData", 3, "SearchResults"));

		//--newExcel.setCellData("SearchData", 2,"SearchResults", "fiDataUUUUUUUYYYY");
		//newExcel.setCellData("Data", "URL", 9,  "dathere");
		//--newExcel.setCellData("Data", 9,"URL", "fiDataUUUUUUUYYYY");
		//newExcel.setCellData("cases", 9, "URL", "filledData");
		//newExcel.setCellData("Data", 11, "URL", "datafilled");
		
		System.out.println("-------get Row data------");
		//Vector<String> rowData = newExcel.getRowData("Data", 1);
		Vector<String> rowData = newExcel.getRowData("Data", 6);
		for (int i=0;i<rowData.size();i++){
			System.out.println(rowData.get(i));
		} 
		System.out.println("-----------");
		Vector<String> rowDataByRowName = newExcel.getRowDataByRowName("Data", "Method9");
		//Vector<String> rowDataByRowName = newExcel.getRowDataByRowName("Data", "Method15");
		System.out.println("-------get Rowdata by Row Name------");
		for (int i=0;i<rowDataByRowName.size();i++){
			System.out.println(rowDataByRowName.get(i));
		} 
		System.out.println("-------------------------------------------");
		
		System.out.println("Row Count from Data: "+newExcel.getRowCount("Data"));	
		System.out.println("Column count from Data: "+newExcel.getColCount("Data"));
		System.out.println("Row Count from Cases: "+newExcel.getRowCount("Cases"));
		System.out.println("Column count from Cases: "+newExcel.getColCount("Cases"));
		
		System.out.println("-------getTestData--------------");
		//newExcel.getTestData();
		
		//int testMethodCount = newExcel.getTestMethodName().size();
		//System.out.println("Number of Test Methods: "+testMethodCount);
				
		//for (int i=0;i<testMethodCount;i++){
		//	System.out.println(newExcel.getTestMethodName().get(i));
		//}
		
		
		//System.out.println("--------executeTestMethods-----------");
		//String pathExcel2 = "C:\\Users\\15625\\Documents\\GitHub\\QA-TestNG\\TestFunctions\\src\\testfunctions\\TestData2.xlsx";
		//ReadExcel testData = new ReadExcel(pathExcel2);
		//testData.executeTestMethods(0, 4);
		//System.out.println("--------executeTestMethods-----------");
		
		//newExcel.getTestCaseID();
		
		System.out.println("--------getCellData-----------");
	
		System.out.println("----getCellData by Column Index");
		String datee = newExcel.getStringCellData("Data",2,4);
		System.out.println("Date: "+datee);
		String bool4 = newExcel.getStringCellData("Data",2,5);
		System.out.println("boolean: "+bool4);
		String formu = newExcel.getStringCellData("Data",2,7);
		System.out.println("Formula: "+formu);
		String emptycell = newExcel.getStringCellData("Data",1,11);
		System.out.println("Empty: "+emptycell);
	
		System.out.println("----getCellData by Column Name");
		
		String dt = newExcel.getStringCellData("data",1, "param4");
		System.out.println("Date: "+dt);
		
		String bool1 = newExcel.getStringCellData("data",1, "param5");
		System.out.println("boolean: "+bool1);
		String bool2 = newExcel.getStringCellData("data",1, "param6");
		System.out.println("boolean: "+bool2);
		
		String formul = newExcel.getStringCellData("data",1, "param7");
		System.out.println("Formula: "+formul);
		
		String numeric1 = newExcel.getStringCellData("data",1, "param7");
		System.out.println("Formula: "+numeric1);
		
		//newExcel.getStringCellData("data",1, "param8");
		System.out.println("---------");
		String emptyCell = newExcel.getStringCellData("data",1, "param9");
		System.out.println(emptyCell);
		System.out.println("---------");
		
		String cellData = newExcel.getStringCellData("DATA",4, "UserName");
		System.out.println("UserName: "+cellData);
		
		String passcode = newExcel.getStringCellData("DATA",4, "passcode");
		System.out.println("Passcode: "+passcode);
		
		String url1 = newExcel.getStringCellData("DATA",4, "URL");
		System.out.println("URL: "+url1);
		
		String dt2 = newExcel.getStringCellData("DATA",2, "param4");
		System.out.println("Date:IST: "+dt2);
		
		
		newExcel.getStringCellData("DATAAAA",4, "passcode");
		System.out.println("--------getCellData-----------");
		//newExcel.getStringTestDataForMethod("Method1");
		System.out.println("-------getTestData--------------");
		

	}
*/	
	
}
