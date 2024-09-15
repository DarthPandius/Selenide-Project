package ge.tbc.itacademy.steps.swoop;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ge.tbc.itacademy.exceptions.NoOffersException;
import ge.tbc.itacademy.pages.swoop.SwoopSectionPage;
import io.qameta.allure.Step;

import java.util.Objects;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class SwoopSectionPageSteps {
    SwoopSectionPage swoopSectionPage = new SwoopSectionPage();


    @Step("Set price lower bound in filters")
    public SwoopSectionPageSteps enterMinPriceInFilters(String minPrice) {
        swoopSectionPage.minPrice
                .scrollIntoView(false)
                .sendKeys(minPrice);
        return this;
    }

    @Step("Set upper bound for price in filters")
    public SwoopSectionPageSteps enterMaxPriceInFilters(String maxPrice) {
        swoopSectionPage.maxPrice
                .scrollIntoView(false)
                .sendKeys(maxPrice);
        return this;
    }

    @Step("Click on filter to filter results")
    public SwoopSectionPageSteps clickOnFilter() {
        swoopSectionPage
                .filterSubmitButton
                .scrollIntoView(false)
                .click();
        return this;
    }

    @Step("Click on first offer")
    public SwoopSectionPageSteps clickOnFirstOffer() {
        swoopSectionPage.firstOffer.shouldBe(clickable).click();
        return this;
    }

    @Step("get progress of the unsold offer progress bar")
    public String unsoldOfferProgressBar() {
        if (swoopSectionPage.unsoldOfferProgressBar == null) {
            throw new NoOffersException("There are no unsold offers");
        }
        return swoopSectionPage.unsoldOfferProgressBar.getAttribute("data-width");
    }

    @Step("Choose payment method in filters")
    public SwoopSectionPageSteps choosePaymentMethodTBC() {
        swoopSectionPage.tbcPayment.shouldBe(clickable)
                .scrollIntoView(false)
                .click();
        return this;
    }

    @Step("Click to expand location dropdown")
    public SwoopSectionPageSteps openLocationDropdown() {
        swoopSectionPage.dropDownArrow
                .scrollIntoView(false).shouldBe(clickable)
                .click();
        return this;
    }

    @Step("Choose location from location dropdown")
    public SwoopSectionPageSteps chooseLocation() {
        swoopSectionPage.firstOption.scrollTo().shouldBe(clickable).click();
        return this;
    }

    @Step("Clear filters by clicking on a rubbish bin")
    public SwoopSectionPageSteps clearFilters() {
        swoopSectionPage.filtersRubbishBin
                .scrollIntoView(false).shouldBe(clickable)
                .click();
        return this;
    }

    @Step("Check that location is set to the default value after resting filters")
    public SwoopSectionPageSteps locationIsClear() {
        swoopSectionPage.defaultDropdownName.shouldBe(visible);
        return this;
    }

    @Step("Check that payment method is set to the default value after resting filters")
    public SwoopSectionPageSteps paymentMethodIsDefault() {
        swoopSectionPage.defaultPaymentMethod.shouldBe(checked);
        return this;
    }

    @Step("Check that minimum price is cleared after resting filters")
    public boolean minPriceIsCleared() {
        return swoopSectionPage.minPrice.getValue().isEmpty();
    }

    @Step("Check that maximum price is cleared after resting filters")
    public boolean maxPriceIsCleared() {
        return swoopSectionPage.maxPrice.getValue().isEmpty();
    }

    @Step("Check that offers available")
    public SwoopSectionPageSteps checkThatOffersAreAvailable() {
        if (swoopSectionPage.offersNotFoundMessage.exists()) {
            throw new NoOffersException("There are no offers in given price range");
        }
        return this;
    }

    @Step("Get filtered results")
    public ElementsCollection getFilteredOffers() {
        return swoopSectionPage.filteredOfferPrices.shouldHave(sizeGreaterThan(0));
    }

    @Step("Scroll to offer")
    public SwoopSectionPageSteps goTo(SelenideElement price) {
        price.scrollIntoView(false);
        return this;
    }

    @Step("Look for the offer in the section")
    public SwoopSectionPageSteps lookForOffer() {
        while (swoopSectionPage.unsoldOfferProgressBar == null &&
                !Objects.requireNonNull(swoopSectionPage.pagination
                        .getAttribute("style")).contains("none")) {
            swoopSectionPage.pagination.scrollIntoView(false).click();
        }
        if (swoopSectionPage.unsoldOfferProgressBar == null)
            throw new NoOffersException("there are no unsold offers");
        return this;
    }
}