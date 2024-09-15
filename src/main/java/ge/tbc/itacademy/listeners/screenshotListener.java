package ge.tbc.itacademy.listeners;

import org.openqa.selenium.OutputType;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;


import static com.codeborne.selenide.Selenide.screenshot;
import static io.qameta.allure.Allure.addAttachment;

public class screenshotListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        addAttachment("test","test");
        try (InputStream is = Files.newInputStream(Path.of(Objects.requireNonNull(screenshot(OutputType.FILE)).getAbsolutePath()))) {
            addAttachment("screenshot.png", is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

