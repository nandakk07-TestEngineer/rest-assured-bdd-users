package com.automation.runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/resources/features",
		glue = {
				 "com.automation.stepDefinitions",
	             "com.automation.hooks"
		},
		tags = "@regression",
		plugin    = {
		               "pretty",
		               "html:target/cucumber-reports/smoke.html",
		               "json:target/cucumber-reports/smoke.json",
		               "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
		  },
	   monochrome = true
)

public class TestRunner extends AbstractTestNGCucumberTests{
	
	@Override
	@DataProvider(parallel=false)
	public Object[][] scenarios(){
		return super.scenarios();
	}
}
