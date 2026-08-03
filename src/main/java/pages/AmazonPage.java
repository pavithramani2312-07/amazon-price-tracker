package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
    public String getCurrentPrice() {

        String price = driver.findElement(
                        By.cssSelector("span.a-price-whole"))
                .getText();

        return price;
    }
}
