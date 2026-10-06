package listeners;

import base.BaseClass;
import org.testng.ITestListener;
import org.testng.ITestResult;
import util.ScreenshotUtil;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println("FAILED : " + result.getName());

        if (BaseClass.driver != null) {
            ScreenshotUtil.takeScreenshot(
                    BaseClass.driver,
                    result.getName()
            );
        }
    }
}