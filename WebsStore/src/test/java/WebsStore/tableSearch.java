package WebsStore;

import PageObjects.HomePage;
import PageObjects.SearchResultsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class tableSearch {
    public static WebDriver webDriver = new ChromeDriver();

    // Instances of pages
    HomePage homePage = new HomePage();
    SearchResultsPage searchResultsPage = new SearchResultsPage();

    @BeforeEach
    void Setup(){
        WebDriverManager.chromedriver().setup(); // Download driver
    }

    @AfterEach
    void TearDown(){
        webDriver.quit(); // Close window and end session
    }


    @Test
    void searchTableAddToCartEmptyCart(){
        // Vars
        String searchPhrase = "stainless work table";
        String verificationString = "Table"; // To pass test, change Table to Stainless

        // Step 1) Open page
        webDriver.get("https://www.webstaurantstore.com/");

        // Step 2) Search for the stainless work table
        homePage.SearchForProduct(webDriver, searchPhrase);

        // Step 3) Verify all products contain the word Table in title
        searchResultsPage.VerifySearchResultsContainString(webDriver, verificationString);

        // Step 4) Add last item to cart
        searchResultsPage.AddLastItemToCart(webDriver);

        // Step 5) Empty the cart
        searchResultsPage.EmptyCart(webDriver);

    }
}
