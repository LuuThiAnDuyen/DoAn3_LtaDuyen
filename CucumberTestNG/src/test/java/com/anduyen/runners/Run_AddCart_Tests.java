package com.anduyen.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/Cart/AddCart.feature",
        glue = {"com.anduyen.stepdefinitions"},
        plugin = {"pretty", "html:target/cucumber-html-report.html"}
        //tags = "@addMoreProducts"
)

@Test
public class Run_AddCart_Tests extends AbstractTestNGCucumberTests {

}