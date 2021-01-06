package com.baselet.standalone;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "pretty", "html:target/cucumber-reports", "json:target/cucumber.json"},
        features = "src/test/resources/cucumber",
        glue = "com/baselet/standalone")
public class CucumberRunnerTest {
}