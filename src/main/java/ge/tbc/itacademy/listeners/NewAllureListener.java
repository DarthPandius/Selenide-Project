package ge.tbc.itacademy.listeners;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class NewAllureListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = WebDriverRunner.getWebDriver();
        saveScreenshot(driver);
    }

    @Attachment(value = "screenshot", type = "image/png", fileExtension = "png")
    public byte[] saveScreenshot(WebDriver driver) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        return screenshot.getScreenshotAs(OutputType.BYTES);
    }

}
