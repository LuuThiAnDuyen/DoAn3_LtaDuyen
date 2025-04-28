package com.anduyen.stepdefinitions;

import com.anduyen.common.BaseTest;
import com.anduyen.constants.ConstantGlobal;
import com.anduyen.driver.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
public class Steps_AddMoreCart {
    public void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    @Given("user add more products in cart successful")
    public void userAddMoreProductsInCartSuccessful() {
        //BaseTest.createDriver("edge");
        WebDriver driver = DriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConstantGlobal.URL);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.xpath("//div[@class='SaleProduct_sale__rfa6P']//div[1]//a[1]//div[1]//img[1]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'THÊM VÀO GIỎ HÀNG')]")).click();

        WebElement lq = driver.findElement(By.xpath("//div[@class='Detail_recommend__8FyTC']//div[1]//a[1]//div[1]//img[1]"));
        scrollToElement(driver, lq);
        lq.click();
        driver.findElement(By.xpath("//button[contains(text(),'THÊM VÀO GIỎ HÀNG')]")).click();
    }
}
