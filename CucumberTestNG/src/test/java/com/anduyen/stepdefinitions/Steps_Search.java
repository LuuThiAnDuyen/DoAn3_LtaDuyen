package com.anduyen.stepdefinitions;

import com.anduyen.common.BaseTest;
import com.anduyen.constants.ConstantGlobal;
import com.anduyen.database.DatabaseHelper;
import com.anduyen.driver.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class Steps_Search {
    @Given("user navigates to Home page")
    public void userNavigatesToHomePage() {
        BaseTest.createDriver("edge");
        WebDriver driver = DriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConstantGlobal.URL);
        driver.findElement(By.xpath("//h2[contains(text(),'SẢN PHẨM SALE')]")).isDisplayed();

    }

    @When("user clicks on suggestion under search box")
    public void userClicksOnSuggestionUnderSearchBox() {
        DriverManager.getDriver().findElement(By.xpath("//a[contains(text(),'Sữa rửa mặt')]")).click();
    }

    private String searchKeyword = "";

    @When("user enters search keyword {string} and clicks enter")
    public void userEntersSearchKeyword(String arg0) {
        WebDriver driver = DriverManager.getDriver();
        searchKeyword = arg0.toLowerCase().trim();
        WebElement searchBox = driver.findElement(By.xpath("//input[@placeholder='Nhập từ khóa tìm kiếm']"));

        searchBox.sendKeys(arg0);
        searchBox.sendKeys(Keys.ENTER);

        int totalUiCount = 0;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        while (true) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Search_home-product-item_name__oBqbG")));
            List<WebElement> searchResults = driver.findElements(By.className("Search_home-product-item_name__oBqbG"));
            totalUiCount += searchResults.size();

            try {
                WebElement page = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@title='Next Page']//button[@type='button']")));
                scrollAndClick(driver, page);

            } catch (TimeoutException e) {
                break;
            }
        }

        int dbCount = DatabaseHelper.getSearchResultCount(arg0);
        assertEquals("Số lượng kết quả không khớp!", dbCount, totalUiCount);
    }
    public void scrollAndClick(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            js.executeScript("arguments[0].click();", element);
        }
    }
    @Then("system returns appropriate results")
    public void systemReturnsAppropriateResults() {
        WebDriver driver = DriverManager.getDriver();
        List<WebElement> searchResults = driver.findElements(By.className("Search_home-product-item_name__oBqbG"));

        if (searchResults.isEmpty()) {
            System.out.println("Không có kết quả tìm kiếm nào.");
            DriverManager.quit();
            return;
        }

        System.out.println("Các kết quả tìm kiếm:");
        for (WebElement result : searchResults) {
            String resultText = result.getText().toLowerCase().trim();
            System.out.println(resultText);

            // Kiểm tra nếu kết quả chứa từ khóa tìm kiếm
            if (resultText.contains(searchKeyword)) {
                System.out.println("=> Hiển thị đúng kết quả");
            } else {
                System.out.println("=> Hiển thị không đúng kết quả: " + resultText);
            }
        }

        Assert.assertTrue(searchResults.size() > 0, "Không có kết quả tìm kiếm nào được hiển thị.");
        DriverManager.quit();
    }

}
