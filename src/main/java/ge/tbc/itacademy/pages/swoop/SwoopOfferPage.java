package ge.tbc.itacademy.pages.swoop;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class SwoopOfferPage {
    public SelenideElement checkoutSection = $(".checkoutSection"),
            progressbar = checkoutSection.find(".deal-validation .validation-count div"),
            favouriteButton = checkoutSection.find(".addWishlist"),
            informationSection = $(".infoSection"),
            share = informationSection.find(".merchantReview a");
}
