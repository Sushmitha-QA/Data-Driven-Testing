import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;

public class ExcelUtils {

    public static FileInputStream fi;
    public static FileOutputStream fo;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws;
    public static XSSFRow row;
    public static XSSFCell cell;
    public static XSSFCellStyle style;

    //find total number of rows from excel
    public static int getRowCount(String xlfile, String xlsheet) throws IOException {
        fi= new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);
        int rowCount = ws.getLastRowNum();
        wb.close();
        fi.close();
        return rowCount;
    }
    //find total number of columns from excel
    public static int getColumnsCount(String xlfile, String xlsheet, int rownum) throws IOException {
        fi= new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);
        int columnCount = ws.getRow(rownum).getLastCellNum();
        wb.close();
        fi.close();
        return columnCount;
    }
    //read data from excel
    public static String getCellData(String xlfile, String xlsheet, int rownum,int colnum) throws IOException {
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);
      cell = ws.getRow(rownum).getCell(colnum);
      String data;
      try{
          DataFormatter formatter = new DataFormatter();
        data = formatter.formatCellValue(cell);
      }
      catch (Exception e){
          data= "";
      }

        wb.close();
        fi.close();
        return data;

    }
    //write data in excel
    public static String setCellData(String xlfile, String xlsheet, int rownum,int colnum, String data) throws IOException {
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);
        cell = ws.getRow(rownum).createCell(colnum);
        cell.setCellValue(data);
        fo = new FileOutputStream(xlfile);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
        return data;

    }

    public static void fillGreenColor(String xlfile,String xlsheet, int rownum, int colnum) throws IOException {

        fi= new FileInputStream(xlfile);
        wb= new XSSFWorkbook(fi);
        ws= wb.getSheet(xlsheet);
        row= ws.getRow(rownum);
        cell = row.getCell(colnum);

        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);
        fo= new FileOutputStream(xlfile);
        wb.write(fo);
        wb.close();
        fo.close();
        fi.close();

    }
    public static void fillRedColor(String xlfile,String xlsheet, int rownum, int colnum) throws IOException {

        fi= new FileInputStream(xlfile);
        wb= new XSSFWorkbook(fi);
        ws= wb.getSheet(xlsheet);
        row= ws.getRow(rownum);
        cell = row.getCell(colnum);
        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);
        fo= new FileOutputStream(xlfile);
        wb.write(fo);
        wb.close();
        fo.close();
        fi.close();

    }

}
