package ge.tbc.itacademy.swooptests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
//import com.codeborne.selenide.testng.ScreenShooter;
import ge.tbc.itacademy.data.constants.SwoopConstants;
import ge.tbc.itacademy.data.dataproviders.DataProviders;
import ge.tbc.itacademy.listeners.NewAllureListener;
import ge.tbc.itacademy.listeners.screenshotListener;
import ge.tbc.itacademy.pages.swoop.SwoopBasePage;
import ge.tbc.itacademy.pages.swoop.SwoopOfferPage;
import ge.tbc.itacademy.pages.swoop.SwoopSectionPage;
import ge.tbc.itacademy.steps.swoop.SwoopBasePageSteps;
import ge.tbc.itacademy.steps.swoop.SwoopOfferPageSteps;
import ge.tbc.itacademy.steps.swoop.SwoopSectionPageSteps;
import ge.tbc.itacademy.util.Util;
import io.qameta.allure.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.LocalTime;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

@Listeners({screenshotListener.class})
@Epic("Swoop Functional")
public class SwoopTests {
    SwoopBasePage swoopBasePage;
    SwoopBasePageSteps swoopBasePageSteps;
    SwoopSectionPage swoopSectionPage;
    SwoopSectionPageSteps swoopSectionPageSteps;
    SwoopOfferPage swoopOfferPage;
    SwoopOfferPageSteps swoopOfferPageSteps;

    SoftAssert softAssert;

    @BeforeClass(groups = "Swoop")
    public void setup()
    {
        Configuration.reportsFolder = "SwoopScreenshots";
        this.swoopBasePage = new SwoopBasePage();
        this.swoopBasePageSteps = new SwoopBasePageSteps();
        this.swoopSectionPage = new SwoopSectionPage();
        this.swoopSectionPageSteps = new SwoopSectionPageSteps();
        this.swoopOfferPage = new SwoopOfferPage();
        this.swoopOfferPageSteps = new SwoopOfferPageSteps();

    }

    @BeforeMethod(groups = "Swoop")
    public void browserSetup() {
        this.softAssert = new SoftAssert();
        open(SwoopConstants.SWOOP_URL);
        getWebDriver().manage().window().maximize();
    }

    @Feature("Filter Functionality")
    @Description("Testing that filter on holiday page displays offers correct in price range upon setting boundaries")
    @Severity(SeverityLevel.NORMAL)
    @Story("Price range filter tests")
    @Test(dataProvider = "MinAndMaxPrices", dataProviderClass = DataProviders.class,groups = {"Swoop filter"})
    public void rangeTest(String minPrice, String maxPrice){
        swoopBasePageSteps
                .acceptCookiesIfPresent()
                .navigateToSection(SwoopConstants.HOLIDAY_SECTION);

        swoopSectionPageSteps
                .enterMinPriceInFilters(minPrice)
                .enterMaxPriceInFilters(maxPrice)
                .clickOnFilter()
                .checkThatOffersAreAvailable()
                .getFilteredOffers()
                        .forEach(price -> {
                            swoopSectionPageSteps.goTo(price);
                            softAssert.assertTrue(Util.isInRange(price.getText(), minPrice, maxPrice));
                        });
        softAssert.assertAll();
    }


    @Feature("Adding to favourites functionality")
    @Description("Authorization should be required upon adding an item to favorites.")
    @Severity(SeverityLevel.CRITICAL)
    @Story("After adding the offer to favorites, the user should be redirected to the login page.")
    @Test(groups = "Swoop Sharing")
    public void favouriteOfferTest() {
        swoopBasePageSteps
                .acceptCookiesIfPresent()
                .clickOnCategoriesDropdown()
                .hoverOnHolidayCategory()
                .selectFirstSubCategory(SwoopConstants.MOUNTAIN_RESORTS);
        swoopSectionPageSteps
                .clickOnFirstOffer();

        softAssert.assertTrue(Util.isNotFull(swoopOfferPageSteps.salesProgressBar()));

        swoopOfferPageSteps
                .addToFavourites();

        softAssert.assertTrue(url().contains(SwoopConstants.TNET_URL));
        softAssert.assertAll();
    }

    @Feature("Sharing functionality")
    @Description("Upon clicking on the sharing offer, the Facebook login page should be redirected to.")
    @Severity(SeverityLevel.MINOR)
    @Story("Clicking on Sharing redirects to facebook sign in ")
    @Test(groups = "Swoop Social")
    public void shareOfferTest() {
        swoopBasePageSteps
                .acceptCookiesIfPresent()
                .clickOnCategoriesDropdown()
                .hoverOnHolidayCategory()
                .selectFirstSubCategory(SwoopConstants.MOUNTAIN_RESORTS);
        swoopSectionPageSteps
                .clickOnFirstOffer();
        swoopOfferPageSteps
                .shareOffer()
                .gotToFBLogInPage();
        softAssert.assertTrue(url().contains("facebook"));
        softAssert.assertAll();
    }

    @Feature("Cleat filter functionality")
    @Description("Upon clicking on filter's bin the default choices should be returned")
    @Severity(SeverityLevel.NORMAL)
    @Story("After clearing filter default parameters should be returned")
    @Test(groups = "Swoop Filter")
    public void clearFilterTest() {
        swoopBasePageSteps
                .acceptCookiesIfPresent()
                .clickOnCategoriesDropdown()
                .hoverOnHolidayCategory()
                .selectFirstSubCategory(SwoopConstants.MOUNTAIN_RESORTS);

        swoopSectionPageSteps
                .choosePaymentMethodTBC()
                .openLocationDropdown()
                .chooseLocation()
                .enterMinPriceInFilters(SwoopConstants.MIN_PRICE)
                .enterMaxPriceInFilters(SwoopConstants.MAX_PRICE)
                .clearFilters()
                .locationIsClear()
                .paymentMethodIsDefault();

        softAssert.assertTrue(swoopSectionPageSteps.minPriceIsCleared());
        softAssert.assertTrue(swoopSectionPageSteps.maxPriceIsCleared());

        softAssert.assertAll();
    }

    @Feature("UI")
    @Description("Unsold offer bar is blanc")
    @Severity(SeverityLevel.TRIVIAL)
    @Test(groups = "Swoop UI")
    public void noOffersSoldTest() {

        swoopBasePageSteps
                .acceptCookiesIfPresent()
                .clickOnCategoriesDropdown()
                .hoverOnHolidayCategory()
                .selectFirstSubCategory(SwoopConstants.MOUNTAIN_RESORTS);

        swoopSectionPageSteps.lookForOffer();

        softAssert.assertTrue(Util.isEmpty(swoopSectionPageSteps.unsoldOfferProgressBar()));
        softAssert.assertAll();
    }

    @AfterMethod(groups = "Swoop")
    public void quitBrowser() {
        Selenide.closeWebDriver();
    }

}