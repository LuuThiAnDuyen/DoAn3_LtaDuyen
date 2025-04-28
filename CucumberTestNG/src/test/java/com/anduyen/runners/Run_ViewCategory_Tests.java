package com.anduyen.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/ViewCategory",
        glue = {"com.anduyen.stepdefinitions"},
        plugin = {"pretty", "html:target/cucumber-html-report.html"}
        //tags = "@addMoreProducts"
)

@Test
public class Run_ViewCategory_Tests extends AbstractTestNGCucumberTests {

}