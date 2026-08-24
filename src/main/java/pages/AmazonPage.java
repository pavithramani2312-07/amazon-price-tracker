package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class AmazonPage {
    WebDriver driver;

    public AmazonPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openproduct(String asin) {
        String url = "https://www.amazon.in/dp/" + asin;
        driver.get(url);

        System.out.println("Opened: " + url);
        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Page Title: " + driver.getTitle());



        try {
            WebElement continueBtn = driver.findElement(
                    By.xpath("//*[contains(text(),'Continue shopping')]")
            );

            System.out.println("Continue button detected");

            continueBtn.click();

            Thread.sleep(3000);

        } catch (Exception e) {
            System.out.println("No Continue button found");
        }


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getCurrentPrice() {//Explicit wait instead of thread
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            return wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.cssSelector("span.a-price-whole")))
                    .getText();
        } catch (Exception e) {
            System.out.println("PRICE NOT FOUND");
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Page Title: " + driver.getTitle());

            try {
                Files.writeString(
                        Path.of("amazon_debug.html"),
                        driver.getPageSource()
                );
                System.out.println("HTML SAVED");
            } catch (Exception fileException) {
                fileException.printStackTrace();
            }


            throw e;
        }
    }

}


