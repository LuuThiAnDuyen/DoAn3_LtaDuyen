package com.anduyen.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/Cart/UpdateCart.feature",
        glue = {"com.anduyen.stepdefinitions"},
        plugin = {"pretty", "html:target/cucumber-html-report.html"}
        //tags = "@addMoreProducts"
)

@Test
public class Run_UpdateCart_Tests extends AbstractTestNGCucumberTests {

}