package data;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import static org.apache.commons.lang3.CharSetUtils.count;

public class ExcelReader {
    public void readproduct(){
        try{
            FileInputStream fis = new FileInputStream("src/main/resources/product_list.xlsx");
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);
            int i = 1;

            for(i = 1; i<= sheet.getLastRowNum(); i++){
                Row row = sheet.getRow(i);

                String asin = row.getCell(0).getStringCellValue();
                String productname = row.getCell(1).getStringCellValue();
                double targetprice  =row.getCell(2).getNumericCellValue();

                System.out.println("Processing count " +i);
                System.out.println("Product name = " +productname);
                System.out.println("https://www.amazon.in/dp/"+asin);
                System.out.println("Target Price = " +targetprice);
                System.out.println("-------------------------------------------");
            }
            System.out.println("Total product = " + (i-1));
            workbook.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
