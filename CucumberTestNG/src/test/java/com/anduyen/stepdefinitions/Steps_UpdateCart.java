package com.anduyen.stepdefinitions;

import com.anduyen.common.BaseTest;
import com.anduyen.constants.ConstantGlobal;
import com.anduyen.driver.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class Steps_UpdateCart {
    @Given("user navigates to cart page")
    public void userNavigatesToCartPage() {
        BaseTest.createDriver("edge");
        WebDriver driver = DriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConstantGlobal.URL);
        Steps_AddMoreCart moreProducts = new Steps_AddMoreCart();
        moreProducts.userAddMoreProductsInCartSuccessful();
        driver.findElement(By.xpath("//a[@class='cartItem']")).click();
    }

    @When("user change quantity of product")
    public void userChangeQuantityOfProduct() {
        DriverManager.getDriver().findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[6]/div[1]/*[name()='svg'][2]")).click();
    }

    @When("user enters quantity of product")
    public void userEntersQuantityOfProduct() {
        WebElement quantity = DriverManager.getDriver().findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[6]/div[1]/input[1]"));
        quantity.clear();
        quantity.sendKeys("2");
    }

    @Then("system returns respective amount")
    public void systemReturnsRespectiveAmount() throws InterruptedException {
        String  sl = DriverManager.getDriver().findElement(By.xpath("//tr[@class='item0']//input[@value='1']")).getAttribute("value");
        if (sl.equals("2")){
            System.out.println("Chính xác");
        }
        Thread.sleep(2000);
        DriverManager.quit();
    }


}
