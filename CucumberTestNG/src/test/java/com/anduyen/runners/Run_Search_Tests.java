package com.anduyen.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/Search",
        glue = {"com.anduyen.stepdefinitions"},
        plugin = {"pretty", "html:target/cucumber-html-report.html"},
        tags = "@keyword"
)

@Test
public class Run_Search_Tests extends AbstractTestNGCucumberTests {

}