package data;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {
    public List<Product> readproduct(){
        List<Product> products = new ArrayList<>();
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

                Product product= new Product(asin, productname, targetprice);
                products.add(product);
            }
            System.out.println("Read Excel sheet");
            System.out.println("Total product = " + (i-1));
            System.out.println("___________");
            workbook.close();
            fis.close();
            return products;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}
