package service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import data.ExcelReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReportService {
    private Workbook workbook;
    private Sheet sheet;

    private int rowNumber = 1;
    public void createreport() {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Price Report");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Product Name");
        header.createCell(1).setCellValue("Target Price");
        header.createCell(2).setCellValue("Current Price");
        header.createCell(3).setCellValue("Status");
        header.createCell(4).setCellValue("Product link");
    }
    public void addrow(String productName, double targetPrice, double currentPrice, String status, String productUrl){
        Row row = sheet.createRow(rowNumber);

        row.createCell(0).setCellValue(productName);
        row.createCell(1).setCellValue(targetPrice);
        row.createCell(2).setCellValue(currentPrice);
        row.createCell(3).setCellValue(status);
        row.createCell(4).setCellValue(productUrl);

        rowNumber++;
    }
    public void saveReport() throws IOException {
        for (int i = 0; i < 5; i++) {
            sheet.autoSizeColumn(i);
        }
        FileOutputStream fos = new FileOutputStream("src/main/resources/priceReport.xlsx");
        workbook.write(fos);
        fos.close();
        workbook.close();
    }
}
