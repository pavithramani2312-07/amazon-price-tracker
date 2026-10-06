package service;

public class NotificationService {

    private TelegramService telegramService =
            new TelegramService();

    public void sendNotification(String productName,
                                 double targetPrice,
                                 double currentPrice,
                                 String productUrl) {

        String message =
                "🚨 PRICE DROPPED\n\n" +
                        "Product: " + productName +
                        "\nTarget Price: ₹" + targetPrice +
                        "\nCurrent Price: ₹" + currentPrice +
                        "\n\n" + productUrl;

        telegramService.sendTelegramMessage(message);
    }
}