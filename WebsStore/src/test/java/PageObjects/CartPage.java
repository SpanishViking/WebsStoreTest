package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
    // Gets
    private static WebElement getFirstEmptyCartButton(WebDriver driver){
        return driver.findElement(By.xpath("//*[@id='main']/div[1]/div/div[1]/div/button"));
    }
    private static WebElement getSecondEmptyCartButton(WebDriver driver){
        return driver.findElement(By.xpath("//*[@id='td']/div[11]/div/div/div/footer/button[1]"));
    }

    // Public functions
    public void ClickFirstEmptyCartButton(WebDriver driver){
        getFirstEmptyCartButton(driver).click();
    }
    public void ClickSecondEmptyCartButton(WebDriver driver){
        getSecondEmptyCartButton(driver).click();
    }
}
