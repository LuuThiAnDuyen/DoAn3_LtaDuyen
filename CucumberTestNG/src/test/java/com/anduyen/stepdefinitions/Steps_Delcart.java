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
import java.util.List;

public class Steps_Delcart {
    @Given("user navigates to Cart")
    public void userNavigatesToCart() {
        BaseTest.createDriver("edge");
        WebDriver driver = DriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConstantGlobal.URL);
        Steps_AddMoreCart moreProducts = new Steps_AddMoreCart();
        moreProducts.userAddMoreProductsInCartSuccessful();
        driver.findElement(By.xpath("//a[@class='cartItem']")).click();
    }

    @When("user clicks delete")
    public void userClicksDelete() {
        DriverManager.getDriver().findElement(By.xpath("//tr[@class='item1']//span[@class='delete-cart'][normalize-space()='Xoá']")).click();
    }

    @Then("not find those product in cart")
    public void notFindThoseProductInCart() {
        WebDriver driver = DriverManager.getDriver();


        String deletedProductXPath = "//a[contains(text(),'ng Eucerin Cho Da Nh')]";


        List<WebElement> deletedProduct = driver.findElements(By.xpath(deletedProductXPath));

        if (deletedProduct.isEmpty()) {
            System.out.println("Sản phẩm đã được xóa thành công khỏi giỏ hàng.");
        } else {
            System.out.println("Sản phẩm vẫn còn trong giỏ hàng!");
        }


        List<WebElement> remainingProducts = driver.findElements(By.xpath("//form[@action='#']//div//table//tbody/tr"));
        System.out.println("🛒 Số lượng sản phẩm còn lại trong giỏ hàng: " + remainingProducts.size());
    }
}

