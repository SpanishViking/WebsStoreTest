package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    // Gets
    private static WebElement getSearchBox (WebDriver driver){
        return driver.findElement(By.xpath("(//input[@id='searchval'])[1]"));
    }
    private static WebElement getSearchButton (WebDriver driver){
        return driver.findElement(By.xpath("(//button[@value='Search'])[1]"));
    }

    // Functions
    public void SearchForProduct(WebDriver driver, String searchPhrase){
        getSearchBox(driver).sendKeys(searchPhrase); // Input the search phrase
        getSearchButton(driver).click(); // Click the search button
    }
}
