package automationFramework.handlers;

import org.openqa.selenium.WebDriver;

public class PageObjectsHandler {

    protected WebDriver driver = null;

    protected PageObjectsHandler(WebDriver driver){
        this.driver = driver;
    }
}
