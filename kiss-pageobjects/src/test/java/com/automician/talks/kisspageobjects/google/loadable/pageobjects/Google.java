package com.automician.talks.kisspageobjects.google.loadable.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.automician.talks.kisspageobjects.google.loadable.helpers.CustomConditions.numberIsAtLeast;
import static org.junit.Assert.assertTrue;

/**
 * Created by yashaka on 1/9/17.
 */
public class Google extends LoadableComponent<Google>{
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name = "q")
    private WebElement searchInput;

    @FindBy(css = ".srg>.g")
    private List<WebElement> results;

    public List<WebElement> getResults() {
        return this.results;
    }

    public Google(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 4);
        PageFactory.initElements(driver, this);
    }

//    public Google open() {
//        this.driver.get("http://google.com/ncr");
//        return this;
//    }

    protected void load() {
        this.driver.get("http://google.com/ncr");
    }

    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        assertTrue("Not on the google page: " + url,
                url.contains("www.google.com"));
    }

    public Google search(String text) {
        this.searchInput.sendKeys(text + Keys.ENTER);
        return this;
    }

    public void followResultLink(int index) {
        wait.until(numberIsAtLeast(results, index + 1))
                .get(index)
                .findElement(By.cssSelector(".r>a"))
                .click();
    }
}
