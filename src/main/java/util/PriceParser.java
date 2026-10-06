package util;

public class PriceParser {
        public static double parsePrice(String price){
            return Double.parseDouble(
                    price.replace("₹", "")
                            .replace(",", "")
                            .trim()
            );
        }
}
