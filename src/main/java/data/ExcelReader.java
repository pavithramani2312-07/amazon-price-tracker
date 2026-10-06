package data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import util.ProductValidator;

import model.Product;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.DataFormatter;

public class ExcelReader {
    private static final Logger logger = LoggerFactory.getLogger(ExcelReader.class);

    public List<Product> readproduct(String filePath){
        List<Product> products = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            int i = 1;

            DataFormatter formatter = new DataFormatter();
            for(i = 1; i<= sheet.getLastRowNum(); i++){
                Row row = sheet.getRow(i);

                if (row == null ||
                        (row.getCell(0) == null &&
                                row.getCell(1) == null &&
                                row.getCell(2) == null)) {
                    continue;
                }

                String asin = formatter.formatCellValue(row.getCell(0)).trim();
                if (asin.isEmpty()){
                    logger.warn("Invalid Test Data - Empty ASIN at row"+ (i + 1));
                    continue;
                }
                String productname = formatter.formatCellValue(row.getCell(1)).trim();
                if (productname.isEmpty()) {
                    logger.warn("Invalid Test Data - Empty Product Name at row " + (i + 1));
                    continue;
                }
                String priceText = formatter.formatCellValue(row.getCell(2)).trim();

                double targetprice;

                try {
                    targetprice = Double.parseDouble(priceText);
                } catch (NumberFormatException e) {
                    logger.warn("Invalid Test Data - Price is not numeric at row " + (i + 1));
                    continue;
                }
                if (targetprice < 0) {
                    logger.warn("Invalid Test Data - Negative Target Price at row " + (i + 1));
                    continue;
                }
                if (targetprice == 0) {
                    logger.warn("Invalid Test Data - Target Price cannot be 0 " + (i + 1));
                    continue;
                }
                if(ProductValidator.isDuplicate(asin,
                        productname,
                        targetprice,
                        i+1)){
                    continue;
                }

                Product product= new Product(asin, productname, targetprice);
                products.add(product);
            }
            logger.info("Read Excel sheet");
            logger.info("Total product = " + products.size());
            logger.info("___________");
            return products;
        } catch (Exception e) {
            logger.error("Failed to read excel file", e);
        }
        return products;
    }
}
