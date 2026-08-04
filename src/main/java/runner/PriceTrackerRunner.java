package runner;

import base.BaseClass;
import org.openqa.selenium.WebDriver;
import pages.AmazonPage;
import model.Product;
import data.ExcelReader;
import service.EmailService;
import service.NotificationService;
import service.ReportService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class PriceTrackerRunner {
    public static void main(String[] args) throws IOException {
        WebDriver driver = BaseClass.getDriver();
        AmazonPage amazonPage = new AmazonPage(driver);
        ExcelReader reader = new ExcelReader();
        List<Product> products = reader.readproduct();
        NotificationService notificationService = new NotificationService();
        ReportService reportService = new ReportService();
        reportService.createreport();

        for (Product product: products){
            amazonPage.openproduct(product.getAsin());
            String actualprice = amazonPage.getCurrentPrice();
            double currentprice = Double.parseDouble(actualprice.replace(",", "")); //convert the string to number
            System.out.println("Product name = " +product.getProductname());
            System.out.println("Target price = " +product.gettargetprice());
            System.out.println("Current price = " +currentprice);
            String status;
            if(currentprice<= product.gettargetprice()){
                status = "DROPPED";
                System.out.println("Price dropped");

                notificationService.sendNotification(product.getProductname(), product.gettargetprice(),
                        currentprice, "https://www.amazon.in/dp/" + product.getAsin());
            }
            else {
                status = "HIGHER";
                System.out.println("Price is higher than the target");
            }
            System.out.println("_____________________________");
            reportService.addrow(product.getProductname(), product.gettargetprice(), currentprice, status, "https://www.amazon.in/dp/"+product.getAsin());
                    }
        reportService.saveReport();
        EmailService emailService = new EmailService();
        emailService.sendEmail(
                "Amazon Price Tracker Report",
                "Total Products checked: " +products.size() + "\n" +
                        "Generated on: " + LocalDateTime.now() + "\n" +
                        "Please find the attached report and url.",
                "src/main/resources/priceReport.xlsx"
        );
        driver.quit();
}}
