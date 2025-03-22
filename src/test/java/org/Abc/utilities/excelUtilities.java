package org.Abc.utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class excelUtilities {
    public  FileInputStream file;
    public  XSSFWorkbook workbook;
    public  XSSFSheet sheet;
    public  XSSFRow currentRow;
    public  XSSFCell cell;

    public int getRowCount(String XLfile,String xlSheet) throws IOException {
        int rowcount =0;
        file = new FileInputStream(XLfile);
        workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheet(xlSheet);
        rowcount = sheet.getLastRowNum();
        workbook.close();
        file.close();
        return rowcount;
    }

    public  int getCellCount(String XLfile,String xlSheet,int rowNum) throws IOException {
        int cellCount =0;
        file = new FileInputStream(XLfile);
        workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheet(xlSheet);
        currentRow = sheet.getRow(rowNum);
        cellCount = currentRow.getLastCellNum();
        return cellCount;
    }

    public  String getCellData(String XLfile,String xlSheet,int rowNum,int cellNum) throws IOException {
        String data=null;
        file = new FileInputStream(XLfile);
        workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheet(xlSheet);
        currentRow = sheet.getRow(rowNum);
        cell = currentRow.getCell(cellNum);
        try{
            //data = cell.toString();
            DataFormatter formatter =new DataFormatter();
            data = formatter.formatCellValue(cell); //converts data to string from any data type
        }catch (Exception e){
            data = "";
        }
        workbook.close();
        file.close();
        return data;
    }

    public  void SetCellData(String XLfile,String xlSheet,int rowNum,int cellNum,String data) throws IOException {
        file = new FileInputStream(XLfile);
        workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheet(xlSheet);
        currentRow = sheet.getRow(rowNum);
        cell =currentRow.createCell(cellNum);
        cell.setCellValue(data);
        FileOutputStream fileOutputStream = new FileOutputStream(XLfile);
        workbook.write(fileOutputStream);
        workbook.close();
        file.close();
        fileOutputStream.close();
    }
}
