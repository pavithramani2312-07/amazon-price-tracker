package util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtil {

    private static final Logger log =
            LoggerFactory.getLogger(ScreenshotUtil.class);

    public static void takeScreenshot(WebDriver driver,
                                      String fileName) {

        try {
            File screenshot =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE);

            Files.createDirectories(
                    Path.of("screenshots"));

            Path target = Path.of(
                    "screenshots",
                    fileName + ".png");

            Files.copy(
                    screenshot.toPath(),
                    target,
                    StandardCopyOption.REPLACE_EXISTING
            );
            log.info("Screenshot saved: screenshots/{}.png", fileName);

            log.info("Screenshot saved at: {}",
                    target.toAbsolutePath());

        } catch (Exception e) {
            log.error("Screenshot capture failed", e);
        }
    }
}