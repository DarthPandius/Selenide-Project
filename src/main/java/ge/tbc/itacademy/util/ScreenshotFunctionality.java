package ge.tbc.itacademy.util;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.selenide.AllureSelenide;

public class ScreenshotFunctionality extends AllureSelenide {
    private boolean saveScreenshots;
    @Override
    public ScreenshotFunctionality screenshots(boolean saveScreenshots) {
        if (WebDriverRunner.hasWebDriverStarted()){
            this.saveScreenshots = saveScreenshots;
        }

        return this;
    }
}