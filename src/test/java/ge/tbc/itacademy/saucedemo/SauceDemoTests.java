package ge.tbc.itacademy.saucedemo;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import ge.tbc.itacademy.data.constants.SauceDemoConstants;
import ge.tbc.itacademy.data.database.DBSteps;
import ge.tbc.itacademy.listeners.screenshotListener;
import ge.tbc.itacademy.pages.saucedemo.SwagLabsLoginPage;
import ge.tbc.itacademy.pages.saucedemo.SwagLabsProductsPage;
import ge.tbc.itacademy.steps.saucedemo.SwagLabsLogInSteps;
import ge.tbc.itacademy.steps.saucedemo.SwagLabsProductsSteps;
import ge.tbc.itacademy.util.ScreenshotFunctionality;
import ge.tbc.itacademy.util.Util;
import io.qameta.allure.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners({screenshotListener.class})
@Epic("Sauce Demo Regression")
public class SauceDemoTests {
    SwagLabsLogInSteps labsLogInSteps;
    SwagLabsLoginPage labsLoginPage;
    SwagLabsProductsPage labsProductsPage;
    SwagLabsProductsSteps labsProductsSteps;
    SoftAssert softAssert;
    DBSteps dbSteps;


    @BeforeClass(groups = {"Sauce Demo"})
    public void setup() {
        SelenideLogger.addListener("AllureSelenide", new ScreenshotFunctionality());


        Configuration.screenshots = true;
        Configuration.browserPosition = "0x0";
//        Configuration.reportsFolder = "SauceDemoScreens";
        Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, Util.myOptions());


        this.dbSteps = new DBSteps();
        this.labsLogInSteps = new SwagLabsLogInSteps();
        this.labsLoginPage = new SwagLabsLoginPage();
        this.labsProductsPage = new SwagLabsProductsPage();
        this.labsProductsSteps = new SwagLabsProductsSteps();
    }

    @BeforeMethod(groups = {"Sauce Demo"})
    public void browserSetup() {
        this.softAssert = new SoftAssert();
        open(SauceDemoConstants.SWAG_LAB_URL);
        getWebDriver().manage().window().maximize();
    }

    @Feature("Sauce Demo Log In")
    @Description("Standard user is working and displayed products page has correct images")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Standard user login should redirect to proper user page")
    @Test(groups = {"Sauce Demo login","Sauce Demo Products"})
    public void successfulLoginTest() {
        Map<String, String> credentials = dbSteps.getStandardUserCredentials();

        labsLogInSteps
                .enterUserName(credentials.get("userName"))
                .enterPassword(credentials.get("password"))
                .pressLoginButton();

        for (SelenideElement image : labsProductsPage.productImages) {
            softAssert.assertFalse(image.getAttribute("src").equals(SauceDemoConstants.PLACEHOLDER_DOG_IMG));
        }

        softAssert.assertAll();
    }

    @Feature("Sauce Demo Log In")
    @Description("Banned user test should leave the user on login page and display all messages and Xs correctly")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Blocked user is not redirected to the product page and encounters errors")
    @Test(groups = {"Sauce Demo login"})
    public void bannedUserLoginTest() {
        Map<String, String> credentials = dbSteps.getLockedOutUserCredentials();
        labsLogInSteps
                .enterUserName(credentials.get("userName"))
                .enterPassword(credentials.get("password"))
                .pressLoginButton()
                .redXButtonsAreVisible()
                .errorMessageIsDisplayed();
        softAssert.assertEquals(labsLogInSteps.getErrorMessage(), SauceDemoConstants.LOCKED_OUT_ERR_TXT);

        softAssert.assertAll();
    }

    @Feature("Sauce Demo Log Out")
    @Description("After successful log in, log out should return user to the login page with empty credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Log out should log the user out and return to the log in page")
    @Test(groups = {"Sauce Demo login"})
    public void logOutTest() {
        Map<String, String> credentials = dbSteps.getStandardUserCredentials();
        labsLogInSteps
                .enterUserName(credentials.get("userName"))
                .enterPassword(credentials.get("password"))
                .pressLoginButton();
        labsProductsSteps
                .clickOnHamburgerMenu()
                .clickOnLogout();
        labsLogInSteps
                .userNameIsEmpty()
                .userPasswordIsEmpty();

    }


    @Feature("Sauce Demo Log In")
    @Description("Problematic user login redirects to the products page that sometimes doesn't have images loaded")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Problematic user log in sometimes redirects user to a problematic products page")
    @Flaky
    @Test(groups = {"Sauce Demo login", "Sauce Demo Products"})
    public void problematicLoginTest(){
        Map<String, String> credentials = dbSteps.getProblematicUserCredentials();
        labsLogInSteps
                .enterUserName(credentials.get("userName"))
                .enterPassword(credentials.get("password"))
                .pressLoginButton();

        for (SelenideElement image : labsProductsPage.productImages) {
            softAssert.assertFalse(image
                    .getAttribute("src").contains(SauceDemoConstants.PLACEHOLDER_DOG_IMG));
        }

        softAssert.assertAll();
    }

    @AfterMethod(groups = {"Sauce Demo"})
    public void quit() {
        Selenide.closeWebDriver();
    }

}