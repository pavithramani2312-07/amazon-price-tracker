package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Constants;
import util.ScreenshotUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class AmazonPage {
    private static final Logger log =
            LoggerFactory.getLogger(AmazonPage.class);
    private final WebDriver driver;

    public AmazonPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openProduct(String asin) {
        String url = Constants.AMAZON_URL + asin;
        driver.get(url);

        log.info("Opened: {}", url);
        log.info("Current URL: {}",  driver.getCurrentUrl());
        log.info("Page Title: {}",  driver.getTitle());



        try {
            WebElement continueBtn = driver.findElement(
                    By.xpath("//*[contains(text(),'Continue shopping')]")
            );

            log.info("Continue button detected");

            continueBtn.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.tagName("body")
                    )
            );

        } catch (Exception e) {
            log.info("No Continue button found");
        }


        try {
            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.tagName("body")
                    )
            );
        } catch (Exception e) {
            log.error("Page load failed", e);
        }
    }
    public boolean isProductAvailable(){
        String title = driver.getTitle().toLowerCase();
//        String pageSource = driver.getPageSource();

        log.info("Checking availability...");
        log.info("Title = {}", title);
        return !(title.contains("page not found")
                || title.contains("sorry")
                || title.contains("dogs of amazon"));
    }
    public String getCurrentPrice() {//Explicit wait instead of thread
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            return wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.cssSelector("span.a-price-whole")))
                    .getText();
        } catch (Exception e) {
            log.info("PRICE NOT FOUND");
            log.error("Current URL: {}", driver.getCurrentUrl());
            log.error("Page Title: {}", driver.getTitle());
            ScreenshotUtil.takeScreenshot(
                    driver,
                    "price_not_found_" +
                            System.currentTimeMillis());


            try {
                Files.writeString(
                        Path.of("amazon_debug.html"),
                        driver.getPageSource()
                );
                log.info("HTML SAVED");
            } catch (Exception fileException) {
                log.error("Failed to save debug HTML", fileException);
            }


            throw e;
        }
    }

}


