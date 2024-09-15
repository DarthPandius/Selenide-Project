package ge.tbc.itacademy.pages.swoop;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SwoopBasePage {
    public SelenideElement header = $(".NewHeader"),
            footer = $(".NewFooter"),
            acceptCookies = $(".cookieSection  .acceptCookie"),
            categoriesDropdown = header.$(".HeaderContainer .NewCategories"),
            sectionsMenu = header.find(".HeaderContainer .categoriesSub.openedMenu .LeftSideCategories"),
            HolidayDDownSection = sectionsMenu.find(".catId-2");

    public SelenideElement SectionName(String sectionName) {
        return footer.$(byText(sectionName));
    }

    public SelenideElement subSectionName(String sectionName) {
        return HolidayDDownSection.$(byText(sectionName));
    }
}
