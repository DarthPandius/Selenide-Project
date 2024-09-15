package ge.tbc.itacademy.pages.swoop;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SwoopSectionPage {
    public SelenideElement filters = $$("section .category-re-filter.second-filter-category").findBy(visible),
            minPrice = filters.find(".price-filter #minprice"),
            maxPrice = filters.find(".price-filter #maxprice"),
            filterSubmitButton = filters.find(".submit-button"),
            tbcPayment = filters.find(By.xpath(".//label[text() ='თიბისი განაწილება']//input")),
            defaultPaymentMethod = filters.find(By.xpath(".//label[text() ='ყველა']//input")),
            locationDropdown = filters.find(".ms-parent.MultipleSelect"),
            dropDownArrow = locationDropdown.find("button"),
            defaultDropdownName = dropDownArrow.find(".placeholder"),
            firstOption = locationDropdown.findAll(".bottom ul li").first(),
            filtersRubbishBin = filters.find(".delete-search-button"),
            offersSection = $(" .deal-container");

    public ElementsCollection offers = offersSection.$$(".special-offer"),
            offersNotYetSold = $$x("//*[text() = 'გაყიდულია 0']"),
            filteredOfferPrices = $$("#render .items .special-offer .discounted-prices p:first-child");

    public SelenideElement firstOffer = offers.get(0).find(".special-offer-img-container"),
            unsoldOfferProgressBar = offersNotYetSold.first().ancestor(".special-offer").find(".voucher-limit"),
            offersNotFoundMessage = $("#render .items .deal-not-find"),
            pagination = $(".pagination [src = '/Images/NewDesigneImg/categoryIn/arrow-01.png']")
            .parent();
}
