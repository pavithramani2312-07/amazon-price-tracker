package runner;

import base.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import pages.AmazonPage;
import model.Product;
import data.ExcelReader;
import service.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.*;

public class PriceTrackerRunner {
    private static final Logger log =
            LoggerFactory.getLogger(PriceTrackerRunner.class);
    private static ExtentTest test;

    public static void main(String[] args) throws IOException {
        WebDriver driver = BaseClass.getDriver();
        try{AmazonPage amazonPage = new AmazonPage(driver);
        String filePath;

        if (args.length > 0) {
            filePath = args[0];
        } else {
            filePath =  Constants.EXCEL_FILE;
        }

        log.info("Reading file: {}", filePath);

        ExcelReader reader = new ExcelReader();
        List<Product> products = reader.readproduct(filePath);
        NotificationService notificationService = new NotificationService();

        ReportService reportService = new ReportService();
        reportService.createreport();
            ExtentReports extent =
                    ExtentManager.getInstance();
        DashboardService dashboardService=new DashboardService();
        dashboardService.createDashboard();



        int dropped = 0;
        int higher = 0;
        int unavailable = 0;

        for (Product product: products){
            ExtentTest test =
                    extent.createTest(
                            product.getProductname());
                amazonPage.openProduct(product.getAsin());
                if (!amazonPage.isProductAvailable()){
                    ScreenshotUtil.takeScreenshot(
                            driver,
                            "unavailable_" +
                                    product.getAsin());
                    log.warn("Product unavailable");
                    test.fail(
                            "Product unavailable\nASIN = " +
                                    product.getAsin()
                    );
                    log.warn("Product Name : {}", product.getProductname());
                    log.warn("ASIN : {}", product.getAsin());
                    reportService.addrow(
                            product.getProductname(),
                            product.gettargetprice(),
                            0,
                            "UNAVAILABLE",
                            Constants.AMAZON_URL + product.getAsin());


                    dashboardService.addRow(
                            product.getProductname(),
                            product.gettargetprice(),
                            0,
                            "UNAVAILABLE",
                            Constants.AMAZON_URL + product.getAsin());

                    continue;

                }
                double currentprice;

            try {
                String actualprice =
                        RetryUtil.execute(
                                () -> amazonPage.getCurrentPrice(),
                                3
                        );
                currentprice = PriceParser.parsePrice(actualprice);

            } catch (Exception e) {
                test.fail(
                        "Unable to fetch price");
                unavailable++;

                log.warn("Unable to fetch price");
                log.warn("Product Name : {}", product.getProductname());
                log.warn("ASIN : {}", product.getAsin());

                continue;
            }
            log.info("Product name = {}", product.getProductname());
            log.info("Target price = {}", product.gettargetprice());
            log.info("Current price = {}", currentprice);
            String status;
            if(PriceComparator.isPriceDropped(currentprice,
                    product.gettargetprice())){
                dropped++;
                status = "DROPPED";
                test.pass(
                        "Price Dropped\n" +
                                "Current Price = " + currentprice);
                log.info("Price dropped for {}. Current={} Target={}",
                        product.getProductname(),
                        currentprice,
                        product.gettargetprice());
                test.pass(
                        "Price Dropped\n" +
                                "Current Price = " + currentprice +
                                "\nTarget Price = " +
                                product.gettargetprice());
                test.pass("Price Dropped");
                notificationService.sendNotification(product.getProductname(), product.gettargetprice(),
                        currentprice, Constants.AMAZON_URL + product.getAsin());
            }
            else {
                status = "HIGHER";
                test.info(
                        "Current Price = " + currentprice +
                                "\nTarget Price = " +
                                product.gettargetprice());
                test.info("Price Higher Than Target");
                log.info("Price is higher than the target");
                higher++;
            }
            log.info("_____________________________");
            reportService.addrow(product.getProductname(), product.gettargetprice(), currentprice, status, Constants.AMAZON_URL +product.getAsin());
            dashboardService.addRow(product.getProductname(), product.gettargetprice(), currentprice, status, Constants.AMAZON_URL +product.getAsin());
        }
        reportService.saveReport();
        dashboardService.saveDashboard();
            extent.flush();
        EmailService emailService = new EmailService();
            emailService.sendEmail(
                    "Amazon Price Tracker Report",

                    "Total Products Checked : " + products.size() + "\n" +
                            "Price Dropped : " + dropped + "\n" +
                            "Price Higher : " + higher + "\n" +
                            "Unavailable Products : " + unavailable + "\n" +
                            "Generated On : " + LocalDateTime.now(),

                    Constants.REPORT_FILE,
                    Constants.DASHBOARD_FILE,
                    "ExtentReport.html"
            );
        }
        finally{
        driver.quit();}
}}
