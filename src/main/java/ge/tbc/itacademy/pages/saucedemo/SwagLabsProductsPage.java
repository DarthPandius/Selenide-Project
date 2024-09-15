package ge.tbc.itacademy.pages.saucedemo;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SwagLabsProductsPage {
    public SelenideElement headerContainer = $(".primary_header"),
            hamburgerMenu = $("#react-burger-menu-btn"),
            logoutOption = headerContainer.$x(".//nav//a[text()='Logout']");

    public ElementsCollection productImages = $$("img.inventory_item_img");
}
