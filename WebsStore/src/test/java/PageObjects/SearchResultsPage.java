package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SearchResultsPage {
    // Gets
    private static WebElement getSearchResultHeader(WebDriver driver){
        return driver.findElement(By.xpath("//h1[@class='page-header search--title']"));
    }
    private static WebElement getNextPageButton(WebDriver driver){
        return driver.findElement(By.cssSelector("#paging > nav > ul > li.inline-block.leading-4.align-top.rounded-r-md > a"));
    }
    private static WebElement getViewCartButton(WebDriver driver){
        return driver.findElement(By.xpath("//*[@id='react_0HN25O4MDQGD6']/div[2]/div/div[1]/div[4]/a"));
    }

    // Cart instance
    CartPage cartPage = new CartPage();

    // Public Functions
    public void VerifySearchResultsContainString(WebDriver driver, String verificationString){
        // Get the number of results from the search header
        int totalNumOfElements = GetNumberOfResults(driver);
        // Assertion to ensure test continues as long as results were found
        if (totalNumOfElements == 0){
            fail("No results found in search");
        }
        // Store all item descriptions that contain the term Table
        int numOfMatchingElements = GetNumberOfMatchingElements(driver, verificationString);
        // Assertion to test if all elements found match the number of elements that contain Table in the description
        assertEquals(totalNumOfElements, numOfMatchingElements, "Comparing total number of elements returned against the number of elements that contain Table");
    }

    public void AddLastItemToCart(WebDriver driver){
        // Get all the elements
        List<WebElement> matchingElements = driver.findElements(By.xpath("//*[@id=\"ProductBoxContainer\"]/div[4]"));
        // Find the last element
        WebElement lastItem = matchingElements.getLast();
        // Click add to cart
        lastItem.click();
    }

    public void EmptyCart(WebDriver driver){
        // Find the view cart button
        WebElement viewCartbutton = getViewCartButton(driver);

        // Click view cart button
        viewCartbutton.click();

        // Wait for popup to be there
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        // Click 1st Empty Cart button
        cartPage.ClickFirstEmptyCartButton(driver);

        // Wait for button to be there
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        // Click 2nd Empty cart button
        cartPage.ClickSecondEmptyCartButton(driver);
    }


    // Private Functions
    private int GetNumberOfResults (WebDriver driver){
        // Get the header and store in a string
        String header = getSearchResultHeader(driver).getText();
        // Assumes the number of results is always first
        // Split string into an array
        String[] parts = header.split(" ");
        // Grab number of results/elements returned
        return Integer.parseInt(parts[0]);
    }
    private int GetNumberOfMatchingElements(WebDriver driver, String verificationString) {
        // Var to hold the total found
        List<WebElement> matchingElements;
        // Iterate through pages to get the total count that match
        String s;
        int total=0;
        do {
            matchingElements = driver.findElements(By.xpath("//span[@data-testid='itemDescription'][contains(text(), '"+verificationString+"')]"));
            total += matchingElements.size();
            s = getNextPageButton(driver).getAttribute("aria-label");
            getNextPageButton(driver).click();
        } while (!s.contains("current page"));

        return total;
    }
}
