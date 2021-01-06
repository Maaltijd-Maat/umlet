package com.baselet.standalone;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/cucumber-reports"}, dryRun = true,
        features = "src/test/resources/cucumber",
        glue = "com/baselet/standalone")
public class CucumberRunnerTest {
}