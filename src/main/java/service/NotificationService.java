package service;

public class NotificationService {
    public void sendNotification(String productName, double targetPrice, double currentPrice, String productUrl){
        System.out.println("EMAIL ALERT");
        System.out.println("Product : " + productName);
        System.out.println("Current Price : " + currentPrice);
        System.out.println("Target Price : " + targetPrice);
        System.out.println("URL : " + productUrl);
    }
    }
