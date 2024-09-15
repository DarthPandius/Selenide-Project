package ge.tbc.itacademy.steps.swoop;

import com.codeborne.selenide.SelenideElement;
import ge.tbc.itacademy.exceptions.NoSuchSectionException;
import ge.tbc.itacademy.pages.swoop.SwoopBasePage;
import io.qameta.allure.Step;

import java.util.Objects;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class SwoopBasePageSteps {
    SwoopBasePage basePage = new SwoopBasePage();



    @Step("Navigating to section {0}")
    public SwoopBasePageSteps navigateToSection(String sectionName) {
        if (basePage.SectionName(sectionName) == null) {
            throw new NoSuchSectionException("NO SUCH SECTION WAS FOUND");
        }
        basePage.SectionName(sectionName)
                .scrollTo()
                .shouldBe(clickable)
                .click();
        return this;
    }

    @Step("Accept cookies")
    public SwoopBasePageSteps acceptCookiesIfPresent() {
        if (basePage.acceptCookies.isDisplayed()) {
            basePage.acceptCookies.click();
        }
        return this;
    }

    @Step("Click on dropdown categories")
    public SwoopBasePageSteps clickOnCategoriesDropdown() {
        basePage.categoriesDropdown.shouldBe(clickable).click();
        return this;
    }

    @Step("Hover On Holidays category")
    public SwoopBasePageSteps hoverOnHolidayCategory() {
        basePage.HolidayDDownSection.shouldBe(visible)
                .hover();
        return this;
    }

    @Step("Click on Subcategory {0}")
    public SwoopBasePageSteps selectFirstSubCategory(String subCategory) {
        basePage.subSectionName(subCategory).shouldBe(clickable).click();
        return this;
    }

}
