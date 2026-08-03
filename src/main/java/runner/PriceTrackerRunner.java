package runner;

import data.ExcelReader;
import base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AmazonPage;

public class PriceTrackerRunner {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = BaseClass.getDriver();
        AmazonPage amazonPage = new AmazonPage(driver);
        amazonPage.openproduct("B0DZ2RMZQF");
        Thread.sleep(5000);
        String price = amazonPage.getCurrentPrice();
        System.out.println("Current Price = " +price);
//        ExcelReader reader = new ExcelReader();
//        reader.readproduct();
    }
}
