package com.anduyen.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/SignUp",
        glue = {"com.anduyen.stepdefinitions"},
        plugin = {"pretty", "html:target/cucumber-html-report.html"},
        tags = "@empty"
)

@Test
public class Run_SignUp_Tests extends AbstractTestNGCucumberTests {

}