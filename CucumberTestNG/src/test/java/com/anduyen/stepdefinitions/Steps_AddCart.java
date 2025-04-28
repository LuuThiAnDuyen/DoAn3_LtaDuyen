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

public class Steps_AddCart {

    @When("user clicks an item to show detail product")
    public void userClicksAnItemToShowDetailProduct() throws InterruptedException {
        WebDriver driver = DriverManager.getDriver();
        driver.findElement(By.xpath("//div[@class='SaleProduct_sale__rfa6P']//div[1]//a[1]//div[1]//img[1]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'THÊM VÀO GIỎ HÀNG')]")).isDisplayed();

    }
    @Given("user navigates to Home page and login success")
    public void userNavigatesToHomePageAndLoginSuccess() throws InterruptedException {
        BaseTest.createDriver("edge");
        WebDriver driver = DriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Steps_LoginToAddCart login = new Steps_LoginToAddCart();
        login.userLogsInWithValidCredentials();
        driver.get("http://localhost:3000/");
    }
    @And("user clicks Add cart and view cart")
    public void userClicksAddCartAndViewCart() {
        WebDriver driver = DriverManager.getDriver();

        String oldValueCart = driver.findElement(By.xpath("//span[@class='value-cart']")).getText();
        int oldCartValue = Integer.parseInt(oldValueCart);

        driver.findElement(By.xpath("//button[contains(text(),'THÊM VÀO GIỎ HÀNG')]")).click();

        String newValueCart = driver.findElement(By.xpath("//span[@class='value-cart']")).getText();
        int newCartValue = Integer.parseInt(newValueCart);

        if (newCartValue == oldCartValue + 1) {
            System.out.println("Sản phẩm đã được thêm thành công. Giá trị giỏ hàng: " + newCartValue);
        } else {
            System.out.println("Lỗi: Giá trị giỏ hàng không tăng như mong đợi.");
        }
    }

    @Then("user verify product be displayed on cart")
    public void userVerifyProductBeDisplayedOnCart() throws InterruptedException {

        WebDriver driver = DriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        driver.findElement(By.xpath("//a[@class='cartItem']")).click();


        List<WebElement> cartRows = driver.findElements(By.xpath("//form[@action='#']//div//table//tbody/tr[td]"));

        if (cartRows.size() > 0) {
            System.out.println("Số lượng sản phẩm trong giỏ hàng: " + cartRows.size());

            for (WebElement row : cartRows) {
                try {

                    String productName = row.findElement(By.xpath(".//td[2]")).getText().trim();
                    String price = row.findElement(By.xpath(".//td[5]")).getText().trim();
                    WebElement quantityInput = row.findElement(By.xpath(".//td[6]//input"));
                    String quantity = quantityInput.getAttribute("value").trim();

                    System.out.println("Tên sản phẩm: " + productName + ", Giá: " + price + ", Số lượng: " + quantity);
                } catch (NoSuchElementException e) {
                    System.out.println("Lỗi: Không thể tìm thấy một số phần tử trong hàng giỏ hàng.");
                }
            }
        } else {
            System.out.println("Lỗi: Giỏ hàng không có sản phẩm nào.");
        }

        Thread.sleep(2000);
        driver.quit();
    }



}

