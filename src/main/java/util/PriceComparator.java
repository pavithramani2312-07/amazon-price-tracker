package util;

public class PriceComparator {
    public static boolean isPriceDropped(double currentPrice,
                                         double targetPrice) {

        return currentPrice <= targetPrice;
    }
}
