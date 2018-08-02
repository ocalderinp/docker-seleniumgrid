package us.abstracta.opencart.pageObjectsHandler;

import automation.handlers.PageObjectsHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import us.abstracta.opencart.pages.Cart;
import us.abstracta.opencart.pages.Header;
import us.abstracta.opencart.pages.HomePage;
import us.abstracta.opencart.pages.SearchResultPage;

public class OpencartPageObjectsHandler extends PageObjectsHandler {

    private static OpencartPageObjectsHandler instance = null;

    private HomePage homePage;
    private Header header;
    private Cart cart;
    private SearchResultPage searchResultPage;

    protected OpencartPageObjectsHandler(WebDriver driver) {
        super(driver);
    }

    public static OpencartPageObjectsHandler getInstance(WebDriver driver) {
        if (instance == null) {
            instance = new OpencartPageObjectsHandler(driver);
        }
        return instance;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage(driver, instance);
            PageFactory.initElements(driver, homePage);
        }
        return homePage;
    }

    public SearchResultPage getSearchResultPage() {
        if (searchResultPage == null) {
            searchResultPage = new SearchResultPage(driver, instance);
            PageFactory.initElements(driver, searchResultPage);
        }
        return searchResultPage;
    }

    public Header getHeader() {
        if (header == null) {
            header = new Header(driver, instance);
            PageFactory.initElements(driver, header);
        }
        return header;
    }

    public Cart getCart() {
        if (cart == null) {
            cart = new Cart(driver, instance);
            PageFactory.initElements(driver, cart);
        }
        return cart;
    }

    public static void setInstanceNull() {

        instance = null;
    }
}
