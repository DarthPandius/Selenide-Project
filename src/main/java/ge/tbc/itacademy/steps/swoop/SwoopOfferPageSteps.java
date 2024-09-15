package ge.tbc.itacademy.steps.swoop;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ge.tbc.itacademy.pages.swoop.SwoopOfferPage;
import io.qameta.allure.Step;

import java.util.Objects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class SwoopOfferPageSteps {
    SwoopOfferPage offerPage = new SwoopOfferPage();


    @Step("Click on add to favourites")
    public SwoopOfferPageSteps addToFavourites() {
        offerPage.favouriteButton.scrollIntoView(false).shouldBe(clickable).click();
        return this;
    }

    @Step("Click on share offer")
    public SwoopOfferPageSteps shareOffer() {
        offerPage.share.scrollIntoView(false).shouldBe(clickable).click();
        return this;
    }

    @Step("Get progress bar percentage")
    public String salesProgressBar() {
        return offerPage.progressbar.shouldBe(visible).getAttribute("style");
    }

    @Step("Changing Windows")
    public void gotToFBLogInPage() {
        Selenide.switchTo().window(1);
    }
}
