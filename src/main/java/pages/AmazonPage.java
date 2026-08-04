package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AmazonPage {
    WebDriver driver;
    public AmazonPage(WebDriver driver){
        this.driver=driver;
    }

    public void openproduct(String asin){
        String url = "https://www.amazon.in/dp/" +asin;
        driver.get(url);

        System.out.println("Opened: " +url);
    }
    public String getCurrentPrice() {        //Explicit wait instead of thread
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String actualprice = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("span.a-price-whole")))
                .getText();

        return actualprice;
        }
    }


