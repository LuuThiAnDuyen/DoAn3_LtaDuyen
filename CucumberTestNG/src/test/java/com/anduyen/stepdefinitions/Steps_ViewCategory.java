package com.anduyen.stepdefinitions;

import com.anduyen.driver.DriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Steps_ViewCategory {
    @When("user click menu list and click category serum")
    public void userClickMenuListAndClickCategorySerum() {
        WebDriver driver = DriverManager.getDriver();
        Actions actions = new Actions(driver);
        driver.findElement(By.xpath("//div[@class='ti-menu list']//*[name()='svg']")).click();
        WebElement danhmuc = driver.findElement(By.xpath("//a[contains(text(),'Danh mục')]"));
        actions.moveToElement(danhmuc).perform();
        driver.findElement(By.xpath("//a[@href='/category/Serum'][normalize-space()='Serum']")).click();
    }

    @Then("system returns appropriate category")
    public void systemReturnsAppropriateCategory() {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<WebElement> serumList = driver.findElements(By.className("Category_home-product-item_name__nB-r0"));
        System.out.println("Số sản phẩm serum hiện có: "+serumList.size());

        if (serumList.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm Serum nào.");
        } else {
            System.out.println("Danh sách sản phẩm Serum:");
            for (WebElement serum : serumList) {
                String productName = serum.getText().trim();
                System.out.println(productName);
            }
        }
        driver.quit();
    }
}