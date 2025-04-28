package com.anduyen.runners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/Login",
        glue = {"com.anduyen.stepdefinitions"},
        plugin = {"pretty", "html:target/cucumber-html-report.html"},
        tags = "@success"
)

@Test
public class Run_Login_Tests extends AbstractTestNGCucumberTests {

}