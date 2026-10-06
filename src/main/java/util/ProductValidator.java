package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashSet;
import java.util.Set;


public class ProductValidator {
    private static final Logger log= LoggerFactory.getLogger(ProductValidator.class);
    private static final Set<String> processedRows = new HashSet<>();

    public static boolean isDuplicate(String asin,
                                      String productName,
                                      double targetPrice,
                                      int rowNumber) {

        String uniqueKey =
                asin + "|" +
                        productName + "|" +
                        targetPrice;

        if (processedRows.contains(uniqueKey)) {
            log.warn("Duplicate row found at row {}", rowNumber);
            return true;
        }

        processedRows.add(uniqueKey);
        return false;
    }
}
